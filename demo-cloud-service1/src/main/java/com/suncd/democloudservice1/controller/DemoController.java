/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * www.suncd.com
 */
package com.suncd.democloudservice1.controller;

import com.suncd.democloudservice1.service.XaRollbackHandler;
import com.suncd.pms.xaschedule.tcc.XaParticipant;
import com.suncd.pms.xaschedule.tcc.XaScheduleService;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 功能：XXXX
 *
 * @author qust
 * @version 1.0 2017/12/1 15:36
 */
@RestController
@RequestMapping("/test")
public class DemoController {
    @Autowired
    private XaScheduleService xaScheduleService;

    @Autowired
    @Qualifier(value = "pmsConnectionFactory")
    private ConnectionFactory connectionFactory;
    /**
     * 简单接口-供外部feign和zuul调用或定向
     * @return String
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String getString(@RequestParam("transNo") String transNo){
        //1. 数据库操作
        // ...


        //2. 记录参数
        Map<String,Object> map = new HashMap<>();
        map.put("arg2","test222");

        //3.交由分布式事务管理
        XaParticipant xp = new XaParticipant(transNo,"cgx2",2,map,false);
        xaScheduleService.pushToXAPool(connectionFactory.createConnection(),xp,XaRollbackHandler.class);
        return "cgx2";
    }
}
