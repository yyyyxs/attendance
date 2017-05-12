package com.jmhz.device.util.timertask;

/*import com.jasson.im.api.MOItem;*/
import com.jmhz.device.Constants;
import com.jmhz.device.service.SMSSentServiceI;
import com.jmhz.device.service.impl.SmOriginalAppealServiceImpl;
import com.jmhz.device.model.Tsmoriginalappeal;
import com.jmhz.device.service.SmOriginalAppealServiceI;
import com.jmhz.device.service.impl.SMSSentServiceImpl;
import com.jmhz.device.util.SMSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by 锋情 on 2014-08-18.
 */
/*public class ReceiveSMTask extends TimerTask {

    @Autowired
    private SmOriginalAppealServiceI smOriginalAppealService;
    @Autowired
    private SMSSentServiceI smsSentService;
    private String mobileString = "";
    private StringBuilder mobileBuilder = null;
    private static Logger logger = LoggerFactory.getLogger(ReceiveSMTask.class);
    //    Tsmoriginalappeal tsmoriginalappeal = null;
    MOItem[] moItems = null;

    public ReceiveSMTask() {
        smOriginalAppealService = new SmOriginalAppealServiceImpl();
        smsSentService = new SMSSentServiceImpl();
        mobileBuilder = new StringBuilder();

    }

    @Override
    public void run() {
//        logger.error("Receive SM Start .....");
        moItems = SMSUtils.receiveSM();
        if (moItems == null) {
            logger.error("接收失败！");
            return;
        }

        if (moItems.length > 0) {
            int count = 1;
            String smContent = "";
            for (MOItem m : moItems) {
                try {
                    smContent = new String(m.getContent().getBytes(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    logger.error("接收数据失败: " + smContent + e.getMessage());
                }

                Tsmoriginalappeal tsmoriginalappeal = new Tsmoriginalappeal(m.getMobile(), smContent, new Date());
                //原始诉求添加成功
                if (smOriginalAppealService.add(tsmoriginalappeal)) {
                    mobileBuilder.append(m.getMobile() + ";");
                }else {
                    logger.error("getContent: " + smContent+"getMobile: " + m.getMobile()+"failure!" );
                }
                count++;
            }
            if (!mobileBuilder.toString().isEmpty()) {
                mobileString = mobileBuilder.toString().substring(0, mobileBuilder.toString().length() - 1);
                String[] mobiles = mobileString.split(";");
                mobileBuilder.delete(0, mobileBuilder.length());
                int result = SMSUtils.sendSM(mobiles, "您的诉求已经收到，我们会尽快处理！", new Long(22));
                if (result == SMSUtils.apiClient.IMAPI_SUCC) {
                    //添加发送记录 rar 当收到诉求后回复本短信
                    for (String tel :mobiles){
                        try{
                            smsSentService.addJDBC(Constants.SMS_TEMPLATE_TYPES.RAR, tel, "您的诉求已经收到，我们会尽快处理！");
                        }catch (Exception e){
                            logger.error("写入数据库失败:"+e.getMessage());
                        }
                    }

                } else if (result == SMSUtils.apiClient.IMAPI_INIT_ERR){
                    logger.error("IMAPI_INIT_ERR");
                }else if (result == SMSUtils.apiClient.IMAPI_CONN_ERR){
                    logger.error("IMAPI_CONN_ERR");
                }else if (result == SMSUtils.apiClient.IMAPI_DATA_ERR){
                    logger.error("IMAPI_DATA_ERR");
                }else if (result == SMSUtils.apiClient.IMAPI_DATA_TOOLONG){
                    logger.error("IMAPI_DATA_TOOLONG");
                }else if (result == SMSUtils.apiClient.IMAPI_INS_ERR){
                    logger.error("IMAPI_INS_ERR");
                }else if (result == SMSUtils.apiClient.IMAPI_IFSTATUS_INVALID){
                    logger.error("IMAPI_IFSTATUS_INVALID");
                }else if (result == SMSUtils.apiClient.IMAPI_GATEWAY_CONN_ERR){
                    logger.error("IMAPI_GATEWAY_CONN_ERR");
                }else{
                    logger.error("other_err");
                }
            }

        } else {
//            logger.error("暂无数据 .....");
        }
//        logger.error("Receive SM Over .....");
    }
}*/
