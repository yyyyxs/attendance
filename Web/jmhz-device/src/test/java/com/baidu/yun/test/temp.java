package com.baidu.yun.test;

import com.baidu.yun.core.utility.StringUtility;

import java.util.ArrayList;
import java.util.List;

public class temp {

    public static void main(String[] args) {
        String ts = "10995021999621778018";
//		Long v = Long.parseLong(ts);
//		System.out.println(v);

        String t1 = "{\"name\":\"lilei\", \"name2\":\"hanmeimei\"}";
        System.out.println(t1);

        System.out.println("\"hello\"".replaceAll("\"", "\\\\\""));

        List<String> msgs = new ArrayList<String>();
        msgs.add("\"hello\"");
        msgs.add(t1);
        System.out.println(StringUtility.toJson(msgs));


        String msg = "\"hello\"";
        msg = msg.replaceAll("\"", "\\\\\"");
        System.out.println(msg);
        System.out.println("\"hello\"");
        System.out.println("\"http://developer.baidu.com\"");

        System.out.println(t1.replaceAll("\"", "\\\\\""));

        System.out.println(t1.replaceAll("\"", "\\\\\"").replaceAll("\\\\\"", "\""));

    }

}
