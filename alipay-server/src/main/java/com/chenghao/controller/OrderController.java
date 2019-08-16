package com.chenghao.controller;

import com.chenghao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenghao
 * @Date: 2019/8/11 23:29
 **/
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 转账
     *
     * @param userId
     * @param amount
     * @return
     */
    @RequestMapping("/transfer")
    public String transfer(String userId, int amount) {
        try {
            orderService.updateAmount(userId, amount);
        } catch (Exception e) {
            return "error";
        }
        return "success";
    }

    /**
     * 进行回调
     *
     * @param param
     * @return
     */
    @RequestMapping("/callBack")
    public String callBack(String param) {
        try {

        } catch (Exception e) {
            return "error";
        }
        return "success";
    }
}
