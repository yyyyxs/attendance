package com.baidu.yun.channel.client;

import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.*;

public interface BaiduChannel {

    public PushUnicastMessageResponse pushUnicastMessage(
            PushUnicastMessageRequest request) throws ChannelClientException,
            ChannelServerException;

    public PushTagMessageResponse pushTagMessage(PushTagMessageRequest request)
            throws ChannelClientException, ChannelServerException;

    public PushBroadcastMessageResponse pushBroadcastMessage(
            PushBroadcastMessageRequest request) throws ChannelClientException,
            ChannelServerException;

    public QueryBindListResponse queryBindList(QueryBindListRequest request)
            throws ChannelClientException, ChannelServerException;

    public void verifyBind(VerifyBindRequest request)
            throws ChannelClientException, ChannelServerException;

    public FetchMessageResponse fetchMessage(FetchMessageRequest request)
            throws ChannelClientException, ChannelServerException;

    public void setTag(SetTagRequest request) throws ChannelClientException,
            ChannelServerException;

    public void deleteTag(DeleteTagRequest request)
            throws ChannelClientException, ChannelServerException;

    public FetchTagResponse fetchTag(FetchTagRequest request)
            throws ChannelClientException, ChannelServerException;

    public QueryUserTagsResponse queryUserTags(QueryUserTagsRequest request)
            throws ChannelClientException, ChannelServerException;

    public void initAppIoscert(InitAppIoscertRequest request)
            throws ChannelClientException, ChannelServerException;

    public void updateAppIoscert(UpdateAppIoscertRequest request)
            throws ChannelClientException, ChannelServerException;

    public void deleteAppIoscert(DeleteAppIoscertRequest request)
            throws ChannelClientException, ChannelServerException;

    public QueryAppIoscertResponse queryAppIoscert(
            QueryAppIoscertRequest request) throws ChannelClientException,
            ChannelServerException;

    public QueryDeviceTypeResponse queryDeviceType(
            QueryDeviceTypeRequest request) throws ChannelClientException,
            ChannelServerException;

}
