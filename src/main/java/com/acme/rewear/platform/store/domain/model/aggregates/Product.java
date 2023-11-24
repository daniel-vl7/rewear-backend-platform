package com.acme.rewear.platform.store.domain.model.aggregates;

import com.acme.rewear.platform.store.domain.model.valueobjects.ProductDescription;
import com.acme.rewear.platform.store.domain.model.valueobjects.ProductDetail;
import com.acme.rewear.platform.store.domain.model.valueobjects.ProductName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Product extends AbstractAggregateRoot<Product> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Embedded
    private ProductName name;

    private String size;
    private String state;
    private String price;
    private String color;
    private String urlToImg;

    @Embedded
    private ProductDescription description;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Product(String name, String description, String size, String state, String price, String color, String urlToImg) {
        this.name = new ProductName(name);
        this.description = new ProductDescription(description);
        this.size = size;
        this.state = state;
        this.price = price;
        this.color = color;
        this.urlToImg = urlToImg;
    }

    public Product() {

    }

    public void updateName(String name) {
        this.name = new ProductName(name);
    }

    public void updateDescription(String description) {
        this.description = new ProductDescription(description);
    }


    public String getProductName() {
        return name.getProductName();
    }

    public String getProductDescription() {
        return description.getProductDescription();
    }


}
