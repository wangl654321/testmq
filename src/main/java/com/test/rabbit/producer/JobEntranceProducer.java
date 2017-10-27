/**
 *
 */
package com.test.rabbit.producer;

import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.core.entity.ChannelResult;
import com.test.core.entity.ClearChannel;
import com.test.core.entity.ClearMerchant;


public class JobEntranceProducer {

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
                        for (int i = 0; i<10000; i++){
                            String message =Thread.currentThread().getName()+" : hi rabbit."+i;
                            
                            // 生产通道侧清算消息
                            ClearChannel mqTest = new ClearChannel();
                            mqTest.setChannelCode("402DIRCONQUICKPSAVINGPUBLIC");
                            mqTest.setCurrency("156");
                            mqTest.setPayTime("2016-10-10 12:12:12");
                            mqTest.setPaymentId(i + 14000 +"");
                            //mqTest.setPaymentIdOld(null);
                            mqTest.setTransNo("cym999");
                            //mqTest.setTransNoOld(null);
                            mqTest.setSuccessAmount("100");
                            mqTest.setTransType("WZDRAW");
                            
                            // 生产用户侧清算消息
                            /*ClearMerchant mqTest = new ClearMerchant();
                            mqTest.setMerchantId("9999");
                            mqTest.setMerchantType("1");
                            mqTest.setProductCode("PF");
                            mqTest.setCurrency("156");
                            mqTest.setFee("10");
                            mqTest.setRequestAmount("99");
                            mqTest.setTransNo("9999");
                            mqTest.setTransNoOld("8888");*/
                            
                            /*ChannelResult mqTest = new ChannelResult();
                            mqTest.setStatus("1");
                            mqTest.setSettleBath("19201609162348170");*/
                            
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
