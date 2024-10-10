package org.zerock.api2.product.repository.search;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.api2.product.domain.Product;
import org.zerock.api2.product.domain.ProductStatus;
import org.zerock.api2.product.domain.QProduct;
import org.zerock.api2.product.domain.QReview;

import java.util.List;

@Log4j2
public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {

    public ProductSearchImpl() {
        super(Product.class);
    }

    @Override
    public Page<Product> list1(Pageable pageable) {

        QProduct product = QProduct.product;
        JPQLQuery<Product> query = from(product);
        query.where(product.status.eq(ProductStatus.SALE));
        query.where(product.pno.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Product> list = query.fetch();
        long total = query.fetchCount();

        return new PageImpl<>(list, pageable, total);
    }

    @Override
    public Page<Product> list2(String keyword, ProductStatus status, Pageable pageable) {

        QProduct product = QProduct.product;
        JPQLQuery<Product> query = from(product);
        query.where(product.status.eq(ProductStatus.SALE));
        query.where(product.pno.gt(0L));

        if(keyword != null){
            query.where(product.pname.like("%"+keyword+"%"));
        }
        if(status != null) {
            query.where(product.status.eq(status));
        }

        this.getQuerydsl().applyPagination(pageable, query);

        List<Product> list = query.fetch();
        long total = query.fetchCount();

        return null;
    }

    @Override
    public Page<Product> listWithReplyCount(Pageable pageable) {

        QProduct product = QProduct.product;
        QReview review = QReview.review;

        JPQLQuery<Product> query = from(product);

        query.leftJoin(review).on(review.product.eq(product));
        query.groupBy(product);

        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<Tuple> tupleJPQLQuery = query.select
                (product.pno, product.pname, product.price, review.count(), review.score.avg().coalesce(0.0));

        log.info("============================");
        log.info(tupleJPQLQuery);

        List<Tuple> tupleList = tupleJPQLQuery.fetch();

        Long total = tupleJPQLQuery.fetchCount();

        return null;
    }
}
