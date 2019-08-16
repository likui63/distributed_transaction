package com.chenghao.mq;

import com.alibaba.fastjson.JSONObject;
import com.chenghao.domain.Message;
import com.chenghao.service.OrderService;
import com.chenghao.vo.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @Author: chenghao
 * @Date: 2019/8/12 20:53
 **/
@Component
@Slf4j
public class MessageListener {

    @Autowired
    private RabbitmqSender rabbitmqSender;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = Constant.QUEUE_INFORM_ALIPAY)
    public void process(String param) {
        log.info("========开始消费信息=========" + param);
        Message message = JSONObject.parseObject(param, Message.class);
        Long execute = (Long)transactionTemplate.execute((TransactionCallback<Object>) transactionStatus -> {
            Long id = message.getId();
            int i = orderService.queryMessageCountByMessageId(id);
            if (i == 0) {
                orderService.updateAmount(message.getUserId(), message.getAmount());
                return orderService.addMessage(message);
            } else {
                log.info("转账异常，已经转过了");
            }
            return null;
        });
        //给支付宝回调，修改message 状态
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "ok");
        jsonObject.put("messageId", message.getId());
        log.info("=========发送转账成功信息到mq，供支付宝进行消费========" + jsonObject.toJSONString());
        rabbitmqSender.sendMessage(Constant.EXCHANGE_TOPIC_INFORM_YUEBAO,
                Constant.ROUTINGKEY_INFORM_YUEBAO, jsonObject);
    }

}
