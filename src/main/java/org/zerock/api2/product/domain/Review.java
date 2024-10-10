package org.zerock.api2.product.domain;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@ToString(exclude = "product")
@Table(name = "tbl_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String reviewer;

    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
