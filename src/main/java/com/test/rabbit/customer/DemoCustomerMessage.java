package com.test.rabbit.customer;

import com.heepay.common.util.JsonMapperUtil;
import com.test.core.dao.MqTestDao;
import com.test.core.entity.MqTest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Administrator on 2016/8/17.
 */
@Service
public class DemoCustomerMessage implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(DemoCustomerMessage.class);

    public void onMessage(Message message) {
        String receiveMsg = null;
        try {
            receiveMsg = new String(message.getBody());
            logger.info("{}, MessageVO: {}", Thread.currentThread().getId(), receiveMsg);
        } catch (Exception e) {
            logger.error("receive message has an error, {}", e);
        }
    }
}
