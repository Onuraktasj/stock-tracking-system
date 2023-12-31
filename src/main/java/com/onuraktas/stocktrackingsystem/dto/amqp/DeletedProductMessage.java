package com.onuraktas.stocktrackingsystem.dto.amqp;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class DeletedProductMessage implements Serializable {

    private UUID productId;
}
