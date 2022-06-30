package com.buba.springcloud.controller;


import com.buba.springcloud.pojo.CommonResult;
import com.buba.springcloud.pojo.Payment;
import com.buba.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class OrderFeignController {

    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){

        return paymentService.queryById(id);
    }

//    @GetMapping("/payment/feign/timeout")
//    public String PaymentFeginTimeout() throws InterruptedException{
//
//
//        TimeUnit.SECONDS.sleep(3);;
//        return serverPort;
//    }

    @GetMapping("/consumer/feign/timeout")
    public String PaymentFeignTimeOut() throws InterruptedException{
        return paymentService.PaymentFeignTimeOut();
    }

}
