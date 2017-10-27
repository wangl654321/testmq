/***
 * 
* 
* ��    ����
*
* �� �� �ߣ� xuangang
* ����ʱ�䣺  2017��5��23������5:55:07
* ����������
* 
* �� �� �ߣ�  
* �޸�ʱ�䣺 
* �޸������� 
* 
* �� �� �ߣ�
* ���ʱ�䣺
* ���������
*
 */
package com.test.rabbit.producer;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 * 
* 
* 描    述：
*
* 创 建 者： wangl
* 创建时间： 2017-05-24 03:04 PM
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
public class MerchantProducer {
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
	                        for (int i = 0; i<10; i++){
	                            String message =Thread.currentThread().getName()+" : hi rabbit."+i;

								Map<String, String> map = new HashMap<String, String>();
								map.put("merchantId", "100005");    //商户Id
								map.put("merchantType", "AA");      //商户类型
								map.put("productCode", "CP10");     //产品编码
								map.put("currency", "156");         //币种
								map.put("transNo", "30111100001491904529");    //交易订单号
								map.put("transNoOld", "");          //原交易订单号
								map.put("requestAmount", "100.0000");   //交易金额

								map.put("feeAmount", "1.0000");       //手续费
								map.put("payTime", "2017-05-24");    			  //清算日期，使用交易日期作为清算日
								map.put("successTime", "2017-05-24 11:38:43");     //交易支付日期(成功支付时间(yyyy-MM-dd hh:mi:ss) )
								map.put("transType", "PAYMNT");                   //交易类型
								map.put("successAmount", "100.0000");   //交易金额（银行返回的金额）
								map.put("feeWay", "INDEDU");            //手续费扣除方式
								map.put("settleCyc", "1");              //订单结算周期

								map.put("merchantOrderNo", "");         //商户交易订单号
								map.put("busiTime", "2017-04-11 19:21:14");   //交易发起时间
								map.put("agentName", "");   			//代理名称(新增)
								map.put("agentCode", "");               //代理商编码
								map.put("bankcardType", "SAVING");      //银行卡类型
								map.put("merchantCompany", "五常大米");    //商户名称
								map.put("accountNo", "1234567");   //账户编码(新增)
								map.put("createTime", "2017-05-24 11:37:43");  //订单创建日期

								map.put("payTime", "2017-05-24 11:37:53");    //支付发起时间(新增)
								map.put("payType", "QUICKP");  //支付类型(新增)


								map.put("paymentIdOld", "012345");               //原支付订单号
								map.put("channelCode", "");     		//通道编码
								map.put("paymentId", "1234567");        //支付单号
								map.put("costWay", "INDEDU");           //手续费扣除方式
								map.put("bankId", "308");           //银行编码
								map.put("bankName", "招商银行");     //银行名称
								map.put("bankSerialNo", "000011862819");    //银行流水
								map.put("channelProvider", "统统付");    //通道提供者

								//demoQueueSender.sendDataToQueue(map);
								//demoQueueSender.sendDataToQueue(map);
								demoQueueSender.sendClearMerchant(map);

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
