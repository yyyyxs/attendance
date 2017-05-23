package com.jmhz.device.util.timertask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * Created by 锋情 on 2014-08-18.
 */
public class TimerListener implements ServletContextListener{

    private Timer timer = null ;
    //一天时间间隔
    private static final long PERSIOD_DAY = 24 * 60 * 60 * 1000;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        timer = new Timer(true);
//        ReceiveSMTask receiveSMTask = new ReceiveSMTask();

        /*
         30秒钟后开始执行myTask的任务，执行完本次任务后，隔60秒再执行一次
          */
//        timer.schedule(receiveSMTask, 30000,1000 * 60);

/*        *//*
           定制每日夜里2:00执行方法
         *//*
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
//		  //第一次执行定时任务的时间
        Date date = calendar.getTime();
//		 *//**
//         * 如果第一次执行定时任务的时间 小于 当前的时间，
//         * 此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。
//         * 如果不加一天，任务会立即执行
//         *//*
        if (date.before(new Date())) {
            date = this.addDate(date, 1);
        }
        timer.schedule(myTask, date,PERSIOD_DAY);*/

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        timer.cancel();
    }

    private Date addDate(Date date, int i) {
        Calendar starDate = Calendar.getInstance();
        starDate.setTime(date);
        starDate.add(Calendar.DAY_OF_MONTH, i);
        return starDate.getTime();
    }
}
