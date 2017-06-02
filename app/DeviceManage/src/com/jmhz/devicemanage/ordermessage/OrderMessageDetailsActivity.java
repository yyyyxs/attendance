package com.jmhz.devicemanage.ordermessage;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.OrderMessageApi;
import com.uid.trace.module.sale.schema.OrderMessage;
import com.uid.trace.module.sale.schema.OrderMessageParameter;

/**
 * 订单消息详情
 * 
 * @author 锋情
 * 
 */
public class OrderMessageDetailsActivity extends BaseActivity {

	private TextView mTextView = null;
	private Button mButton = null;
	private String content = null;
	private String orderSn = null;
	private int position = -1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_message_details);
		initializeMemberVariable(findViewById(R.id.mainHead));
		Bundle bundle = this.getIntent().getExtras();
		orderSn = bundle.getString("orderSn");
		position = bundle.getInt("position");
		try {
			initWidget();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initWidget() throws Exception {

		mButton = (Button) findViewById(R.id.btn_check_order_details);
		mTextView = (TextView) findViewById(R.id.tv_order_msg);
		mButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(OrderMessageDetailsActivity.this,
						OrderDetailsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("orderSn", orderSn);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

		OrderMessage om = new OrderMessage();
		om.setOrderSn(orderSn);//461407492389296

		new OrderMessageApi(this).getOrderMessageDetail(om, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {

				OrderMessageParameter omp = (OrderMessageParameter) o;
				OrderMessage om = omp.getMessageBody();
				content = "尊敬的"
						+ om.getReceiver()
						+ ":<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						+ om.getOrderSn() + "订单在" + om.getPushTime()
						+ "成功提交。 内容为：<font color=red>" + om.getContent()
						+ "</font>";
				mTextView.setText(Html.fromHtml(content));
				try {
					updateMsgRead();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {

				toastShort(errorMsg);
//				OrderMessageDetailsActivity.this.finish();
			}
		});

	}

	protected void updateMsgRead() throws Exception {

		OrderMessage om = new OrderMessage();
		om.setOrderSn(orderSn);

		new OrderMessageApi(this).updateMsgRead(om, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {

				// toastShort("消息被设置为已读！");
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {

				// toastShort("消息未被设置为已读！"+errorCode+errorMsg);
			}
		});

	}

	@Override
	protected void initializeActivityHead() {
		centreText.setText(R.string.order_msg_details);
		centreText.setTextSize(22);
		rightButton.setText(R.string.delete);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent resultIntent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putInt("position", position);
				resultIntent.putExtras(bundle);
				setResult(RESULT_OK, resultIntent);
				finish();
			}
		});
		rightButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				toastShort("暂无接口！");
			}
		});
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
			Intent resultIntent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putInt("position", position);
			resultIntent.putExtras(bundle);
			setResult(RESULT_OK, resultIntent);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
