package com.test.rabbit.producer;

import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.core.entity.ClearChannel;

/**
 * 
 * 
 * 描    述：
 *
 * 创 建 者：chenyanming
 * 创建时间： 2016年9月13日下午5:39:01 
 * 创建描述：
 * 
 * 修 改 者：  
 * 修改时间：
 * 修改描述： 
 * 
 * 审 核 者：
 * 审核时间：
 * 审核描述：
 *
 */
public class MyJobProducer {
	private static final String CORE_WORK_XML = "rabbitmq-producer.xml";

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JobEntranceProducer.class);

    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CORE_WORK_XML)) {
            context.registerShutdownHook();
            context.start();

            for (int i = 0; i < 1; i++){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DemoQueueSender demoQueueSender = context.getBean("demoQueueSender", DemoQueueSender.class);
                        for (int i = 0; i<100; i++){
                            String message =Thread.currentThread().getName()+" : hi rabbit."+i;
                            ClearChannel mqTest = new ClearChannel();
                            mqTest.setChannelCode("1000");
                            mqTest.setCurrency("156");
                            mqTest.setPayTime("2016-09-13");
                            mqTest.setPaymentId("100001");
                            mqTest.setPaymentIdOld("100000");
                            mqTest.setTransNo("jiaoyidan01");
                            mqTest.setTransNoOld("jiaoyidanold01");
                            mqTest.setSuccessAmount("10000");
                            
                            demoQueueSender.sendDataToQueue(mqTest);
                            logger.info(message);
                            try {
                                Thread.currentThread().sleep(200l);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }

            logger.info("spring 容器启动成功! ");

            Object lock = new Object();
            synchronized (lock) {
                try {
                    while (true)
                        lock.wait();
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            logger.error("spring 容器启动错误", e);
        }

    }
}
