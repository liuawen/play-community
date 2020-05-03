package com.awen.community;

import com.awen.community.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-05-01 10:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class TransactionTests {

    @Autowired
    private HelloService helloService;

    @Test
    public void testSave1() {
        Object obj = helloService.save1();
        System.out.println(obj);
    }

    @Test
    public void testSave2() {
        Object obj = helloService.save2();
        System.out.println(obj);
    }

}
