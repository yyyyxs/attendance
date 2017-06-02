package com.jmhz.devicemanage.ordermessage;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.OrderDetailAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.SalesOrderApi;
import com.jmhz.devicemanage.model.OrderDetailItem;
import com.uid.trace.module.sale.schema.Logistics;
import com.uid.trace.module.sale.schema.OrderDetail;
import com.uid.trace.module.sale.schema.OrderDetailMessage;
import com.uid.trace.module.sale.schema.OrderGoods;
import com.zxing.activity.CaptureActivity;

/**
 * ��������
 * 
 * @author ����
 * 
 */
public class OrderDetailsActivity extends BaseActivity {

	public static final int REQUEST_CODE = 1;
	private String orderSn = null;
	private ArrayList<OrderDetailItem> mOrderDetailList = new ArrayList<OrderDetailItem>();
	private OrderDetailAdapter mDetailAdapter = null;
	private ListView mLvOrderDetail = null;
	private ScrollView mScrollView = null;
	private Button mButton = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_details);
		initializeMemberVariable(findViewById(R.id.mainHead));
		
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		orderSn = bundle.getString("orderSn");
		
		mScrollView = (ScrollView) findViewById(R.id.sv_check_order_details);
		mButton = (Button) findViewById(R.id.btn_check_order_details);
		mButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(OrderDetailsActivity.this,
						CaptureActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("PageTitle", "ɨ������");
				bundle.putString("operate", "ɨ������");
				bundle.putString("orderSn", orderSn);
				intent.putExtras(bundle);
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
		mScrollView.smoothScrollTo(0, 0);
		mLvOrderDetail = (ListView) findViewById(R.id.lv_order_detail);
		
		try {
			initData();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initData() throws Exception {
		OrderDetail od = new OrderDetail();
		od.setOrderSn(orderSn);
		new SalesOrderApi(this).getCompleteOrderDetail(od, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {

				OrderDetailMessage odm = (OrderDetailMessage) o;
				OrderDetail od = odm.getMessageBody();
				Logistics logistics = od.getLogistics();
				List<OrderGoods> orderGoods = od.getOrderGoods().getListData();

				String orderStatus = null;
				if ("0".equals(od.getOrderStatus())) {
					orderStatus = "��֧��";
				}else if ("1".equals(od.getOrderStatus())) {
					orderStatus = "δ֧��";
				}else if ("2".equals(od.getOrderStatus())) {
					orderStatus = "������";
				}else if ("3".equals(od.getOrderStatus())) {
					orderStatus = "���ջ�";
				}else {
					orderStatus = "δ֪";
				}
				initData(R.id.order_status, "����״̬: ", orderStatus);
				((TextView) findViewById(R.id.order_status).findViewById(
						R.id.tv_value)).setTextColor(OrderDetailsActivity.this
						.getResources().getColor(R.color.red));
				initData(R.id.order_id, "������: ", od.getOrderSn());
				((TextView) findViewById(R.id.order_money)).setText("�������");
				((TextView) findViewById(R.id.order_moneyvalue)).setText("��"
						+ od.getOrderAmount());
				initData(R.id.order_time, "�µ�ʱ��: ", od.getOrderAddTime());
				initData(R.id.order_customer, "�µ��û�: ", od.getBuyer());
				initData(R.id.order_receiver, "    �ռ���: ", logistics.getConsignee());
				initData(R.id.order_address, "�ռ���ַ: ", logistics.getAddress());
				initData(R.id.order_distribution, "���ͷ�ʽ: ",
						logistics.getShippingName());
				for (OrderGoods og : orderGoods) {
					OrderDetailItem mOrder = new OrderDetailItem(og
							.getGoodsName(), og.getGoodsWeight() + "/��", og
							.getGoodsNumber(), og.getGoodsPrice(), og
							.getGoodsTotalPrice());
					mOrderDetailList.add(mOrder);
				}
				initOrderList();
				// initList(R.id.tv_et_order_order_num, orderGoods.get(0)
				// .getGoodsName());
				// initList(R.id.tv_et_order_transactor_name, "����");
				// initList(R.id.tv_et_order_sum, orderGoods.get(0)
				// .getGoodsNumber());
				// initList(R.id.tv_et_order_data, orderGoods.get(0)
				// .getGoodsPrice() + "Ԫ");
				// initList(R.id.tv_et_order_order_type, orderGoods.get(0)
				// .getGoodsTotalPrice() + "Ԫ");
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {

				toastShort(errorMsg);
				finish();
			}
		});

	}

	private void initData(int widgetId, String key, String value) {
		((TextView) findViewById(widgetId).findViewById(R.id.tv_value))
				.setText(value);
		((TextView) findViewById(widgetId).findViewById(R.id.tv_key))
				.setText(key);
	}

	// private void initList(int widgetId, String value) {
	// ((TextView) findViewById(widgetId).findViewById(
	// R.id.tv_et_order_order_num)).setText(value);
	// ((TextView) findViewById(widgetId).findViewById(
	// R.id.tv_et_order_transactor_name)).setText(value);
	// ((TextView) findViewById(widgetId).findViewById(R.id.tv_et_order_sum))
	// .setText(value);
	// ((TextView) findViewById(widgetId).findViewById(R.id.tv_et_order_data))
	// .setText(value);
	// ((TextView) findViewById(widgetId).findViewById(
	// R.id.tv_et_order_order_type)).setText(value);
	// }

	private void initOrderList() {
		// OrderDetailItem mOrder1 = new OrderDetailItem("�л�����", "5kg/��", "1",
		// "10Ԫ", "10Ԫ");
		// OrderDetailItem mOrder2 = new OrderDetailItem("�л�����", "5kg/��", "1",
		// "10Ԫ", "10Ԫ");
		// OrderDetailItem mOrder3 = new OrderDetailItem("�л�����", "5kg/��", "1",
		// "10Ԫ", "10Ԫ");
		// OrderDetailItem mOrder4 = new OrderDetailItem("�л�����", "5kg/��", "1",
		// "10Ԫ", "10Ԫ");

		// mOrderDetailList.add(mOrder1);
		// mOrderDetailList.add(mOrder2);
		// mOrderDetailList.add(mOrder3);
		// mOrderDetailList.add(mOrder4);
		mDetailAdapter = new OrderDetailAdapter(mOrderDetailList, this);
		mLvOrderDetail.setAdapter(mDetailAdapter);
	}

	@Override
	protected void initializeActivityHead() {
		centreText.setText(R.string.order_details);
		rightButton.setText(R.string.distribution_scanning);
		rightButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// startActivityWithClassForResult(CaptureActivity.class,
				// REQUEST_CODE);
				Intent intent = new Intent(OrderDetailsActivity.this,
						CaptureActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("PageTitle", "ɨ������");
				bundle.putString("operate", "ɨ������");
				bundle.putString("orderSn", orderSn);
				intent.putExtras(bundle);
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			if (requestCode == REQUEST_CODE) {
				toastLong("ɨ������" + bundle.getString("result"));
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
