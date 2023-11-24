package com.cc.oj.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author cwc
 * @Date 2023/11/23 16:40
 * @Version 1.0
 */
@Component
@Slf4j
public  class SSeUtils {
    public static final Map<String, SseEmitter> sseMap = new ConcurrentHashMap<>();
    public static SseEmitter connect(String key){
        SseEmitter sseEmitter = new SseEmitter();
        sseMap.put(key, sseEmitter);
        return sseEmitter;
    }
    @Scheduled(fixedRate = 50000)
    public void sendMessage(){
        sseMap.forEach((k,v)->{
            try {
                v.send(k);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
