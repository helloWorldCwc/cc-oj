package com.cc.oj.controller;

import com.cc.oj.utils.SSeUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author cwc
 * @Date 2023/11/23 16:18
 * @Version 1.0
 */
@RestController
@RequestMapping("/sse")
public class SSEController {
    private static final Map<String, SseEmitter> sseMap = new ConcurrentHashMap<>();
    @GetMapping("{id}")
    public SseEmitter sseEmitterTest(@PathVariable("id") String id){
        return SSeUtils.connect(id);
    }
}
