
package com.jmhz.devicemanage.stock;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.TransportNodeApi;
import com.jmhz.devicemanage.model.DeliveryOutItem;
import com.uid.trace.common.schema.Paging;
import com.uid.trace.common.schema.TransportNode;
import com.uid.trace.module.stock.schema.TransportNodeMessage;
import com.zxing.activity.CaptureActivity;

public class DeliveryOutActivity extends BaseActivity implements
		OnItemSelectedListener {

	private List<String> deliveryOutSite = null;
	private List<DeliveryOutItem> deliveryOutSiteItem = null;
	private Spinner mSpDeliveryOut = null;
	private ArrayAdapter<String> mAdapterSp = null;
	// private static String mSite = null;
	private Button btnStockOut = null;
	private String currentNodeId = "";
	private TransportNode tn = null;
	private List<TransportNode> mResult = null;
	private int rowTotal = 0;
	private int mPage = 1;
	private int pageSize = 2;
	@SuppressWarnings("unused")
	private boolean mLoading = false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delivery_out);
		initializeMemberVariable(findViewById(R.id.mainHead));
		try {
			initView();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initView() throws Exception {
		mSpDeliveryOut = (Spinner) findViewById(R.id.sp_delivery_out_site);
		getTransportNode();
		btnStockOut = (Button) findViewById(R.id.btn_stock_out);
		btnStockOut.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (currentNodeId == "") {
					toastShort("��ѡ�����վ��");
					return;
				}
				Intent intent = new Intent(DeliveryOutActivity.this,
						CaptureActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("PageTitle", "��������");
				bundle.putString("operate", "��������");
				bundle.putString("nodeId", currentNodeId);
				intent.putExtras(bundle);
				startActivity(intent);

			}
		});
	}

	protected void getTransportNode() throws Exception {
		tn = new TransportNode();
		Paging pag = new Paging();
		pag.setPage(mPage);
		pag.setPageSize(pageSize);
		tn.setPaging(pag);

		new TransportNodeApi(this).getTransportNode(tn, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {

				TransportNodeMessage tnsm = (TransportNodeMessage) o;
				TransportNode tn = tnsm.getMessageBody();
				rowTotal = tn.getPaging().getRowTotal();
				mResult = tn.getTransportNodeList();
				deliveryOutSite = new ArrayList<String>();
				deliveryOutSiteItem = new ArrayList<DeliveryOutItem>();
				for (TransportNode result : mResult) {
					DeliveryOutItem deliveryOutItem = new DeliveryOutItem(
							result.getNodeName(), result.getNodeId());
					deliveryOutSite.add(result.getNodeName());

					deliveryOutSiteItem.add(deliveryOutItem);
					currentNodeId = result.getNodeId();
				}
				if (rowTotal > deliveryOutSite.size()) {
					deliveryOutSite.add("��ȡ��������");
				}

				toastShort("��ȡ" + mResult.size() + "�����ݣ���" + rowTotal);
				mAdapterSp = new ArrayAdapter<String>(DeliveryOutActivity.this,
						R.layout.spinner_checked_text, deliveryOutSite);
				mAdapterSp
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				mSpDeliveryOut
						.setOnItemSelectedListener(DeliveryOutActivity.this);
				// mSpDeliveryOut.seto
				mSpDeliveryOut.setAdapter(mAdapterSp);
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {
				toastShort("����ʧ�ܣ�" + errorMsg);
			}
		});

	}

	@Override
	protected void initializeActivityHead() {
		centreText.setText(R.string.delivery_out);
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// startActivityWithClass(EnterWarehouseListActivity.class);
				finish();
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
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View v, int arg2, long arg3) {

		// ������ʾ��ǰѡ�����
		// mSite = deliveryOutSite.get(arg2);
		// v.setVisibility(View.VISIBLE);

		if ("��ȡ��������".equals(deliveryOutSite.get(arg2))) {
			Log.i("cfq", arg2 + "");
			deliveryOutSite.remove(arg2);
			getMoreNodes();
		}
		// currentNodeId = deliveryOutSiteItem.get(arg2).getNodeId();
		return;
	}

	private void getMoreNodes() {

		mPage += 1;
		mLoading = true;
		tn = new TransportNode();
		Paging pag = new Paging();
		pag.setPage(mPage);
		pag.setPageSize(pageSize);
		tn.setPaging(pag);
		try {
			getMoreNode(tn);
		} catch (Exception e) {
			mLoading = false;
			e.printStackTrace();
			mPage -= 1;
		}

	}

	private void getMoreNode(TransportNode tn2) throws Exception {
		new TransportNodeApi(this).getTransportNode(tn, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {

				TransportNodeMessage tnsm = (TransportNodeMessage) o;
				TransportNode tn = tnsm.getMessageBody();
				rowTotal = tn.getPaging().getRowTotal();
				mResult = tn.getTransportNodeList();
				for (TransportNode result : mResult) {
					DeliveryOutItem deliveryOutItem = new DeliveryOutItem(
							result.getNodeName(), result.getNodeId());
					deliveryOutSite.add(result.getNodeName());

					deliveryOutSiteItem.add(deliveryOutItem);
					currentNodeId = result.getNodeId();
				}
				toastShort("��ȡ" + mResult.size() + "������");
				if (rowTotal > deliveryOutSite.size()) {
					deliveryOutSite.add("��ȡ��������");
				}
				mAdapterSp.notifyDataSetChanged();

			}

			@Override
			public void onFail(String errorCode, String errorMsg) {
				Toast.makeText(DeliveryOutActivity.this,
						"����ʧ�ܣ�" + errorMsg, 1).show();
				deliveryOutSite.add("��ȡ��������");
				mSpDeliveryOut.setSelection(0, true);
				mPage -= 1;
			}
		});
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		Log.i("cfq", "onNothingSelected");
	}
}
