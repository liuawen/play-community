package com.awen.community.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-27 21:50
 */
@Configuration
//差不多都是Google的包什么导入的时候
public class KaptchaConfig {
    @Bean
    public Producer kaptchaProducer(){
        Properties properties = new Properties();

        properties.setProperty("Kaptcha.image.width","100");
        properties.setProperty("Kaptcha.image.height","40");
        properties.setProperty("Kaptcha.textproducer.font.size","40");
        //黑色
        properties.setProperty("kaptcha.textproducer.font.color", "0,0,0");
        //随机字符
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYAZ");
        //四个
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //噪声
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");

        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Config config =  new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }
}
