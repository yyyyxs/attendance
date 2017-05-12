package com.jmhz.device;

public interface Constants {

    public static final String USERNAME_PATTERN = "^[\\u4E00-\\u9FA5\\uf900-\\ufa2d_a-zA-Z][\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w]{1,19}$";
    public static final String EMAIL_PATTERN = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";
    public static final String MOBILE_PHONE_NUMBER_PATTERN = "^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$";
    public static final int USERNAME_MIN_LENGTH = 2;
    public static final int USERNAME_MAX_LENGTH = 20;
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 50;

    //认证
    public static final String authenticationCacheName = "shiro-authenticationCacheName";
    //授权
    public static final String authorizationCacheName = "shiro-authorizationCacheName";

    public static final String tempRootpath = System.getProperty("user.dir") + "/temp/";
    public static final int excelPageSize = 1000;
    public static final String suffix = ".html";
    public static final String excelext = ".xls";
    public static final String exportexcel = "exportexcel";//是否是导出操作的key
    public static final String dataUpdate = "更新";
    public static final String dataSave = "保存";
    public static final String dataDelete = "删除";
    public static final String cacheKey = "simacache";
    public static final String qxCacheKey = "simaqxcache";
    public static final String tableExt = "_history_";
    public static final String frameTableAlias = "frameTableAlias";


    /**
     * 操作名称
     */
    String OP_NAME = "op";


    /**
     * 消息key
     */
    String MESSAGE = "message";

    /**
     * 错误key
     */
    String ERROR = "error";

    /**
     * 上个页面地址
     */
    String BACK_URL = "BackURL";

    String IGNORE_BACK_URL = "ignoreBackURL";

    /**
     * 当前请求的地址 带参数
     */
    String CURRENT_URL = "currentURL";

    /**
     * 当前请求的地址 不带参数
     */
    String NO_QUERYSTRING_CURRENT_URL = "noQueryStringCurrentURL";

    String CONTEXT_PATH = "ctx";

    /**
     * 当前登录的用户
     */
    String CURRENT_USER = "user";
    String CURRENT_USERNAME = "username";

    String ENCODING = "UTF-8";

    public class SYS_CONFIG_TYPE {

        // 系统配置表sys_config中，短信类型的配置数据，配置类型 type = 0
        public static final int SMS_MESSAGE_TYPES = 0;

    }

    public class SMS_TEMPLATE_TYPES {

        /**
         * 当收到诉求后回复本短信
         */
        public static final String RAR = "rar";
        /**
         * 当诉求处理后回复本短信
         */
        public static final String RAD = "rad";
        /**
         * 单独发送单条短信
         */
        public static final String SINGLE = "single";
        /**
         * 批量发送短信
         */
        public static final String BATCH = "batch";
    }

    /**
     * 短信发送历史查询 查询类型 qrytype
     */
    public class SMS_QUERY_TYPES {
        //党务公开
        /**
         * 领导分工
         */
        public static final int DWGK_0 = 100;
        /**
         * 工作制度
         */
        public static final int DWGK_1 = 101;
        /**
         * 镇党委年度工作计划、年终评议、实绩公示情况
         */
        public static final int DWGK_2 = 102;
        /**
         * 重大决策、重要政策
         */
        public static final int DWGK_3 = 103;
        /**
         * 干部任免、奖惩情况和评先评优情况
         */
        public static final int DWGK_4 = 104;
        /**
         * 党代表的推荐产生、党员发展、民主评议党员
         */
        public static final int DWGK_5 = 105;
        /**
         * 党费收缴使用
         */
        public static final int DWGK_6 = 106;
        /**
         * 党风廉政建设工作
         */
        public static final int DWGK_7 = 107;
        /**
         * 其他
         */
        public static final int DWGK_8 = 108;

        //政务公开
        /**
         * 镇政府年度工作报告
         */
        public static final int ZWGK_0 = 120;
        /**
         * 镇年度财政预算及执行情况
         */
        public static final int ZWGK_1 = 121;
        /**
         * 工程项目招投标及社会公益事业建设情况
         */
        public static final int ZWGK_2 = 122;
        /**
         * 计划生育工作
         */
        public static final int ZWGK_3 = 123;
        /**
         * 村镇建设工作
         */
        public static final int ZWGK_4 = 124;
        /**
         * 财务收支情况
         */
        public static final int ZWGK_5 = 125;
        /**
         * 救灾、救济、扶贫款物分配情况
         */
        public static final int ZWGK_6 = 126;
        /**
         * 集体企业承发包、租赁情况
         */
        public static final int ZWGK_7 = 127;
        /**
         * 收费标准、罚款标准
         */
        public static final int ZWGK_8 = 128;
        /**
         * 其他
         */
        public static final int ZWGK_9 = 129;

        //技术推广
        /**
         * 技术推广
         */
        public static final int JSTG_0 = 140;

        //信息发布
        /**
         * 信息发布
         */
        public static final int XXFB_0 = 160;

        //灾害通知
        /**
         * 灾害通知
         */
        public static final int ZHTZ_0 = 180;
    }

    public class BAIDU_PUSH {
        /**
         * App Settings
         */
        public static final String API_KEY = "jk4F2Ch9iu9iv2Z7llBYWdpa";
        public static final String SECRET_KEY = "MG9yLineT81dXp4QoOLac59xjeaV1cOe";

        /**
         * 应用包名: 包名和AppKey必须与AndroidManifest.xml里配置的保持一致。
         */
        public static final String PACKAGE_NAME = "cn.simastudio.app.device";
    }
}
