package com.cc.oj.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author cwc
 * @Date 2023/11/20 19:07
 * @Version 1.0
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    @PostMapping
    public String demo(@RequestParam(value = "id", required = true) String id) {
        return "ok:" + id;
    }
}
