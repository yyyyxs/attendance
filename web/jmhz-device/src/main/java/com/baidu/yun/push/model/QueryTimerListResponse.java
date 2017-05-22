package com.baidu.yun.push.model;

import com.baidu.yun.core.annotation.JSonPath;

import java.util.LinkedList;
import java.util.List;

public class QueryTimerListResponse extends PushResponse {

    @JSonPath(path = "response_params\\total_num")
    private int totalNum;

    @JSonPath(path = "response_params\\result")
    private List<TimerResultInfo> timerResultInfos = new LinkedList<TimerResultInfo>();

    // get
    public int getTotalNum() {
        return totalNum;
    }

    public List<TimerResultInfo> getTimerResultInfos() {
        return timerResultInfos;
    }
}
