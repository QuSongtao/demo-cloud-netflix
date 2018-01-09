/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * http://www.suncd.com
 */
package com.example.democloudcomsumer.service.mq;

import com.suncd.pms.xaschedule.annotation.XaListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 功能：XXXX
 *
 * @author qust
 * @version 1.0 2017/12/28 15:09
 */
@Component
public class MqListener {

    /**
     * 侦听队列定义为xa.[应用名称].q,监听事务调度的消息
     * 进行回滚处理
     */
    @RabbitListener(queues = "xa.${spring.application.name}.q",containerFactory = "pmsContainerFactory")
    @XaListener(xaConnectionFactory = "pmsConnectionFactory")
    public void consumeMsg(byte[] msgBody){

    }
}
