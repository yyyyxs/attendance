/**
 * ************************************************************************
 * <p/>
 * Copyright (c) 2015 Baidu.com, Inc. All Rights Reserved
 * <p/>
 * ************************************************************************
 *
 * @file pushClientTest.java
 * @date 2015/03/15 17:06:26
 */

/**
 * @file pushClientTest.java
 * @date 2015/03/15 17:06:26
 **/

package com.baidu.yun.test;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.*;
import com.jmhz.device.Constants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.List;

public class PushClientTest {

    @Test     // push one message to single device
    public void testPushMsgToSingleDevice() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = Constants.BAIDU_PUSH.API_KEY;
        String secretKey = Constants.BAIDU_PUSH.SECRET_KEY;
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            // make Android Notification
            JSONObject notification = new JSONObject();
            notification.put("title", "TEST");
            notification.put("description", "Hello Baidu Push");
            notification.put("notification_builder_id", 0);
            notification.put("notification_basic_style", 4);
            notification.put("open_type", 1);
            notification.put("url", "http://push.baidu.com");
            JSONObject jsonCustormCont = new JSONObject();
            jsonCustormCont.put("key", "value"); //自定义内容，key-value
            notification.put("custom_content", jsonCustormCont);

            PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().
                    addChannelId("3569104444463414374").
                    addMsgExpires(new Integer(3600)). //message有效时间
                    addMessageType(1).//1：通知,0:消息.默认为0  注：IOS只有通知.
                    addMessage(notification.toString()).
                    //addDeployStatus(2). //IOS, DeployStatus => 1: Developer 2: Production.
                            addDeviceType(3);// deviceType => 3:android, 4:ios
            // 5. http request
            PushMsgToSingleDeviceResponse response = pushClient.
                    pushMsgToSingleDevice(request);
            // Http请求结果解析打印
            System.out.println("msgId: " + response.getMsgId()
                    + ",sendTime: " + response.getSendTime());
        } catch (PushClientException e) {
            /*ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
             *'true' 表示抛出, 'false' 表示捕获。
             */
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test      // push message to all devices
    public void testPushMsgToAll() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            PushMsgToAllRequest request = new PushMsgToAllRequest().
                    addMsgExpires(new Integer(3600)).
                    addMessageType(0).
                    addMessage("Hello Baidu Push.").
                    addSendTime(System.currentTimeMillis() / 1000 + 120).//设置定时推送时间，必需超过当前时间一分钟，单位秒.实例70秒后推送
                    addDeviceType(3);
            // 5. http request
            PushMsgToAllResponse response = pushClient.
                    pushMsgToAll(request);
            // Http请求结果解析打印
            System.out.println("msgId: " + response.getMsgId()
                    + ",sendTime: " + response.getSendTime()
                    + ",timerId: " + response.getTimerId());
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test      // push message to devices in tags which were defined by users 
    public void testPushMsgToTag() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            // pushTagTpye = 1   for common tag pushing
            JSONObject notification = new JSONObject();
            notification.put("title", "TEST");
            notification.put("description", "Hello Baidu Push,http://push.baidu.com");
            notification.put("notification_builder_id", 0);
            notification.put("notification_basic_style", 4);
            notification.put("open_type", 1);
            notification.put("url", "http://push.baidu.com");
            JSONObject jsonCustormCont = new JSONObject();
            jsonCustormCont.put("key", "value"); //自定义内容，key-value
            notification.put("custom_content", jsonCustormCont);

            PushMsgToTagRequest request = new PushMsgToTagRequest().
                    addTagName("pushdemo").
                    addMsgExpires(new Integer(86400)).
                    addMessageType(1).
                    //addSendTime(System.currentTimeMillis() / 1000 + 70).
                            addMessage(notification.toString()).
                    addDeviceType(3);
            // 5. http request
            PushMsgToTagResponse response = pushClient.
                    pushMsgToTag(request);
            // Http请求结果解析打印
            System.out.println("msgId: " + response.getMsgId()
                    + ",sendTime: " + response.getSendTime()
                    + ",timerId: " + response.getTimerId());
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test      // push message to devices in tags which were defined by baidu-push
    // 组播消息推送的类型，必选. 6:组合运算标签 , 仅android使用
    public void testPushMsgToSmartTag() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
//        	tag operation pushing
//        	example.1  {"OR":{"tag":["xxxx","xxxx"]}}
            JSONObject selector = new JSONObject();
            JSONObject tmpJson = new JSONObject();
            JSONArray tagArray = new JSONArray();
            tagArray.add(0, "pushdemo1");
            tagArray.add(1, "pushdemo2");
            tmpJson.put("tag", tagArray);
            selector.put("OR", tmpJson); // "AND":交,"OR":并,"DIFF":差

//        	example.2  {"OR":[{"tag":"xxxx"},{"tag":"xxxx"}]}
//        	JSONObject firstTag = new JSONObject();
//        	firstTag.put("tag", "xxxx");
//        	JSONObject secondTag = new JSONObject();
//        	secondTag.put("tag", "xxxx");
//        	JSONArray tagArray = new JSONArray();
//        	tagArray.add(0, firstTag);
//        	tagArray.add(1, secondTag);
//        	selector.put("OR", tagArray);

            PushMsgToSmartTagRequest request = new PushMsgToSmartTagRequest().
                    addSelector(selector.toString()).
                    addMsgExpires(new Integer(3600)).
                    //addSendTime(System.currentTimeMillis() / 1000 + 70).
                            addMessageType(1).
                    addMessage("{\"title\":\"TEST\",\"description\":\"Hello Baidu push!\"}").
                    addDeviceType(3);
            // 5. http request
            PushMsgToSmartTagResponse response = pushClient.
                    pushMsgToSmartTag(request);
            // Http请求结果解析打印
            System.out.println("msgId: " + response.getMsgId()
                    + ",sendTime: " + response.getSendTime()
                    + ",timerId: " + response.getTimerId());
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test      // push batch unicast message to some channels, only for Android
    public void testPushBatchUniMsg() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            String[] channelIds = {"3569104444463414374", "3775739456230007867"};
            PushBatchUniMsgRequest request = new PushBatchUniMsgRequest().
                    addChannelIds(channelIds).
                    addMsgExpires(new Integer(3600)).
                    addMessageType(1).
                    addMessage("{\"title\":\"TEST\",\"description\":\"Hello Baidu push!\"}").
                    addDeviceType(3).
                    addTopicId("BaiduPush");// 设置类别主题
            // 5. http request
            PushBatchUniMsgResponse response = pushClient.
                    pushBatchUniMsg(request);
            // Http请求结果解析打印
            System.out.println(String.format(
                    "msgId: %s, sendTime: %d",
                    response.getMsgId(), response.getSendTime()));
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test      // query message status
    public void testQueryMsgStatus() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            String[] msgIds = {"3611551594798437811"};
            QueryMsgStatusRequest request = new QueryMsgStatusRequest().
                    addMsgIds(msgIds).
                    addDeviceType(3);
            // 5. http request
            QueryMsgStatusResponse response = pushClient.
                    queryMsgStatus(request);
            // Http请求结果解析打印
            System.out.println("totalNum: " + response.getTotalNum()
                    + "\n" + "result:");
            if (null != response) {
                List<?> list = response.getMsgSendInfos();
                for (int i = 0; i < list.size(); i++) {
                    Object object = list.get(i);
                    if (object instanceof MsgSendInfo) {
                        MsgSendInfo msgSendInfo = (MsgSendInfo) object;
                        StringBuilder strBuilder = new StringBuilder();
                        strBuilder.append("List[" + i + "]: {"
                                + "msgId = " + msgSendInfo.getMsgId()
                                + ",status = " + msgSendInfo.getMsgStatus()
                                + ",sendTime = " + msgSendInfo.getSendTime()
                                + ",success = " + msgSendInfo.getSuccessCount());
                        strBuilder.append("}\n");
                        System.out.println(strBuilder.toString());
                    }
                }
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test
    public void testQueryTimerRecords() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            QueryTimerRecordsRequest request = new QueryTimerRecordsRequest().
                    addTimerId("2413503337800107571").
                    addStart(0).//设置索引起始位置
                    addLimit(10).//设置查询记录条数
                    //addRangeStart(new Long(xxxxxxxx)).//设置查询开始时间
                    //addRangeEnd(System.currentTimeMillis() / 1000).//设置查询结束时间
                    addDeviceType(3);
            // 5. http request
            QueryTimerRecordsResponse response = pushClient.
                    queryTimerRecords(request);
            // Http请求结果解析打印
            System.out.println("timerId: " + response.getTimerId()
                    + "\nresult: ");
            if (null != response) {
                List<?> list = response.getTimerRecords();
                for (int i = 0; i < list.size(); i++) {
                    Object object = list.get(i);
                    if (object instanceof Record) {
                        Record record = (Record) object;
                        StringBuilder strBuilder = new StringBuilder();
                        strBuilder.append("List[" + i + "]: {"
                                + "msgId = " + record.getMsgId()
                                + ",status = " + record.getMsgStatus()
                                + ",sendTime = " + record.getSendTime()
                                + "}");
                        System.out.println(strBuilder.toString());
                    }
                }
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test
    public void testQueryTopicRecords() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            QueryTopicRecordsRequest request = new QueryTopicRecordsRequest().
                    addTopicId("BaiduPush").
                    addStart(0).
                    addLimit(5).
                    //addRangeStart(new Long(xxxxxxxx)).
                            //addRangeEnd(System.currentTimeMillis() / 1000).
                            addDeviceType(3);
            // 5. http request
            QueryTopicRecordsResponse response = pushClient.
                    queryTopicRecords(request);
            // Http请求结果解析打印
            StringBuilder strBuilder = new StringBuilder();
            if (null != response) {
                strBuilder.append("topicId: " + response.getTopicId()
                        + "\n" + "result:{\n");
                List<?> list = response.getTopicRecords();
                for (int i = 0; i < list.size(); i++) {
                    Object object = list.get(i);
                    if (i != 0) {
                        strBuilder.append(",");
                    }
                    if (object instanceof Record) {
                        Record record = (Record) object;
                        strBuilder.append("{msgId: " + record.getMsgId()
                                + ", status: " + record.getMsgStatus()
                                + ", sendTime: " + record.getSendTime()
                                + "}\n");
                    }
                }
                strBuilder.append("}");
                System.out.println(strBuilder.toString());
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test
    public void testQueryTimerList() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            QueryTimerListRequest request = new QueryTimerListRequest().
                    //addTimerId("2413503337800107571"). //查询一个定时任务
                            addStart(0).
                    addLimit(6).
                    addDeviceType(3);
            // 5. http request
            QueryTimerListResponse response = pushClient.
                    queryTimerList(request);
            // Http请求结果解析打印
            System.out.println("totalNum: " + response.getTotalNum()
                    + "\n" + "result:");
            if (null != response) {
                List<?> list = response.getTimerResultInfos();
                for (int i = 0; i < list.size(); i++) {
                    Object object = list.get(i);
                    StringBuilder strBuilder = new StringBuilder();
                    if (object instanceof TimerResultInfo) {
                        TimerResultInfo timerResult = (TimerResultInfo) object;
                        strBuilder.append("List[" + i + "]: " + "timerId= " + timerResult.getTimerId()
                                + ",sendTime= " + timerResult.getSendTime()
                                + ",message= " + timerResult.getMessage()
                                + ",msgType= " + timerResult.getMsgType()
                                + ",rangeType= " + timerResult.getRangeType()
                                + "\n");
                    }
                    System.out.println(strBuilder.toString());
                }
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test
    public void testQueryTopicList() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            QueryTopicListRequest request = new QueryTopicListRequest().
                    addStart(0).
                    addLimit(6).
                    addDeviceType(3);
            // 5. http request
            QueryTopicListResponse response = pushClient.
                    queryTopicList(request);
            // Http请求结果解析打印
            System.out.println("totalNum: " + response.getTotalNum()
                    + "\n" + "result:");
            if (null != response) {
                List<?> list = response.getTimerResultInfos();
                for (int i = 0; i < list.size(); i++) {
                    Object object = list.get(i);
                    StringBuilder strBuilder = new StringBuilder();
                    if (object instanceof TopicResultInfo) {
                        TopicResultInfo topicResult = (TopicResultInfo) object;
                        strBuilder.append("List[" + i + "]: " + "topicId= " + topicResult.getTopicId()
                                + ",firstPushTime= " + topicResult.getFirstPushTime()
                                + ",lastPushTime= " + topicResult.getLastPushTime()
                                + ",totalPushDevsNum= " + topicResult.getTotalPushDevsNum()
                                + ",totalAckDevsNum= " + topicResult.getTotalAckDevsNum());
                    }
                    System.out.println(strBuilder.toString());
                }
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test
    public void testQueryTags() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            QueryTagsRequest request = new QueryTagsRequest().
                    //addTagName("xxxxx").
                            addStart(0).
                    addLimit(20).
                    addDeviceType(3);
            // 5. http request
            QueryTagsResponse response = pushClient.
                    queryTags(request);
            // Http请求结果解析打印
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("totalNum: " + response.getTotalNum()
                    + "\n");
            if (null != response) {
                List<?> list = response.getTagsInfo();
                for (int i = 0; i < list.size(); i++) {
                    Object object = list.get(i);
                    if (object instanceof TagInfo) {
                        TagInfo tagInfo = (TagInfo) object;
                        strBuilder.append("List[" + i + "]: "
                                + "tagId=" + tagInfo.getTagId()
                                + ",tag=" + tagInfo.getTagName()
                                + ",info=" + tagInfo.getInfo()
                                + ",type=" + tagInfo.getType()
                                + ",creatTime=" + tagInfo.getCreateTime()
                                + "\n");
                    }
                }
                System.out.println(strBuilder.toString());
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test
    public void testCreateTag() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                // TODO Auto-generated method stub
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            CreateTagRequest request = new CreateTagRequest().
                    addTagName("pushdemo2").
                    addDeviceType(3);
            // 5. http request
            CreateTagResponse response = pushClient.
                    createTag(request);
            System.out.println(String.format(
                    "tagName: %s, result: %d",
                    response.getTagName(),
                    response.getResult()));
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test
    public void testDeleteTag() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            DeleteTagRequest request = new DeleteTagRequest().
                    addTagName("pushdemo1").
                    addDeviceType(new Integer(3));
            // 5. http request
            DeleteTagResponse response = pushClient.
                    deleteTag(request);
            // Http请求结果解析打印
            System.out.println(String.format(
                    "tagName: %s, result: %d",
                    response.getTagName(),
                    response.getResult()));
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test
    public void testAddDevicesToTag() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacted information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            String[] channelIds = {"3569104444463414374", "3775739456230007867"};
            AddDevicesToTagRequest request = new AddDevicesToTagRequest().
                    addTagName("pushdemo2").
                    addChannelIds(channelIds).
                    addDeviceType(3);
            // 5. http request
            AddDevicesToTagResponse response = pushClient.
                    addDevicesToTag(request);
            // Http请求结果解析打印
            if (null != response) {
                StringBuilder strBuilder = new StringBuilder();
                strBuilder.append("devicesInTag：{");
                List<?> devicesInfo = response.getDevicesInfoAfterAdded();
                for (int i = 0; i < devicesInfo.size(); i++) {
                    Object object = devicesInfo.get(i);
                    if (i != 0) {
                        strBuilder.append(",");
                    }
                    if (object instanceof DeviceInfo) {
                        DeviceInfo deviceInfo = (DeviceInfo) object;
                        strBuilder.append("{channelId:" + deviceInfo.getChannelId()
                                + ",result:" + deviceInfo.getResult()
                                + "}");
                    }
                }
                strBuilder.append("}");
                System.out.println(strBuilder.toString());
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test
    public void testDeleteDevicesFromTag() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            String[] channelIds = {"3569104444463414374", "3775739456230007867"};
            DeleteDevicesFromTagRequest request = new DeleteDevicesFromTagRequest().
                    addTagName("pushdemo").
                    addChannelIds(channelIds).
                    addDeviceType(3);
            // 5. http request
            DeleteDevicesFromTagResponse response = pushClient.
                    deleteDevicesFromTag(request);
            // Http请求结果解析打印
            if (null != response) {
                StringBuilder strBuilder = new StringBuilder();
                strBuilder.append("devicesInfoAfterDel:{");
                List<?> list = response.getDevicesInfoAfterDel();
                for (int i = 0; i < list.size(); i++) {
                    if (i != 0) {
                        strBuilder.append(",");
                    }
                    Object object = list.get(i);
                    if (object instanceof DeviceInfo) {
                        DeviceInfo deviceInfo = (DeviceInfo) object;
                        strBuilder.append("{channelId: " + deviceInfo.getChannelId()
                                + ", result: " + deviceInfo.getResult()
                                + "}");
                    }
                }
                strBuilder.append("}");
                System.out.println(strBuilder.toString());
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test
    public void testQueryDeviceNumInTag() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            QueryDeviceNumInTagRequest request = new QueryDeviceNumInTagRequest().
                    addTagName("pushdemo").
                    addDeviceType(3);
            // 5. http request
            QueryDeviceNumInTagResponse response = pushClient.
                    queryDeviceNumInTag(request);
            if (null != response) {
                System.out.println(String.format(
                        "deviceNum: %d",
                        response.getDeviceNum()));
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test
    public void testQueryStatisticMsg() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            QueryStatisticMsgRequest request = new QueryStatisticMsgRequest().
                    addQueryType(0).
                    addDeviceType(3);
            // 5. http request
            QueryStatisticMsgResponse response = pushClient.
                    queryStatisticMsg(request);

            // Http请求结果解析打印
            if (null != response) {
                StringBuilder strBuilder = new StringBuilder();
                List<MsgStatInfo> msgStats = response.getMsgStats();
                for (int i = 0; i < msgStats.size(); i++) {
                    MsgStatInfo msgStatInfo = msgStats.get(i);
                    strBuilder.append("totalNum:" + msgStatInfo.getTotalNum()
                            + "\n" + "rangetype:" + msgStatInfo.getRangeType()
                            + "\n" + "result:{");
                    List<KeyValueForMsg> result = msgStatInfo.getResult();
                    for (int j = 0; j < result.size(); j++) {
                        KeyValueForMsg keyValue = result.get(j);
                        if (j != 0) {
                            strBuilder.append(",");
                        }
                        strBuilder.append("" + keyValue.getKey() + ":{"
                                + "pushNum=" + keyValue.getValue().getPushNum()
                                + ",ackNum=" + keyValue.getValue().getAckNum()
                                + ",delNum=" + keyValue.getValue().getDelNum()
                                + ",clickNum=" + keyValue.getValue().getClickNum()
                                + "}\n");
                    }
                    strBuilder.append("}");
                }
                System.out.println(strBuilder.toString());
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test
    public void testQueryStatisticTopic() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            QueryStatisticTopicRequest request = new QueryStatisticTopicRequest().
                    addTopicId("BaiduPush").
                    addDeviceType(3);
            // 5. http request
            QueryStatisticTopicResponse response = pushClient.
                    queryStatisticTopic(request);
            // Http请求结果解析打印
            if (null != response) {
                StringBuilder strBuilder = new StringBuilder();
                strBuilder.append("totalNum: " + response.getTotalNum());
                List<KeyValueForTopic> topicStats = response.getResult();
                strBuilder.append("\nresult:{");
                for (int i = 0; i < topicStats.size(); i++) {
                    KeyValueForTopic keyValue = topicStats.get(i);
                    if (i != 0) {
                        strBuilder.append(",");
                    }
                    strBuilder.append("" + keyValue.getKey() + ":{"
                            + "ackNum: " + keyValue.getValue().getAckNum()
                            + "}");
                }
                strBuilder.append("\n}");
                System.out.println(strBuilder.toString());
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    @Test
    public void testQueryStatisticDevice() throws PushClientException,
            PushServerException {
        // 1. get apiKey and secretKey from developer console
        String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            QueryStatisticDeviceRequest request = new QueryStatisticDeviceRequest().
                    addDeviceType(3);
            // 5. http request
            QueryStatisticDeviceResponse response = pushClient.
                    queryStatisticDevice(request);
            // Http请求结果解析打印
            if (null != response) {
                StringBuilder strBuilder = new StringBuilder();
                strBuilder.append("totalNum: " + response.getTotalNum()
                        + "\n");
                List<KeyValueForDevice> deviceStats = response.getResult();
                strBuilder.append("result:{");
                for (int i = 0; i < deviceStats.size(); i++) {
                    KeyValueForDevice keyValue = deviceStats.get(i);
                    if (i != 0) {
                        strBuilder.append(",");
                    }
                    strBuilder.append("" + keyValue.getKey() + ":{"
                            + "newDeviceNum=" + keyValue.getValue().getNewDevNum()
                            + ",delDeviceNum=" + keyValue.getValue().getDelDevNum()
                            + ",onlineDeviceNum=" + keyValue.getValue().getOnlineDevNum()
                            + ",addUpDeviceNum=" + keyValue.getValue().getAddUpDevNum()
                            + ",totalDeviceNum=" + keyValue.getValue().getTotalDevNum()
                            + "}\n");
                }
                strBuilder.append("}");
                System.out.println(strBuilder.toString());
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }
}





















/* vim: set expandtab ts=4 sw=4 sts=4 tw=100: */
