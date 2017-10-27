package com.test.rabbit.producer;

import com.test.core.entity.ClearChannel;
import com.test.core.entity.ClearChannelRecold;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
public class ClearChannelRecoldProducer {
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

                            ClearChannelRecold clearChannelRecold = new ClearChannelRecold();
                            clearChannelRecold.setChannelCode(i*100+"");
                            clearChannelRecold.setChannelName("中国建设银行"+i);
                            clearChannelRecold.setChannelType("QUICKP");
                            clearChannelRecold.setPayTime("2017-03-01");
                            clearChannelRecold.setCurrency("156");
                            clearChannelRecold.setPaymentId("0000000");
                            clearChannelRecold.setTransNo("000000000000");
                            clearChannelRecold.setTransNoOld("00100");
                            clearChannelRecold.setSuccessAmount("12000");
                            clearChannelRecold.setChannelTime("2017-05-20");
                            clearChannelRecold.setSettleNo("2017-05-20");
                            clearChannelRecold.setSettleTimePlan("2017-05-20");
                            clearChannelRecold.setSettleCyc("0");
                            clearChannelRecold.setSettleBath("11");
                            clearChannelRecold.setCostTime("2017-05-20");
                            clearChannelRecold.setCostAmount("50");
                            clearChannelRecold.setCostWay("INDEDU");
                            clearChannelRecold.setCostSettleCyc("1");
                            clearChannelRecold.setCheckStatus("Y");
                            clearChannelRecold.setCheckFlg("QSQT");
                            clearChannelRecold.setSettleStatus("N");
                            clearChannelRecold.setFinishTime("2017-05-20");
                            clearChannelRecold.setTransType("DPTPAY");
                            clearChannelRecold.setBankSeq("111111111111");
                            clearChannelRecold.setBankName("中国农业银行");
                            clearChannelRecold.setBankCode("103");
                            clearChannelRecold.setChannelProvider("老王");
                            
                            demoQueueSender.sendDataToQueue(clearChannelRecold);
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
