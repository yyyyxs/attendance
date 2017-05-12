package com.jmhz.device.util;


import com.jmhz.device.constants.AuthorityConstants;
import com.jmhz.device.sys.entity.User;

import java.util.Map;

/**
 * Created by cfw on 2014/8/21.
 */
public class AuthorityUtils {

    public static String AUTH_SQL_CITY = " t.city = :city ";
    public static String AUTH_SQL_TOWN = AUTH_SQL_CITY + "and t.town = :town ";
    public static String AUTH_SQL_VILLAGE = AUTH_SQL_TOWN + "and t.village = :village ";
    public static String AUTH_SQL_GRID = AUTH_SQL_VILLAGE + "and t.grid = :grid ";

    public static String ORDER_BY_VILLAGE = " order by t.town , t.village, t.id desc";
    public static String ORDER_BY_GRID = " order by t.town , t.village , t.grid , t.id desc";

    /**
     * 拼接数据库记录级别的查询条件
     * 要求：数据表的别名必须为t，且目前仅支持单表操作
     *
     * @param user
     * @param params
     * @param originalSql
     * @return
     */
    public static String getAuthoritySql(User user, Map<String, Object> params, String originalSql) {

        String sql;
        StringBuffer sqlStringBuffer = new StringBuffer(originalSql);
        /*
        获取条件语句
         */
        String authoritySql;
        switch (user.getAuth_level()) {

            case AuthorityConstants.AUTH_LEVEL_ALL: {
                authoritySql = AUTH_SQL_CITY;
                params.put("city", user.getCity());
                break;
            }
            case AuthorityConstants.AUTH_LEVEL_CITY: {
                authoritySql = AUTH_SQL_CITY;
                params.put("city", user.getCity());
                break;
            }

            case AuthorityConstants.AUTH_LEVEL_TOWN: {
                authoritySql = AUTH_SQL_TOWN;
                params.put("city", user.getCity());
                params.put("town", user.getTown());
                break;
            }

//            case AuthorityConstants.AUTH_LEVEL_VILLAGE: {
//                authoritySql = AUTH_SQL_VILLAGE;
//                params.put("city", user.getCity());
//                params.put("town", user.getTown());
//                params.put("village", user.getVillage());
//                break;
//            }
//
//            case AuthorityConstants.AUTH_LEVEL_GRID: {
//                authoritySql = AUTH_SQL_GRID;
//                params.put("city", user.getCity());
//                params.put("town", user.getTown());
//                params.put("village", user.getVillage());
//                params.put("grid", user.getGrid());
//                break;
//            }
            default: {
                authoritySql = "";
                break;
            }
        }

        /*
        拼接sql语句
         */
        /*
        originalSql 包含 where
         */
        if (originalSql.indexOf("where") >= 0) {

            authoritySql = " AND" + authoritySql;
        } else {
            authoritySql = " WHERE" + authoritySql;
        }
        /*
        originalSql 包含 order
         */
        if (originalSql.indexOf("order") >= 0) {
            sqlStringBuffer.insert(originalSql.indexOf("order"), authoritySql);
            sql = sqlStringBuffer.toString();
        } else {
            sql = originalSql + authoritySql;
        }
        return sql;
    }
}
