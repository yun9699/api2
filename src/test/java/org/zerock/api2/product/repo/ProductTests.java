package org.zerock.api2.product.repo;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.api2.product.repository.ProductRepository;



@DataJpaTest
@Log4j2
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    public void testList1() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("pno").descending());

        productRepository.list1(pageable);

    }

    @Test
    public void testListWithReplyCount() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("pno").descending());

        productRepository.listWithReplyCount(pageable);
    }

    @Test
    public void testRead() {

        log.info("00000000");
        log.info(productRepository.read(15L));
    }
}
