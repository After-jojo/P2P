package com.wangc.p2p.sms;

import com.wangc.p2p.sms.util.SmsProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author After拂晓
 * @date 2022年05月19日 20:48
 */
@SpringBootTest
@RunWith(SpringRunner.class)    //涉及spring上下文环境时需要
public class UtilsTest {
    @Test
    public void text(){
        System.out.println(SmsProperties.REGION_Id);
        System.out.println(SmsProperties.KEY_ID);
        System.out.println(SmsProperties.KEY_SECRET);
        System.out.println(SmsProperties.TEMPLATE_CODE);
        System.out.println(SmsProperties.SIGN_NAME);
    }

}
