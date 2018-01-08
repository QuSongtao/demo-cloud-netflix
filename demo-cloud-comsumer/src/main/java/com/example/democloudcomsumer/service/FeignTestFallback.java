/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * http://www.suncd.com
 */
package com.example.democloudcomsumer.service;

import org.springframework.stereotype.Service;

/**
 * 功能：XXXX
 *
 * @author qust
 * @version 1.0 2018/1/5 13:57
 */
@Service
public class FeignTestFallback implements FeignTest {
    @Override
    public String get(String transNo) {
        return "cgx_fall";
    }
}
