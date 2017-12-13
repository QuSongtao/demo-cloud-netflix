/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * www.suncd.com
 */
package com.example.democloudservice2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能：XXXX
 *
 * @author qust
 * @version 1.0 2017/12/1 15:36
 */
@RestController
@RequestMapping("/test")
public class DemoController {
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String getString(){
        return "hrgx";
    }
}
