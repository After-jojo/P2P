package com.wangc.p2p.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author After拂晓
 * @date 2022年05月19日 20:27
 */
@SpringBootApplication
@ComponentScan({"com.wangc.p2p"})
@EnableFeignClients    //服务消费者
public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class, args);
    }
}
