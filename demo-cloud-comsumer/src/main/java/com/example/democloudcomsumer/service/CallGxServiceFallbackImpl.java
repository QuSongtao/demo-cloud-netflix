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
 * @version 1.0 2017/12/1 16:12
 */
@Service
public class CallGxServiceFallbackImpl implements CallGxService {
    @Override
    public String getString() {
        return "error";
    }
}
