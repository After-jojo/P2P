package com.wangc.p2p.base.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author After拂晓
 */
@Configuration
public class MQConfig {
    @Bean
    public MessageConverter messageConverter(){
        // 对象类型 --> json字符串转换
        return new Jackson2JsonMessageConverter();
    }
}
