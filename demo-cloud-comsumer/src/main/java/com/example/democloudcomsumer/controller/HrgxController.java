/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * www.suncd.com
 */
package com.example.democloudcomsumer.controller;

import com.example.democloudcomsumer.service.CallGxService;
import com.example.democloudcomsumer.service.FeignTest;
import com.lensyn.common.utils.service.common.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能：XXXX
 *
 * @author qust
 * @version 1.0 2017/12/1 16:11
 */
@RestController
@RequestMapping("/oh")
public class HrgxController {
    @Autowired
    private CallGxService callGxService;
    @Autowired
    private FeignTest feignTest;
    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/yes",method = RequestMethod.GET)
    public String mc(){
        return callGxService.getString();
    }

    @RequestMapping(value = "/no",method = RequestMethod.GET)
    public String mcgx(){
        return feignTest.get("11111");
    }

    @RequestMapping(value = "/redis",method = RequestMethod.GET)
    public DemoEntity redis(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","gx");
        map.put("action","cc");
        DemoEntity dm = new DemoEntity("asduiaisd-qust",map);
        redisService.set(dm.getId(),dm);

        DemoEntity dmGet = redisService.get("asduiaisd-qust");
        return dmGet;
    }

    @RequestMapping(value = "/aop",method = RequestMethod.GET)
    public String aop(){
        return callGxService.test("ccggxxll".getBytes());
    }
}
