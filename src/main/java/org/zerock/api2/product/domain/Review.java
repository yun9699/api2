package org.zerock.api2.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@ToString(exclude = "product")
@Table(name = "tbl_review", indexes = {
        @Index(name = "idx_review_product", columnList = "product_pno")
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String reviewer;

    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ElementCollection
    @CollectionTable(name = "tbl_review_img")
    private Set<ContentImage> images = new HashSet<>();

    public void changeImages(Set<ContentImage> images) {
        this.images = images;
    }
}
