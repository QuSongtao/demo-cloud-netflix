/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * http://www.suncd.com
 */
package com.suncd.democloudservice1.system;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

/**
 * 功能：MQ配置类
 *
 * @author qust
 * @version 1.0 2018/1/2 13:52
 */
@Configuration
public class MQConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(MQConfig.class);

    @Value("${spring.rabbitmq.addresses:192.168.2.200:5672}")
    private String addresses; //地址:格式为IP:port,集群环境每个IP:port之间用逗号分隔

    @Value("${spring.rabbitmq.host:192.168.2.200}")
    private String host; //RabbitMQ主机地址

    @Value("${spring.rabbitmq.port:5672}")
    private int port; //RabbitMQ主机端口

    @Value("${spring.rabbitmq.username:admin}")
    private String username; //连接RabbitMQ的用户名

    @Value("${spring.rabbitmq.password:admin}")
    private String password; //连接RabbitMQ的密码

    @Value("${pms.rabbitmq.authVhost:usercenter-api-service}")
    private String authVhost; //权限的虚拟主机

    @Value("${pms.rabbitmq.pmsVhost:pms-vhost}")
    private String pmsVhost; //权限的虚拟主机

    @Value("${spring.application.name:}")
    private String applicationName;

    /* 容器工厂 - 权限 */
    @Bean(name = "authContainerFactory")
    @Primary
    public SimpleRabbitListenerContainerFactory authContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("authConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConcurrentConsumers(1);//设置并发
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    /* MQ连接工厂 - 权限 */
    @Bean(name = "authConnectionFactory")
    @Primary
    public ConnectionFactory authConnectionFactory() {
        return initConnFactory(host, port, addresses, username, password, authVhost);
    }

    /* Template - 权限 */
    @Bean(name = "authRabbitTemplate")
    @Primary
    public RabbitTemplate authRabbitTemplate() {
        return new RabbitTemplate(authConnectionFactory());
    }


    /* 容器工厂 - pms */
    @Bean(name = "pmsContainerFactory")
    public SimpleRabbitListenerContainerFactory pmsContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("pmsConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConcurrentConsumers(1);//设置并发
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    /* MQ连接工厂 - pms */
    @Bean(name = "pmsConnectionFactory")
    public ConnectionFactory pmsConnectionFactory() {
        return initConnFactory(host, port, addresses, username, password, pmsVhost);
    }

    /* Template - pms */
    @Bean(name = "pmsRabbitTemplate")
    public RabbitTemplate pmsRabbitTemplate() {
        return new RabbitTemplate(pmsConnectionFactory());
    }

    /**
     * 私有方法-创建连接工厂
     *
     * @param host      主机
     * @param port      端口
     * @param addresses 地址,格式如 [ip1]:[port1],[ip2]:[port2],[ip3]:[port3]
     * @param username  RabbitMQ用户名
     * @param password  RabbitMQ密码
     * @param vhost     虚拟主机
     * @return ConnectionFactory
     */
    private ConnectionFactory initConnFactory(String host, int port, String addresses, String username, String password, String vhost) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        if (!StringUtils.isEmpty(host)) {
            connectionFactory.setHost(host);
            connectionFactory.setPort(port);
        } else {
            connectionFactory.setAddresses(addresses);
        }
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(vhost);
        return connectionFactory;
    }

    /**
     * 初始化队列
     *
     * @param connectionFactory mq连接工厂
     * @return string
     */
    @Bean("xaQueues")
    public String xaQueue(@Qualifier("pmsConnectionFactory") ConnectionFactory connectionFactory) {
        Connection conn = connectionFactory.createConnection();
        Channel channel = conn.createChannel(true);
        try {
            channel.queueDeclare("xa."+applicationName+".q", true, false, false,null); // 参与者队列
            channel.queueDeclare("common.trans.q", true, false, false,null); // 事务主队列
            channel.close();
            conn.close();
        } catch (Exception e) {
            LOGGER.error("初始化队列失败",e);
        }
        LOGGER.info("创建分布式事务参与者队列OK");
        return "xaQueues";
    }
}
