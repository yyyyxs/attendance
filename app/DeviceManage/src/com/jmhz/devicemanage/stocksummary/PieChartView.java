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
		renderer.setLegendTextSize(30);// 设置左下角表注的文字大小
		// renderer.setZoomButtonsVisible(true);//设置显示放大缩小按钮
		renderer.setZoomEnabled(false);// 设置不允许放大缩小.
		renderer.setChartTitleTextSize(30);// 设置图表标题的文字大小
		renderer.setChartTitle(mTitle);// 设置图表的标题 默认是居中顶部显示
		renderer.setLabelsTextSize(30);// 饼图上标记文字的字体大小
		renderer.setLabelsColor(Color.GRAY);//饼图上标记文字的颜色
		renderer.setPanEnabled(false);// 设置是否可以平移
		renderer.setDisplayValues(true);//是否显示值
		renderer.setClickEnabled(true);// 设置是否可以被点击
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
