/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * http://www.suncd.com
 */
package com.example.democloudcomsumer.controller;

import java.io.Serializable;
import java.util.Map;

/**
 * 功能：XXXX
 *
 * @author qust
 * @version 1.0 2018/1/8 11:03
 */
public class DemoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private Map<String,Object> data;

    public DemoEntity(){}
    public DemoEntity(String id,Map<String,Object> map){
        super();
        this.id = id;
        this.data = map;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
