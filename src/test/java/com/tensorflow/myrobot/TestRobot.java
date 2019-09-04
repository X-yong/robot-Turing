package com.tensorflow.myrobot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: panda
 * @Date: 2019/5/17 17:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRobot {

    @Test
    public void testRobotCaht() {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        String url = "http://openapi.tuling123.com/openapi/api/v2";
        Map map = new HashMap<>();
        //输入类型:0-文本(默认)、1-图片、2-音频
        map.put("reqType",0);

        Map p = new HashMap<>();
        Map t = new HashMap();
        t.put("text","你叫什么");
        p.put("inputText",t);
        map.put("perception",p);

        Map u = new HashMap();
        u.put("apiKey","6937388ad799456f8ea66cc2be5ac10c");
        u.put("userId","446301");
        map.put("userInfo",u);


        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("charset","UTF-8");
        HttpEntity requestEntity = new HttpEntity(map, headers);
        ResponseEntity responseEntity =  restTemplate.postForEntity(url, requestEntity, String.class);
        System.out.println(responseEntity.getBody().toString());

    }
}
