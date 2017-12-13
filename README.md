# demo-cloud-netflix
Spring-Cloud-Netflix标准示例,注册中心:EurekaServer,路由网关:zuul,软负载:ribbon,HA重试机制:zuul,feign

## 1.注册中心
项目:demo-cloud-discover

## 2.路由网关
项目:demo-cloud-zuul
提供url请求路由配置

## 3.服务间调用feign示例
项目:demo-cloud-comsumer,该项目中 `/oh/yes` 请求访问 `demo-cloud-service1` 和 `demo-cloud-service2` 中接口

## 4.示例服务2个
项目:demo-cloud-service1
项目:demo-cloud-service2
供zuul,或者feign调用


