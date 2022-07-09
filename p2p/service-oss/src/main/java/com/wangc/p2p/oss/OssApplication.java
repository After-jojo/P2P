package com.wangc.p2p.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author After拂晓
 * @date 2022年05月20日 20:54
 */
@SpringBootApplication
@ComponentScan({"com.wangc.p2p"})
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}
