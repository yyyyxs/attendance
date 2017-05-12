package com.jmhz.device.util;

/**
 * Created by 锋情 on 2015-04-12.
 */
public class AppealUtils {

    /**
     * 诉求类型转换
     *
     * @param typecode
     * @return
     */
    public static String getAppealTypeByCode(String typecode) {
        String appealType = "其他";
        if (typecode.equals("0")) {
            appealType = "发展生产";
        } else if (typecode.equals("1")) {
            appealType = "基础设施";
        } else if (typecode.equals("2")) {
            appealType = "矛盾纠纷";
        } else if (typecode.equals("3")) {
            appealType = "社会治安";
        } else if (typecode.equals("4")) {
            appealType = "生活救助";
        } else if (typecode.equals("5")) {
            appealType = "征地拆迁";
        } else if (typecode.equals("6")) {
            appealType = "政策咨询";
        }else if (typecode.equals("7")) {
            appealType = "证照办理";
        }else if (typecode.equals("8")) {
            appealType = "群众投诉";
        }else if (typecode.equals("9")) {
            appealType = "其他";
        }
        return appealType;
    }

    /**
     * 事务类别转换
     *
     * @param typecode
     * @return
     */
    public static String getAffairTypeByCode(String typecode) {
        String affairType = "个人事务";
        if (typecode.equals("0")) {
            affairType = "个人事务";
        } else if (typecode.equals("1")) {
            affairType = "集体事务";
        }
        return affairType;
    }
    /**
     * 状态类别转换
     *
     * @param typecode
     * @return
     */
    public static String getStatusTypeByCode(String typecode) {
        String statusType = "未解决";
        if (typecode.equals("0")) {
            statusType = "未解决";
        } else if (typecode.equals("1")) {
            statusType = "以上报上级协调解决";
        } else if (typecode.equals("2")) {
            statusType = "延时解决";
        } else if (typecode.equals("3")) {
            statusType = "正在解决";
        } else if (typecode.equals("4")) {
            statusType = "已做好解释说明工作";
        } else if (typecode.equals("5")) {
            statusType = "已解决";
        }
        return statusType;
    }
}
