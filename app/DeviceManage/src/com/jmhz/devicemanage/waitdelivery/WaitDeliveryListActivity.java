package com.jmhz.devicemanage.waitdelivery;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;

public class WaitDeliveryListActivity extends BaseActivity {

	/**
	 * 待出库列表的详细信息
	 */
	private ListView mLvWaitDelivery = null;
	private WaitDeliveryAdapter mWaitDeliveryAdapter = null;
	private String[] data = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wait_delivery_list);
		initializeMemberVariable(findViewById(R.id.mainHead));
		mLvWaitDelivery = (ListView) findViewById(R.id.lv_wait_delivery);

		mWaitDeliveryAdapter = new WaitDeliveryAdapter(this, data);// ------------data需要定义
		mLvWaitDelivery.setAdapter(mWaitDeliveryAdapter);
	}

	@Override
	protected void initializeActivityHead() {
		centreText.setText(R.string.wait_delivery_list);
		rightButton.setText(R.string.delete);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
