package com.chenghao.service;

/**
 * @Author: chenghao
 * @Date: 2019/8/11 23:26
 **/
public interface OrderService {

    //修改支付宝金额
    void updateAmount(String userId, int amount);

    //修改message状态
    void updateMessage(String param);

}
