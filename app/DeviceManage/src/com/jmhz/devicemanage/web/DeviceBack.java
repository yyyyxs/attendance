package com.jmhz.devicemanage.web;


import java.util.List;

/**
 * Created by ��� on 2015-04-10.
 */
public class DeviceBack extends BaseBack {
    private int total;//������
    private int curRows;//ÿҳ��¼��
    private int curPage;//��ǰҳ��
    List<Device> devices;//���ض���Ϊ����豸
    Device device;
    public DeviceBack() {
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurRows() {
        return curRows;
    }

    public void setCurRows(int curRows) {
        this.curRows = curRows;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }


	public Device getDevice() {
		return device;
	}


	public void setDevice(Device device) {
		this.device = device;
	}


    
}
