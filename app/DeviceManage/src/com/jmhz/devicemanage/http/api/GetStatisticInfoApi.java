package com.jmhz.devicemanage.http.api;

import android.app.Activity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.callback.PostCallback;
import com.jmhz.devicemanage.http.HttpBase;
import com.jmhz.devicemanage.web.StatisticBack;
import com.jmhz.devicemanage.web.StatisticsBack;
import com.jmhz.devicemanage.web.StatisticsYPBack;

public class GetStatisticInfoApi extends HttpBase{

	public GetStatisticInfoApi(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
	}

	private void postTotalMethod(StatisticsYPBack statisticsYPBack, String url,String chosed, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("type",chosed);
		post(url,bundle,new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				StatisticsYPBack statisticsYPBack = gson.fromJson(result, StatisticsYPBack.class);
				Boolean backReasultInfo = statisticsYPBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess(statisticsYPBack);
				} else {
					callback.onFail("0", "·µ»Ø´íÎó");
				}
			}
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	
	public void getTotalStatistic(StatisticsYPBack statisticsYPBack,String chosed, HttpCallback callback) throws Exception{
		postTotalMethod(statisticsYPBack, ALL_DEVICE_STATISTICS_BY_CONDITION,chosed, true, callback);
	}
	
	private void postUpdateMaintainMethod(String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		post(url,new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				StatisticBack statisticBack = gson.fromJson(result, StatisticBack.class);
				Boolean backReasultInfo = statisticBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess(statisticBack);
				} else {
					callback.onFail("0", "·µ»Ø´íÎó");
				}
			}
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	
	public void getUpdateMaintainStatistic(HttpCallback callback) throws Exception{
		postUpdateMaintainMethod(DEVICE_REPAIR_STATISTICS, true, callback);
	}
	
	private void postDeviceMethod(StatisticsBack statisticsBack, String url,String type,String condition, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("type", type);
		bundle.putString("condition", condition);
		post(url,bundle,new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				StatisticsBack statisticsBack = gson.fromJson(result, StatisticsBack.class);
				Boolean backReasultInfo = statisticsBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess(statisticsBack);
				} else {
					callback.onFail("0", "·µ»Ø´íÎó");
				}
			}
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	
	public void getDeviceStatistic(StatisticsBack statisticsBack,String type,String condition, HttpCallback callback) throws Exception{
		postDeviceMethod(statisticsBack, DEVICE_STATISTICS_BY_CONDITION,type,condition, true, callback);
	}
	
}
