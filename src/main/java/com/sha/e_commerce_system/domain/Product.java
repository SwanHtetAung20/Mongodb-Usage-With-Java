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
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
@CompoundIndexes({
        @CompoundIndex(name = "category_price_idx", def = "{'category': 1, 'price': -1}"),
        @CompoundIndex(name = "name_tags_idx", def = "{'name': 1, 'tags': 1}")
})
public class Product {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    @Indexed
    private String category;

    private List<String> tags;

    @Indexed
    private BigDecimal price;

    @Field("is_available")
    private boolean available;

    @Indexed
    private String createdAt;
}
