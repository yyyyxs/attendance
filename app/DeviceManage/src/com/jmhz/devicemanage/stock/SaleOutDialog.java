package com.jmhz.devicemanage.stock;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.jmhz.devicemanage.R;

public class SaleOutDialog extends AlertDialog {
    Context context;
    public SaleOutDialog(Context context) {
        super(context);
        this.context = context;
    }
    public SaleOutDialog(Context context, int theme){
        super(context, theme);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.toast_sign_out);
    }

}