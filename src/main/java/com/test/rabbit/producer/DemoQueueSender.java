package com.test.rabbit.producer;

import com.heepay.common.util.JsonMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/8/17.
 */
@Component
public class DemoQueueSender {

    private Logger logger = LoggerFactory.getLogger(DemoQueueSender.class);

    @Autowired
    private AmqpTemplate ltestAmqpTemplate;

    @Autowired
    private AmqpTemplate hyBillingClearMerchantqueueAmqpTemplate;
    
    @Autowired
    private AmqpTemplate hyBillingClearChannelqueueAmqpTemplate;
    @Autowired
    private AmqpTemplate hyBillingChannelResultqueueAmqpTemplate;


    public void sendDataToQueue(String param) {
        logger.debug(" ---sendStringDataToQueue---- {}", param);
        hyBillingChannelResultqueueAmqpTemplate.convertAndSend(param);
    }

    public void sendDataToQueue(Object param) {
        String json = JsonMapperUtil.nonDefaultMapper().toJson(param);
        logger.debug(" ---sendStringDataToQueue---- {}", json);
        hyBillingClearChannelqueueAmqpTemplate.convertAndSend(json);
    }


    public void sendClearMerchant(Object param) {
        String json = JsonMapperUtil.nonDefaultMapper().toJson(param);
        logger.debug(" ---sendStringDataToQueue---- {}", json);
        hyBillingClearMerchantqueueAmqpTemplate.convertAndSend(json);
    }
}
