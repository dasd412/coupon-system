package example.service;

import example.CouponSystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import example.repository.CouponRepository;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CouponSystemApplication.class)
public class ApplyServiceTest {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    public void 한번만응모(){
        applyService.apply(1L);

        long count = couponRepository.count();

        assertEquals(1, count);
    }
}