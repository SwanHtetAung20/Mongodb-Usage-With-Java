package com.sha.e_commerce_system.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order")
@CompoundIndexes({
        @CompoundIndex(name = "customer_orderdate_idx", def = "{'customerId': 1, 'orderDate': -1}"),
        @CompoundIndex(name = "status_ordereddate_idx", def = "{'status': 1, 'orderedDate': -1}")
})
public class Order {

    @Id
    private String id;

    @Indexed
    private String customerId;

    private List<OrderItem> items;

    @Indexed
    private String orderedDate;

    @Indexed
    private String status;


    @Getter
    @Setter
    public static class OrderItem {
        private String productId;
        private int quantity;
    }
}
