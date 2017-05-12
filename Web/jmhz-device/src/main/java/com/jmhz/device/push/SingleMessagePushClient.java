package com.jmhz.device.push;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.jmhz.device.Constants;
import org.apache.log4j.Logger;

/**
 * 单播发送的时候你要发送的对象必须有启动service，否则会发送失败
 */
public class SingleMessagePushClient {

    private static final Logger logger = Logger.getLogger(SingleMessagePushClient.class);

    /**
     * 推送消息
     *
     * @param channelId 手机端的ChannelId
     * @param userId 手机端的UserId
     * @param title 消息标题
     * @param content 消息内容
     * @return 是否发送成功
     */
    public static boolean pushMessage(Long channelId, String userId, String title, String content) {

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
            // 手机端的ChannelId，手机端的UserId
            PushUnicastMessageRequest request = new PushUnicastMessageRequest();
            // device_type => 1: web 2: pc 3:android 4:ios 5:wp
            request.setDeviceType(3);
            request.setChannelId(channelId);
            request.setUserId(userId);
            request.setMessageType(1);
            request.setMessage("{\"title\":\"" + title + "\",\"description\":\"" + content + "\"}");

            // 5. 调用pushMessage接口
            PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);

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

        Boolean pushRes = pushMessage(3880602872469198218L, "710438285720557248", "^_^单播通知", "This is a test message from Charkey. 司码工作室");

        System.out.println("推送结果： " + pushRes);

    }

}
