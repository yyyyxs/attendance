package com.jmhz.device.util;

/*import com.jasson.im.api.APIClient;
import com.jasson.im.api.MOItem;*/

/**
 * Created by 锋情 on 2014-08-15.
 */
public class SMSUtils {

   /* private static String ip = "211.138.138.82:9016";        //ip地址
//    private static String ip = "211.138.138.82";        //ip地址
//    private static String name = "dbi";                 //用户名
//    private static  String apiId = "dbi";                 //api编码
//    private static   String pwd = "dbi";                  //密码

    //
    private static String name = "gaoqiao";                 //用户名
    private static String apiId = "gaoqiao";                 //api编码
    private static String pwd = "Gaoqiao1234567*";                  //密码

//    String name = "sxgq";                 //用户名
//    String apiId = "sima";                 //api编码
//    String pwd = "Sxgq1234567*";                  //密码

    private static String dbName = "mas";               //数据库名
    public static APIClient apiClient = null;

//    public SMSUtils() {
//        apiClient = new APIClient();
//    }

    static {
        apiClient = new APIClient();
        int connectRe = apiClient.init(ip, name, pwd, apiId, dbName);
        if (connectRe == apiClient.IMAPI_SUCC) {
            System.out.println("IMAPI_SUCC");
        }else if (connectRe == apiClient.IMAPI_CONN_ERR){
            close();
            System.out.println("IMAPI_CONN_ERR"); //连接失败
        } else if (connectRe == apiClient.IMAPI_API_ERR){
            close();
            System.out.println("IMAPI_API_ERR");  //apiID不存在
        }
    }

*//*    public SMSUtils(String ip, String name, String apiId, String pwd, String dbName) {
        this.ip = ip;
        this.name = name;
        this.apiId = apiId;
        this.pwd = pwd;
        this.dbName = dbName;
    }*//*

 *//*   public static int init() {
        apiClient = new APIClient();
        return apiClient.init(ip, name, pwd, apiId, dbName);
    }*//*

    public static int sendSM(String mobile, String content, long smID) {

        return apiClient.sendSM(mobile, content, smID);
    }
    public static int sendSM(String[] mobiles, String content, long smID) {

        return apiClient.sendSM(mobiles, content, smID);
    }

   // public static MOItem[] receiveSM(){
//        MOItem[] moItems1 = new MOItem[2];
//        MOItem mo = new MOItem();
//        mo.setContent("caocaocao");
//        mo.setMobile("18259055729");
//        MOItem mo1 = new MOItem();
//        mo1.setContent("kaokaokao");
//        mo1.setMobile("18259067995");
//        moItems1[0] = mo;
//        moItems1[1] = mo1;
        return apiClient.receiveSM();
//        return moItems1;
    }

    public static void close() {
        apiClient.release();
    }*/

}
