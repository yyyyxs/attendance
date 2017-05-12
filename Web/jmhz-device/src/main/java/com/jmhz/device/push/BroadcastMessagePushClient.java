package com.jmhz.device.push;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.jmhz.device.Constants;
import org.apache.log4j.Logger;

/**
 * Usage:广播的话，不管你service是否开启，都可以成功成功，只不过推送的消息等你开启service时才会接收到
 *
 * @author Charkey
 * @date 2015/5/16 15:22
 */
public class BroadcastMessagePushClient {

    private static final Logger logger = Logger.getLogger(BroadcastMessagePushClient.class);

    public static Boolean pushMessage(String title, String content) {

        // 1. 设置developer平台的ApiKey/SecretKey
        String apiKey = Constants.BAIDU_PUSH.API_KEY;
        String secretKey = Constants.BAIDU_PUSH.SECRET_KEY;
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. 创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                logger.info(event.getMessage());
            }
        });

        try {
            // 4. 创建请求类对象
            PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
            // device_type => 1: web 2: pc 3:android 4:ios 5:wp
            request.setDeviceType(3);
            request.setMessageType(1);
            // DeployStatus => 1: Developer 2:
            request.setDeployStatus(2);
            // Production
            request.setMessage("{\"title\":\"" + title + "\",\"description\":\"" + content + "\"}");

            // 5. 调用pushMessage接口
            PushBroadcastMessageResponse response = channelClient
                    .pushBroadcastMessage(request);

            // 6. 认证推送成功
            logger.info("push amount : " + response.getSuccessAmount());

            return true;
        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            logger.error(e.getMessage());
            return false;
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
            logger.error(String.format("request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            return false;
        }
    }

    public static void main(String[] args) {

        Boolean pushRes = pushMessage("^_^广播通知", "This is a test message from Charkey. 司码工作室");

        System.out.println("推送结果： " + pushRes);

    }
}
