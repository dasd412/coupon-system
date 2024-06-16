package example.service;

import example.domain.Coupon;
import org.springframework.stereotype.Service;
import example.repository.CouponRepository;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;


    public ApplyService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public void apply(Long userId){
        long count=couponRepository.count();

        if (count>100){
            return;
        }

        couponRepository.save(new Coupon(userId));
    }
}
