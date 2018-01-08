/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * http://www.suncd.com
 */
package com.example.democloudcomsumer.service;

import com.suncd.pms.xaschedule.tcc.XaParticipant;
import com.suncd.pms.xaschedule.tcc.XaScheduleService;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 功能：XXXX
 *
 * @author qust
 * @version 1.0 2017/12/1 16:12
 */
@Service
public class CallGxServiceFallbackImpl implements CallGxService {
    @Autowired
    private XaScheduleService xaScheduleService;

    @Autowired
    @Qualifier(value = "pmsConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Autowired
    private FeignTest feignTest;

    @Override
    public String getString() {
        //1. 数据库操作
        // ...


        //2. 记录参数
        Map<String,Object> map = new HashMap<>();
        map.put("arg1","test111");

        //3.交由分布式事务管理
        String transNo = "PMS00"+ UUID.randomUUID().toString().substring(0,5) +"20180104171801";
        XaParticipant xp = new XaParticipant(transNo,"cgx1",2,map,true);
        xaScheduleService.pushToXAPool(connectionFactory.createConnection(),xp,XaRollbackHandler.class);
        //调用第二个事务,传入事务编号
        feignTest.get(transNo);
        return "ohyes";
    }
}
