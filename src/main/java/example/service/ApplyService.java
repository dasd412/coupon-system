package example.service;

import example.domain.Coupon;
import example.repository.CouponCountRepository;
import org.springframework.stereotype.Service;
import example.repository.CouponRepository;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;


    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
    }

    public void apply(Long userId){
        // 레디스는 싱글 스레드 기반으로 구현됬음.
        Long count =couponCountRepository.increment();

        if (count>100){
            return;
        }

        couponRepository.save(new Coupon(userId));
    }
}
