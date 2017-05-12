package com.jmhz.device.util;

import com.jmhz.device.model.Tsmoriginalappeal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by cfw on 2014/8/19.
 */
public class JDBCUtils {


    // 创建静态全局变量
    static Connection conn;

    static PreparedStatement pst;
    private static Logger logger = LoggerFactory.getLogger(JDBCUtils.class);
    /* 获取数据库连接的函数*/
    public static Connection getConnection() {
        Connection con = null;  //创建用于连接数据库的Connection对象
        try {
            Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动

            //TODO:数据库信息 从配置文件读取
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gaoqiao?useUnicode=true&characterEncoding=utf8", "gaoqiao", "3vV2YL8eeugQZZ");// 创建数据连接

        } catch (Exception e) {
            logger.error("数据库连接失败" + e.getMessage());
        }
        return con; //返回所建立的数据库连接
    }

    /* 插入数据记录，并输出插入的数据记录数*/
    public static int inserOriginalappeal(String sql, Tsmoriginalappeal tsmo) {

        conn = getConnection(); // 首先要获取连接，即连接到数据库
        try {
            //INSERT INTO tsmoriginalappeal(tel, content，createtime) VALUES (?, ?，?)
            pst = conn.prepareStatement(sql);    // 创建用于执行静态sql语句的Statement对象
            pst.setString(1, tsmo.getTel());
            pst.setString(2, tsmo.getContent());
            pst.setDate(3, new Date(System.currentTimeMillis()));
            int count = pst.executeUpdate();  // 执行插入操作的sql语句，并返回插入数据的个数

//            logger.error("向Tsmoriginalappeal表中插入 " + count + " 条数据"); //输出插入操作的处理结果

            conn.close();   //关闭数据库连接
            return count;
        } catch (SQLException e) {
            logger.error("插入数据失败" + e.getMessage());
        }
        return 0;
    }

    public static int inserSmssent(String sql, String tplname, String smsto, String smscontent) {

        conn = getConnection(); // 首先要获取连接，即连接到数据库
        try {
            //"INSERT INTO tsmssent(tplname, smsto, smscontent, senddate) VALUES (?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);    // 创建用于执行静态sql语句的Statement对象
            pst.setString(1, tplname);
            pst.setString(2, smsto);
            pst.setString(3, smscontent);
            pst.setDate(4, new Date(System.currentTimeMillis()));
            int count = pst.executeUpdate();  // 执行插入操作的sql语句，并返回插入数据的个数

            logger.error("向Tsmssent表中插入 " + count + " 条数据"); //输出插入操作的处理结果

            conn.close();   //关闭数据库连接
            return count;
        } catch (SQLException e) {
            logger.error("插入数据失败" + e.getMessage());
        }
        return 0;
    }


}
