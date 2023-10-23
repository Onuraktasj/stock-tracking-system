package com.onuraktas.stocktrackingsystem.spring.spring;

import com.onuraktas.stocktrackingsystem.spring.config.CommonConfig;
import com.onuraktas.stocktrackingsystem.spring.config.TransactionManagementConfig;
import com.onuraktas.stocktrackingsystem.spring.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(
		value = {
				CommonConfig.class,
				TransactionManagementConfig.class,
				WebConfig.class
		}
)public class StockTrackingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockTrackingSystemApplication.class, args);
	}

}
