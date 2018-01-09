/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * http://www.suncd.com
 */
package com.example.democloudcomsumer.service;

import com.suncd.pms.xaschedule.annotation.XaUser;
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
        //1. 业务操作
        // ...

        //2. 事务批号
        String transNo = "PMS00"+ UUID.randomUUID().toString().substring(0,5) +"20180104171801";

        //3. 中间参数
        Map<String,Object> map = new HashMap<>();
        map.put("arg1","testcgx11111:"+transNo);
        XaParticipant xp = new XaParticipant(transNo,"cgx1",2,10,map,true);

        //4. 加入分布式事务管理
        xaScheduleService.pushToXAPool(connectionFactory.createConnection(),xp,XaRollbackHandler.class);

        //5. 调用其它业务,传入事务编号
        feignTest.get(transNo);
        return "ohyes";
    }

    @XaUser(xaValues = {"asdasd"})
    public String test(byte[] s){
        return new String(s);
    }
}
