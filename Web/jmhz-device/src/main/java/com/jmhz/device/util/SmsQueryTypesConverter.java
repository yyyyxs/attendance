package com.jmhz.device.util;

import com.jmhz.device.Constants;

/**
 * Copyright: Copyright (c) 2013-2015 SimaStudio
 * Usage:
 *
 * @author Charkey
 * @date 2015/4/21 10:25
 */
public class SmsQueryTypesConverter {

    //党务公开
    public static final String DWGK_0 = "领导分工";
    public static final String DWGK_1 = "工作制度";
    public static final String DWGK_2 = "镇党委年度工作计划、年终评议、实绩公示情况";
    public static final String DWGK_3 = "重大决策、重要政策";
    public static final String DWGK_4 = "干部任免、奖惩情况和评先评优情况";
    public static final String DWGK_5 = "党代表的推荐产生、党员发展、民主评议党员";
    public static final String DWGK_6 = "党费收缴使用";
    public static final String DWGK_7 = "党风廉政建设工作";
    public static final String DWGK_8 = "其他";
    //政务公开
    public static final String ZWGK_0 = "镇政府年度工作报告";
    public static final String ZWGK_1 = "镇年度财政预算及执行情况";
    public static final String ZWGK_2 = "工程项目招投标及社会公益事业建设情况";
    public static final String ZWGK_3 = "计划生育工作";
    public static final String ZWGK_4 = "村镇建设工作";
    public static final String ZWGK_5 = "财务收支情况";
    public static final String ZWGK_6 = "救灾、救济、扶贫款物分配情况";
    public static final String ZWGK_7 = "集体企业承发包、租赁情况";
    public static final String ZWGK_8 = "收费标准、罚款标准";
    public static final String ZWGK_9 = "其他";
    //技术推广
    public static final String JSTG_0 = "技术推广";
    //信息发布
    public static final String XXFB_0 = "信息发布";
    //灾害通知
    public static final String ZHTZ_0 = "灾害通知";

    /**
     * 根据两个字符串类型值转换获得一个数字型的查询值
     *
     * @param parentType 字符串
     * @param childType  字符串
     * @return int
     */
    public static int getQueryTypes(String parentType, String childType) {
        if (parentType.equals("党务公开")) {
            if (childType.equals(DWGK_0)) {
                return Constants.SMS_QUERY_TYPES.DWGK_0;
            } else if (childType.equals(DWGK_1)) {
                return Constants.SMS_QUERY_TYPES.DWGK_1;
            } else if (childType.equals(DWGK_2)) {
                return Constants.SMS_QUERY_TYPES.DWGK_2;
            } else if (childType.equals(DWGK_3)) {
                return Constants.SMS_QUERY_TYPES.DWGK_3;
            } else if (childType.equals(DWGK_4)) {
                return Constants.SMS_QUERY_TYPES.DWGK_4;
            } else if (childType.equals(DWGK_5)) {
                return Constants.SMS_QUERY_TYPES.DWGK_5;
            } else if (childType.equals(DWGK_6)) {
                return Constants.SMS_QUERY_TYPES.DWGK_6;
            } else if (childType.equals(DWGK_7)) {
                return Constants.SMS_QUERY_TYPES.DWGK_7;
            } else if (childType.equals(DWGK_8)) {
                return Constants.SMS_QUERY_TYPES.DWGK_8;
            }
        } else if (parentType.equals("政务公开")) {
            if (childType.equals(ZWGK_0)) {
                return Constants.SMS_QUERY_TYPES.ZWGK_0;
            } else if (childType.equals(ZWGK_1)) {
                return Constants.SMS_QUERY_TYPES.ZWGK_1;
            } else if (childType.equals(ZWGK_2)) {
                return Constants.SMS_QUERY_TYPES.ZWGK_2;
            } else if (childType.equals(ZWGK_3)) {
                return Constants.SMS_QUERY_TYPES.ZWGK_3;
            } else if (childType.equals(ZWGK_4)) {
                return Constants.SMS_QUERY_TYPES.ZWGK_4;
            } else if (childType.equals(ZWGK_5)) {
                return Constants.SMS_QUERY_TYPES.ZWGK_5;
            } else if (childType.equals(ZWGK_6)) {
                return Constants.SMS_QUERY_TYPES.ZWGK_6;
            } else if (childType.equals(ZWGK_7)) {
                return Constants.SMS_QUERY_TYPES.ZWGK_7;
            } else if (childType.equals(ZWGK_8)) {
                return Constants.SMS_QUERY_TYPES.ZWGK_8;
            } else if (childType.equals(ZWGK_9)) {
                return Constants.SMS_QUERY_TYPES.ZWGK_9;
            }
        } else if (parentType.equals("技术推广")) {
            if (childType.equals(JSTG_0)) {
                return Constants.SMS_QUERY_TYPES.JSTG_0;
            }
        } else if (parentType.equals("信息发布")) {
            if (childType.equals(XXFB_0)) {
                return Constants.SMS_QUERY_TYPES.XXFB_0;
            }
        } else if (parentType.equals("灾害通知")) {
            if (childType.equals(ZHTZ_0)) {
                return Constants.SMS_QUERY_TYPES.ZHTZ_0;
            }
        }
        return 0;
    }

