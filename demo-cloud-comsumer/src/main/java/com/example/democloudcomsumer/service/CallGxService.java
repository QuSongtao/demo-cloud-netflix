/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * www.suncd.com
 */
package com.example.democloudcomsumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 功能：XXXX
 *
 * @author qust
 * @version 1.0 2017/12/1 16:09
 */
@FeignClient(name = "client-service" , fallback = CallGxServiceFallbackImpl.class)
public interface CallGxService {
    @RequestMapping(value = "/test/get",method = RequestMethod.GET)
    String getString();
}
