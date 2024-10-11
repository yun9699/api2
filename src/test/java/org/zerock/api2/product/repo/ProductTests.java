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
import org.zerock.api2.common.dto.PageRequestDTO;
import org.zerock.api2.common.dto.PageResponseDTO;
import org.zerock.api2.product.domain.Product;
import org.zerock.api2.product.dto.ProductListDTO;
import org.zerock.api2.product.repository.ProductRepository;



@DataJpaTest
@Log4j2
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductTests {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductRepository 1;

    @Test
    public void testInsert() {

        Product product = Product.builder()
                .pname("Test Product")
                .price(3000)
                .build();
        product.addTag("AAA");
        product.addTag("BBB");
        product.addTag("CCC");

        productRepository.save(product);

    }

    @Test
    @Transactional
    public void testList1() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("pno").descending());

        ProductRepository.list1(pageable);

    }

    @Test
    public void testListWithReplyCount() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("pno").descending());

        ProductRepository.listWithReplyCount(pageable);
    }

    @Test
    public void testRead() {

        log.info("00000000");
        log.info(ProductRepository.read(15L));
    }

    @Test
    public void testDTOList() {
        PageRequestDTO requestDTO = PageRequestDTO.builder().build();
        PageResponseDTO<ProductListDTO> result
                = ProductRepository.list(requestDTO);

        log.info(result);
    }
}
