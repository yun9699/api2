package org.zerock.api2.product.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
//빌더 사용 안했음 여기에
public class ReviewImage {

    private int ord;
    private String filename;


}
