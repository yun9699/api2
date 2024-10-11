import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.api2.common.dto.PageRequestDTO;
import org.zerock.api2.common.dto.PageResponseDTO;
import org.zerock.api2.product.domain.Product;
import org.zerock.api2.product.dto.ProductListDTO;
import org.zerock.api2.product.repository.ProductRepository;

import java.awt.print.Pageable;

@DataJpaTest
@Log4j2
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testInsert() {
        Product product = Product.builder()
                .pname("Test Product")
                .price(3000)
                .build();
        // product.addTag("AAA");
        // product.addTag("BBB");
        // product.addTag("CCC");

        productRepository.save(product);
    }

    @Test
    @Transactional
    public void testList1() {
        Pageable pageable = PageRequest.of(0,10, Sort.by("pno").descending());
        productRepository.list1(pageable);  // 수정 완료
    }

    @Test
    public void testListWithReplyCount() {
        Pageable pageable = PageRequest.of(0,10, Sort.by("pno").descending());
        productRepository.listWithReplyCount(pageable);  // 수정 완료
    }

    @Test
    public void testRead() {
        log.info("00000000");
        log.info(productRepository.read(15L));  // 수정 완료
    }

    @Test
    public void testDTOList() {
        PageRequestDTO requestDTO = PageRequestDTO.builder().build();
        PageResponseDTO<ProductListDTO> result = productRepository.list(requestDTO);  // 수정 완료
        log.info(result);
    }
}
