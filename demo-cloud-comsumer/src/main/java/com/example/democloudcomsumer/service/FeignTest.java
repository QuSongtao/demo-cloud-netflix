/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * http://www.suncd.com
 */
package com.example.democloudcomsumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 功能：XXXX
 *
 * @author qust
 * @version 1.0 2018/1/5 13:57
 */
@FeignClient(name = "client-service", fallback = FeignTestFallback.class)
public interface FeignTest {
    @RequestMapping(value = "/test/get",method = RequestMethod.GET)
    String get(@RequestParam("transNo") String transNo);
}
