package com.jmhz.device.util;

import com.jmhz.device.pageModel.FilterRule;

import java.util.Map;


public class SearchUtil {

    public static StringBuilder getHql(StringBuilder stringBuilder, FilterRule filterRule, Map<String, Object> params) {

        //判断rules 中的操作 op
        if (filterRule.getOp().equals("eq")) {
            if (filterRule.getField().equals("id")) {
                params.put(filterRule.getField(), Integer.parseInt(filterRule.getData()));
            } else {
                params.put(filterRule.getField(), filterRule.getData());
            }

            stringBuilder.append("t." + filterRule.getField() + " = " + ":" + filterRule.getField() + " and ");
        } else if (filterRule.getOp().equals("ne")) {
            if (filterRule.getField().equals("id")) {
                params.put(filterRule.getField(), Integer.parseInt(filterRule.getData()));
            } else {
                params.put(filterRule.getField(), filterRule.getData());
            }
            stringBuilder.append("t." + filterRule.getField() + " != " + ":" + filterRule.getField() + " and ");
        } else if (filterRule.getOp().equals("lt")) {
            if (filterRule.getField().equals("id")) {
                params.put(filterRule.getField(), Integer.parseInt(filterRule.getData()));
            } else {
                params.put(filterRule.getField(), filterRule.getData());
            }
            stringBuilder.append("t." + filterRule.getField() + " < " + ":" + filterRule.getField() + " and ");
        } else if (filterRule.getOp().equals("le")) {
            if (filterRule.getField().equals("id")) {
                params.put(filterRule.getField(), Integer.parseInt(filterRule.getData()));
            } else {
                params.put(filterRule.getField(), filterRule.getData());
            }
            stringBuilder.append("t." + filterRule.getField() + " <= " + ":" + filterRule.getField() + " and ");
        } else if (filterRule.getOp().equals("gt")) {
            if (filterRule.getField().equals("id")) {
                params.put(filterRule.getField(), Integer.parseInt(filterRule.getData()));
            } else {
                params.put(filterRule.getField(), filterRule.getData());
            }
            stringBuilder.append("t." + filterRule.getField() + " > " + ":" + filterRule.getField() + " and ");
        } else if (filterRule.getOp().equals("ge")) {
            if (filterRule.getField().equals("id")) {
                params.put(filterRule.getField(), Integer.parseInt(filterRule.getData()));
            } else {
                params.put(filterRule.getField(), filterRule.getData());
            }
            stringBuilder.append("t." + filterRule.getField() + " >= " + ":" + filterRule.getField() + " and ");
        } else if (filterRule.getOp().equals("nu")) {
            stringBuilder.append("t." + filterRule.getField() + " is null" + " and ");
        } else if (filterRule.getOp().equals("nn")) {
            stringBuilder.append("t." + filterRule.getField() + " is not null" + " and ");
        } else if (filterRule.getOp().equals("in")) {
            params.put(filterRule.getField(), "%" + filterRule.getData() + "%");
            stringBuilder.append("t." + filterRule.getField() + " like " + ":" + filterRule.getField() + " and ");
            // stringBuilder.append("t."+filterRule.getField()+" like "+"\'%"+"一"+"%\'"+" and ");
        } else if (filterRule.getOp().equals("ni")) {
            if (filterRule.getField().equals("id")) {
                params.put(filterRule.getField(), Integer.parseInt(filterRule.getData()));
            } else {
                params.put(filterRule.getField(), filterRule.getData());
            }
            stringBuilder.append("t." + filterRule.getField() + " not in (" + ":" + filterRule.getField() + ")" + " and ");
        }


        return stringBuilder;
    }
}
