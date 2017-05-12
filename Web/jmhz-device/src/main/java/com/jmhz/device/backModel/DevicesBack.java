package com.jmhz.device.backModel;

import com.jmhz.device.model.device;

/**
 * Created by fjfzuhqc on 2015/10/27.
 */
public class DevicesBack extends BaseBack{
     device device;  //返回对象为单个设备

    public device getDevice() {
        return device;
    }

    public void setDevice(device device) {
        this.device = device;
    }
}
