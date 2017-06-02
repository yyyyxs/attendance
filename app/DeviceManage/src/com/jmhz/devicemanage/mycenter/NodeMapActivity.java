package com.jmhz.devicemanage.mycenter;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;

public class NodeMapActivity extends BaseActivity implements
		OnGetGeoCoderResultListener {
	GeoCoder mSearch = null; // ����ģ�飬Ҳ��ȥ����ͼģ�����ʹ��
	BaiduMap mBaiduMap = null;
	MapView mMapView = null;
	public String lon = "";
	public String lat = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nodemap);
		initializeMemberVariable(findViewById(R.id.mainHead));
		Bundle bundle = this.getIntent().getExtras();
		lon = bundle.getString("lon");
		lat = bundle.getString("lat");
		lon = "119.275604";
		lat = "26.080270";

		// ��ͼ��ʼ��
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		// ��ʼ������ģ�飬ע���¼�����
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(this);

		searchButtonProcess();
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		mMapView.onDestroy();
		mSearch.destroy();
		super.onDestroy();

	}

	private void searchButtonProcess() {
		// TODO Auto-generated method stub
		LatLng ptCenter = new LatLng((Float.valueOf(lat)), (Float.valueOf(lon)));
		// ��Geo����
		mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(ptCenter));
	}

	@Override
	protected void initializeActivityHead() {

		centreText.setText(R.string.site_address);
		rightButton.setText(R.string.location);
		rightButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				searchButtonProcess();
			}
		});
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mBaiduMap = null;
				NodeMapActivity.this.finish();
			}
		});
	}

	@Override
	public void onGetGeoCodeResult(GeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(NodeMapActivity.this, "��Ǹ��δ���ҵ����", Toast.LENGTH_LONG)
					.show();
			return;
		}
		mBaiduMap.clear();
		mBaiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.icon_marka)));
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
				.getLocation()));
		String strInfo = String.format("γ�ȣ�%f ���ȣ�%f",
				result.getLocation().latitude, result.getLocation().longitude);
		Toast.makeText(NodeMapActivity.this, strInfo, Toast.LENGTH_LONG).show();

	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(NodeMapActivity.this, "��Ǹ��δ���ҵ����", Toast.LENGTH_LONG)
					.show();
			return;
		}
		mBaiduMap.clear();
		mBaiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.icon_marka)));
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
				.getLocation()));
		Toast.makeText(NodeMapActivity.this, result.getAddress(),
				Toast.LENGTH_LONG).show();

	}
}
