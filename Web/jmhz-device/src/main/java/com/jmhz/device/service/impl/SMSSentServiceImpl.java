package com.jmhz.device.service.impl;

import com.jmhz.device.dao.SMSSentDaoI;
import com.jmhz.device.model.Tsmssent;
import com.jmhz.device.service.SMSSentServiceI;
import com.jmhz.device.util.SearchUtil;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.util.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SMSSentServiceImpl implements SMSSentServiceI {

    @Autowired
    private SMSSentDaoI smsSentDao;

    @Override
    public List<Tsmssent> getAllSMSSent(int page, int rows, int qrytype) {
        //党务公开
        if (qrytype == 10) {
            String hql = "from Tsmssent t where t.qrytype in (100,101,102,103,104,105,106,107,108) order by t.senddate desc";
            return smsSentDao.find(hql, page, rows);
        //政务公开
        } else if (qrytype == 20) {
            String hql = "from Tsmssent t where t.qrytype in (120,121,122,123,124,125,126,127,128,129) order by t.senddate desc";
            return smsSentDao.find(hql, page, rows);
        } else {
            String hql = "from Tsmssent t order by t.senddate desc";
            return smsSentDao.find(hql, page, rows);
        }
    }

    @Override
    public Long getCount(int qrytype) {
        if (qrytype == 10) {
            String hql = "select count(*) from Tsmssent t where t.qrytype in (100,101,102,103,104,105,106,107,108)";
            return smsSentDao.count(hql);
            //政务公开
        } else if (qrytype == 20) {
            String hql = "select count(*) from Tsmssent t where t.qrytype in (120,121,122,123,124,125,126,127,128,129)";
            return smsSentDao.count(hql);
        } else {
            return smsSentDao.count("select count(*) from Tsmssent");
        }
    }

    @Override
    public List<Tsmssent> getAllSMSSent(int page, int rows, Filters filtersClass) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from Tsmssent t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {
            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);
        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " order by t.id desc";
        return smsSentDao.find(hql, params, page, rows);
    }

    @Override
    public Long getCount(Filters filtersClass) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from Tsmssent t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {
            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);
        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        return smsSentDao.count(hql, params);
    }

    @Override
    public void delSMSSent(Tsmssent tsmssent) {
        smsSentDao.delete(tsmssent);
    }

    @Override
    public int add(Tsmssent tsmssent) {
        Serializable id = smsSentDao.save(tsmssent);
        if (id == null) {
            return -1;
        }
        return Integer.parseInt(id.toString());
    }

    @Override
    public boolean addJDBC(String tplname, String smsto, String smscontent) {
        String sql = "INSERT INTO tsmssent(tplname, smsto, smscontent, senddate) VALUES (?, ?, ?, ?)";

        int res = JDBCUtils.inserSmssent(sql, tplname, smsto, smscontent);
        if (res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Tsmssent> getSMSSent(int page, int rows, int qrytype) {
        String hql = "from Tsmssent t where t.qrytype=:qrytype and t.ispublic=:ispublic order by t.id desc";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("qrytype", qrytype);
        paramMap.put("ispublic", 1);
        return smsSentDao.find(hql, paramMap, page, rows);
    }

    @Override
    public Long getSMSSentCount(int qrytype) {
        String hql = "select count(*) from Tsmssent t where t.qrytype=:qrytype and t.ispublic=:ispublic";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("qrytype", qrytype);
        paramMap.put("ispublic", 1);
        return smsSentDao.count(hql, paramMap);
    }

    @Override
    public List<Tsmssent> getPublicSMSSent(int page, int rows) {
        String hql = "from Tsmssent t where t.ispublic=:ispublic and t.qrytype in (100,101,102,103,104,105,106,107,108,120,121,122,123,124,125,126,127,128,129) order by t.id desc";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ispublic", 1);
        return smsSentDao.find(hql, paramMap, page, rows);
    }

    @Override
    public Long getPublicSMSSentCount() {
        String hql = "select count(*) from Tsmssent t where t.ispublic=:ispublic and t.qrytype in (100,101,102,103,104,105,106,107,108,120,121,122,123,124,125,126,127,128,129)";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ispublic", 1);
        return smsSentDao.count(hql, paramMap);
    }

    @Override
    public List<Tsmssent> getPublicDWForIndex(int page, int rows) {
        String hql = "from Tsmssent t where t.ispublic=:ispublic and t.qrytype in (100,101,102,103,104,105,106,107,108) order by t.id desc";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ispublic", 1);
        return smsSentDao.find(hql, paramMap, page, rows);
    }

    @Override
    public List<Tsmssent> getPublicZWForIndex(int page, int rows) {
        String hql = "from Tsmssent t where t.ispublic=:ispublic and t.qrytype in (120,121,122,123,124,125,126,127,128,129) order by t.id desc";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ispublic", 1);
        return smsSentDao.find(hql, paramMap, page, rows);
    }
}
