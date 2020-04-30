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
        //发的人比较初级
        String text = "这里可以赌博,可以嫖娼,可以吸毒,可以开票,哈哈哈!";
        text = sensitiveFilter.filter(text);
        System.out.println(text);//这里可以***,可以***,可以***,可以***,哈哈哈!

        text = "这里可以☆赌☆博☆,可以☆嫖☆娼☆,可以☆吸☆毒☆,可以☆开☆票☆,哈哈哈!";
        text = sensitiveFilter.filter(text);
        System.out.println(text);//这里可以☆***☆,可以☆***☆,可以☆***☆,可以☆***☆,哈哈哈!
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