    /**
     * 根据qrytype反查其parentType，childType
     *
     * @param qrytype 通过parentType和childType确定的qrytype
     * @return 字符串数组
     */
    public static String[] getParentChildType(int qrytype) {
        String[] parentChildType = new String[2];

        // 党务公开
        if (qrytype >= Constants.SMS_QUERY_TYPES.DWGK_0 && qrytype <= Constants.SMS_QUERY_TYPES.DWGK_8) {
            parentChildType[0] = "党务公开";
            if (qrytype == Constants.SMS_QUERY_TYPES.DWGK_0) {
                parentChildType[1] = DWGK_0;
            } else if (qrytype == Constants.SMS_QUERY_TYPES.DWGK_1) {
                parentChildType[1] = DWGK_1;
            } else if (qrytype == Constants.SMS_QUERY_TYPES.DWGK_2) {
                parentChildType[1] = DWGK_2;
            } else if (qrytype == Constants.SMS_QUERY_TYPES.DWGK_3) {
                parentChildType[1] = DWGK_3;
            } else if (qrytype == Constants.SMS_QUERY_TYPES.DWGK_4) {
                parentChildType[1] = DWGK_4;
            } else if (qrytype == Constants.SMS_QUERY_TYPES.DWGK_5) {
                parentChildType[1] = DWGK_5;
            } else if (qrytype == Constants.SMS_QUERY_TYPES.DWGK_6) {
                parentChildType[1] = DWGK_6;
            } else if (qrytype == Constants.SMS_QUERY_TYPES.DWGK_7) {
                parentChildType[1] = DWGK_7;
            } else if (qrytype == Constants.SMS_QUERY_TYPES.DWGK_8) {
                parentChildType[1] = DWGK_8;
            }
            //    政务公开
        } else if (qrytype >= Constants.SMS_QUERY_TYPES.ZWGK_0 && qrytype <= Constants.SMS_QUERY_TYPES.ZWGK_9) {
            parentChildType[0] = "政务公开";
            if (qrytype == Constants.SMS_QUERY_TYPES.ZWGK_0) {
                parentChildType[1] = ZWGK_0;
            } else if (qrytype == Constants.SMS_QUERY_TYPES.ZWGK_1) {
                parentChildType[1] = ZWGK_1;
            } else if (qrytype == Constants.SMS_QUERY_TYPES.ZWGK_2) {
                parentChildType[1] = ZWGK_2;
            } else if (qrytype == Constants.SMS_QUERY_TYPES.ZWGK_3) {
                parentChildType[1] = ZWGK_3;
            } else if (qrytype == Constants.SMS_QUERY_TYPES.ZWGK_4) {
                parentChildType[1] = ZWGK_4;
            } else if (qrytype == Constants.SMS_QUERY_TYPES.ZWGK_5) {
                parentChildType[1] = ZWGK_5;
            } else if (qrytype == Constants.SMS_QUERY_TYPES.ZWGK_6) {
                parentChildType[1] = ZWGK_6;
            } else if (qrytype == Constants.SMS_QUERY_TYPES.ZWGK_7) {
                parentChildType[1] = ZWGK_7;
            } else if (qrytype == Constants.SMS_QUERY_TYPES.ZWGK_8) {
                parentChildType[1] = ZWGK_8;
            } else if (qrytype == Constants.SMS_QUERY_TYPES.ZWGK_9) {
                parentChildType[1] = ZWGK_9;
            }
        }
        return parentChildType;
    }
}
