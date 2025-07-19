package com.sha.e_commerce_system.repository;

import com.sha.e_commerce_system.domain.Order;
import com.sha.e_commerce_system.domain.Product;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByCustomerId(String id);

    List<Order> findByCustomerIdAndOrderedDateBetween(String customerId, String start, String end);

    List<Order> findByStatusAndOrderedDateBetween(String status, String start, String end);

    List<Order> findByStatus(String status);

    @Aggregation(pipeline = {
            "{ $match: { customerId: ?0 } }",
            "{ $unwind: '$items' }",
            "{ $lookup: { from: 'product', localField: 'items.productId', foreignField: '_id', as: 'productInfo' } }",
            "{ $unwind: '$productInfo' }",
            "{ $group: { _id: '$_id', orderedDate: { $first: '$orderedDate' }, customerId: { $first: '$customerId' }, items: { $push: { product: '$productInfo', quantity: '$items.quantity' } } } }"
    })
    List<OrderWithProducts> findOrdersWithProductDetailsByCustomerId(String customerId);

    interface OrderWithProducts {
        String getId();

        String getOrderedDate();

        String getCustomerId();

        List<ItemDetail> getItems();

        interface ItemDetail {
            Product getProduct();

            int getQuantity();
        }
    }
}
