/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * www.suncd.com
 */
package com.example.democloudcomsumer.controller;

import com.example.democloudcomsumer.service.CallGxService;
import com.example.democloudcomsumer.service.FeignTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/yes",method = RequestMethod.GET)
    public String mc(){
        return callGxService.getString();
    }

    @RequestMapping(value = "/no",method = RequestMethod.GET)
    public String mcgx(){
        return feignTest.get("11111");
    }
}
