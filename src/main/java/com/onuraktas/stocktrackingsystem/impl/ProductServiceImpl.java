package com.onuraktas.stocktrackingsystem.impl;

import com.onuraktas.stocktrackingsystem.amqp.producer.ProductProducer;
import com.onuraktas.stocktrackingsystem.constant.QueueName;
import com.onuraktas.stocktrackingsystem.dto.amqp.DeletedProductMessage;
import com.onuraktas.stocktrackingsystem.dto.entity.ProductDto;
import com.onuraktas.stocktrackingsystem.dto.general.SimpleCategory;
import com.onuraktas.stocktrackingsystem.dto.request.CreateProductRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateProductAmountRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateProductResponse;
import com.onuraktas.stocktrackingsystem.dto.response.DeleteProductByIdResponse;
import com.onuraktas.stocktrackingsystem.entity.Category;
import com.onuraktas.stocktrackingsystem.entity.CategoryProductRel;
import com.onuraktas.stocktrackingsystem.entity.Product;
import com.onuraktas.stocktrackingsystem.entity.enums.Status;
import com.onuraktas.stocktrackingsystem.exception.ProductBadRequestException;
import com.onuraktas.stocktrackingsystem.exception.ProductNotFoundException;
import com.onuraktas.stocktrackingsystem.helper.GeneralValues;
import com.onuraktas.stocktrackingsystem.mapper.ProductMapper;
import com.onuraktas.stocktrackingsystem.message.ExceptionMessages;
import com.onuraktas.stocktrackingsystem.message.ProductMessages;
import com.onuraktas.stocktrackingsystem.repository.CategoryProductRelRepository;
import com.onuraktas.stocktrackingsystem.repository.CategoryRepository;
import com.onuraktas.stocktrackingsystem.repository.ProductRepository;
import com.onuraktas.stocktrackingsystem.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryProductRelRepository categoryProductRelRepository;
    private final CategoryRepository categoryRepository;
    private final ProductProducer productProducer;

    public ProductServiceImpl (ProductRepository productRepository, CategoryProductRelRepository categoryProductRelRepository, CategoryRepository categoryRepository, ProductProducer productProducer){
        this.productRepository = productRepository;
        this.categoryProductRelRepository = categoryProductRelRepository;
        this.categoryRepository = categoryRepository;
        this.productProducer = productProducer;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {

        this.validateCreateProductRequest(createProductRequest);

        List<Category> categoryList = this.categoryRepository.findAllByCategoryIdInAndIsActive(createProductRequest.getCategoryList().stream().map(SimpleCategory::getCategoryId).toList(), Boolean.TRUE);

        if (categoryList.isEmpty())
            throw new ProductBadRequestException(ProductMessages.CATEGORY_NOT_FOUND);

        List<UUID> categoryIdList = categoryList.stream().map(Category::getCategoryId).toList();

        Product product = this.productRepository.save(ProductMapper.toEntity(createProductRequest));
        CreateProductResponse createProductResponse = ProductMapper.toCreateProductResponse(product);
        createProductResponse.setStatus(Status.OK.getStatus());

        List<CategoryProductRel> categoryProductRelList = new ArrayList<>();

        for (UUID categoryId : categoryIdList) {
            categoryProductRelList.add(CategoryProductRel.builder().categoryId(categoryId).productId(product.getProductId()).build());
        }

        this.categoryProductRelRepository.saveAll(categoryProductRelList);

        return createProductResponse;
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return ProductMapper.toDtoList(productRepository.findAll());
    }

    @Override
    public ProductDto getProduct(UUID productId) {
        return ProductMapper.toDto(productRepository.findById(productId).orElseThrow(()-> new NoSuchElementException(ProductMessages.PRODUCT_NOT_FOUND)));

    }

    @Override
    public ResponseEntity<ProductDto> updateProduct(UUID productId, ProductDto productDto) {
        if (Objects.isNull(productId) || Objects.isNull(productDto.getProductId()) || !Objects.equals(productId,productDto.getProductId()))
            return ResponseEntity.badRequest().build();

        Optional<Product> existProduct = productRepository.findById(productId);
        if (existProduct.isEmpty())
            return ResponseEntity.notFound().build();

        final ProductDto updateProduct = this.save(productDto);

        if (Objects.nonNull(updateProduct))
            return ResponseEntity.ok(updateProduct);


        return ResponseEntity.internalServerError().build();
    }

    @Override
    public ProductDto updateProductAmount(UUID productId, UpdateProductAmountRequest request) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new NoSuchElementException(ProductMessages.PRODUCT_NOT_FOUND));
        product.setAmount(request.getAmount());
        productRepository.save(product);
        return ProductMapper.toDto(productRepository.save(product));
    }

    @Override
    public DeleteProductByIdResponse deleteProductById(UUID productId) {
        Product deletedProduct = this.productRepository.findByProductIdAndIsActive(productId, Boolean.TRUE).orElseThrow(()-> new ProductNotFoundException(ProductMessages.PRODUCT_NOT_FOUND));
        deletedProduct.setIsActive(Boolean.FALSE);
        this.productRepository.save(deletedProduct);

        List<CategoryProductRel> categoryProductRelList = this.categoryProductRelRepository.findAllByProductIdAndIsActive(productId, Boolean.TRUE);
        categoryProductRelList.forEach(categoryProductRel -> {
            categoryProductRel.setIsActive(Boolean.FALSE);
        });
        this.categoryProductRelRepository.saveAll(categoryProductRelList);

        return DeleteProductByIdResponse.builder().productId(productId).isActive(Boolean.FALSE).build();
    }

    @Override
    public List<ProductDto> getProductListByCategory(UUID categoryId) {

        List<UUID> productIdList = this.categoryProductRelRepository.findAllByCategoryIdAndIsActive(categoryId, Boolean.TRUE).stream().map(CategoryProductRel::getProductId).toList();

        if (productIdList.isEmpty())
            throw new ProductNotFoundException(ProductMessages.PRODUCT_NOT_FOUND);

        List<ProductDto> productDtoList = ProductMapper.toDtoList(this.productRepository.findAllByProductIdInAndIsActive(productIdList, Boolean.TRUE));

        if (productDtoList.isEmpty())
            throw new ProductNotFoundException(ProductMessages.PRODUCT_NOT_FOUND);

        return productDtoList;
    }

    @Override
    public void deleteProductListByProductListIdList(List<UUID> productIdList) {
        List<Product> productList = this.productRepository.findAllByProductIdInAndIsActive(productIdList, Boolean.TRUE);
        productList.forEach(product -> product.setIsActive(Boolean.FALSE));
        this.productRepository.saveAll(productList);

        List<DeletedProductMessage> deletedProductMessageList = new ArrayList<>();
        productList.stream().map(Product::getProductId).forEach(productId -> deletedProductMessageList.add(DeletedProductMessage.builder().productId(productId).build()));

        this.productProducer.sendToQueue(QueueName.DELETED_PRODUCT_QUEUE, deletedProductMessageList);
    }

    private ProductDto save (ProductDto productDto){
        Product product = ProductMapper.toEntity(productDto);
        product = productRepository.save(product);
        return ProductMapper.toDto(product);
    }

    private void validateCreateProductRequest(CreateProductRequest createProductRequest) {
        if (Objects.isNull(createProductRequest))
            throw new ProductBadRequestException(ExceptionMessages.BAD_REQUEST);

        if (Objects.isNull(createProductRequest.getCategoryList()) || createProductRequest.getCategoryList().isEmpty())
            throw new ProductBadRequestException(ProductMessages.CATEGORY_LIST_EMPTY);

        if (createProductRequest.getCategoryList().size() > GeneralValues.CATEGORY_MAX_SIZE)
            throw new ProductBadRequestException(ProductMessages.CATEGORY_LIST_SIZE_EXCEEDED);
    }
}
