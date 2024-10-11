package org.zerock.api2.product.repository.search;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.api2.product.domain.QContentImage;
import org.zerock.api2.product.domain.QReview;
import org.zerock.api2.product.domain.Review;

public class ReviewSearchImpl extends QuerydslRepositorySupport implements ReviewSearch {

    public ReviewSearchImpl() {
        super(Review.class);
    }

    @Override
    public Page<Review> listByProduct(Long pno, Pageable pageable) {

        QReview review = QReview.review;
        QContentImage image = QContentImage.contentImage;

        JPQLQuery<Review> query = from(review);
        query.leftJoin(review.images, image);
        query.where(review.product.pno.eq(pno));
        query.where(image.ord.eq(0));

        this.getQuerydsl().applyPagination(pageable, query);

        query.fetch(); // 쿼리돌아가는지 확인




        return null;
    }
}
