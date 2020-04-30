package com.awen.community;

import com.awen.community.util.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-30 17:24
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTests {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter() {
        //这是发的人比较初级的
        String text = "I'm a piece of shit，我就是傻逼呀，我个笨蛋，" + "留下不学无术的眼泪!!!!";
        text = sensitiveFilter.filter(text);
        System.out.println(text);//I'm a piece of ***，我就是***呀，我个***，留下不学无术的眼泪!!!!

        text = "I'm a piece of ☆sh☆it，我就是☆傻☆☆逼☆呀，@我个☆笨☆蛋，" +  "留下不学无术的眼泪!!!";
        text = sensitiveFilter.filter(text);
        System.out.println(text);//I'm a piece of ☆***，我就是☆***☆呀，@我个☆***，留下不学无术的眼泪!!!
    }
//    @Test
//    public void test1(){
//        Set<String> sensitiveWords=new HashSet<>();
//        sensitiveWords.add("shit");
//        sensitiveWords.add("傻逼");
//        sensitiveWords.add("笨蛋");
//        String text="你是小傻逼啊";
//        for(String sensitiveWord:sensitiveWords){
//            if(text.contains(sensitiveWord)){
//                System.out.println("输入的文本存在敏感词。——"+sensitiveWord);
//                break;
//            }
//        }
//    }


}
