/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * http://www.suncd.com
 */
package com.example.democloudcomsumer.service;

import com.google.gson.Gson;
import com.suncd.pms.xaschedule.tcc.XaRollbackHandle;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 功能：XXXX
 *
 * @author qust
 * @version 1.0 2018/1/4 17:09
 */
@Component
public class XaRollbackHandler implements XaRollbackHandle {
    @Override
    public boolean rollback(Map<String, Object> map) {
        System.out.println("cgx1进入回滚!"+new Gson().toJson(map));
        return true;
    }
}
