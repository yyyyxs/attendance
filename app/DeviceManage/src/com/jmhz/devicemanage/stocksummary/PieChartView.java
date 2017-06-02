package com.jmhz.devicemanage.stocksummary;

import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

public class PieChartView {
	
	private double[] values = new double[5];
	private static int[] colors = new int[] { 0xC8ffaeb9,0xC8B0E0E6,0xc8FFD700,0xC8F4A460,0xC8DDA0DD};
	private DefaultRenderer renderer;
	private Context mContext;
	private List<String> option;
	private String mTitle;
	private Boolean isThree;
	public PieChartView(Context context,Boolean isThree) {
		this.mContext = context;
		this.isThree= isThree;
		this.renderer = new DefaultRenderer();
	}

	public void initData(double[] firstAnswerPercent, List<String> option, String title) {
		this.values=firstAnswerPercent;
		this.mTitle = title;
		this.option = option;
		
	}
	public View getPieChartView(){
		buildCategoryRenderer(colors);
		View view = ChartFactory.getPieChartView(mContext, buildCategoryDataset(mTitle, values), renderer);
		view.setBackgroundColor(0x00ffffff);
		
		
		return view;
	}

	protected DefaultRenderer buildCategoryRenderer(int[] colors) {
		if (null == renderer) {
			return null;
		}
		renderer.setLegendTextSize(30);// �������½Ǳ�ע�����ִ�С
		// renderer.setZoomButtonsVisible(true);//������ʾ�Ŵ���С��ť
		renderer.setZoomEnabled(false);// ���ò�����Ŵ���С.
		renderer.setChartTitleTextSize(30);// ����ͼ���������ִ�С
		renderer.setChartTitle(mTitle);// ����ͼ��ı��� Ĭ���Ǿ��ж�����ʾ
		renderer.setLabelsTextSize(30);// ��ͼ�ϱ�����ֵ������С
		renderer.setLabelsColor(Color.GRAY);//��ͼ�ϱ�����ֵ���ɫ
		renderer.setPanEnabled(false);// �����Ƿ����ƽ��
		renderer.setDisplayValues(true);//�Ƿ���ʾֵ
		renderer.setClickEnabled(true);// �����Ƿ���Ա����
		renderer.setMargins(new int[] { 70, 70, 70, 70 });
		
		// margins - an array containing the margin size values, in this order:
		// top, left, bottom, right
		int length = isThree ? (colors.length - 2) : colors.length;
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(colors[i]);
			renderer.addSeriesRenderer(r);
		}
		return renderer;
	}
	
	protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        double[] v = values;
		int seriesLength = v.length;
		for (int k = 0; k < seriesLength; k++) {
			series.add(option.get(k),v[k]);
		}
        return series;
      }

}
