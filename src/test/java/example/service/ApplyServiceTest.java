package example.service;

import example.CouponSystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import example.repository.CouponRepository;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @Test
    public void  여러번응모() throws InterruptedException {
        int threadCount=100;

        ExecutorService executorService= Executors.newFixedThreadPool(32);

        //다른 스레드가 하는 작업을 기다리도록 도와주는 클래스
        CountDownLatch latch=new CountDownLatch(threadCount);

        for (int i = 0; i <threadCount ; i++) {
            long userId=i;

            executorService.submit(()->{
                try{
                    applyService.apply(userId);
                }finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        long count=couponRepository.count();

        assertEquals(count,100);

    }
}