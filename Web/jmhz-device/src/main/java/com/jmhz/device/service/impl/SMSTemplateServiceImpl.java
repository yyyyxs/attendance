package com.jmhz.device.service.impl;

import com.jmhz.device.dao.SMSTemplateDaoI;
import com.jmhz.device.model.Tsmstemplate;
import com.jmhz.device.service.SMSTemplateServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SMSTemplateServiceImpl implements SMSTemplateServiceI{

    @Autowired
    private SMSTemplateDaoI smsTemplateDao;

    @Override
    public List<Tsmstemplate> findAllSMSTplForCurUser(String username) {
        String hql = "from Tsmstemplate t where t.tplcreator=:creator order by t.tplname desc, t.id asc";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("creator", username);
        return smsTemplateDao.find(hql, params);
    }

    @Override
    public List<Tsmstemplate> findAllValidSMSTplForCurUserAndName(String username, String tplname) {
        String hql = "from Tsmstemplate t where t.tplcreator=:creator and t.isvalid=:isvalid and t.tplname=:tplname order by t.id asc";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("creator", username);
        params.put("isvalid", 1);
        params.put("tplname", tplname);
        return smsTemplateDao.find(hql, params);
    }

    @Override
    public Tsmstemplate findSMSTplById(int tplId) {
        String hql = "from Tsmstemplate t where t.id=:tplId";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tplId", tplId);
        return smsTemplateDao.get(hql, params);
    }

    @Override
    public String findSMSTplByTplnameAndValid(String tplname, int isvalid) {
        String hql = "from Tsmstemplate t where t.tplname=:tplname and t.isvalid=:isvalid";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tplname", tplname);
        // 获得可用模板
        params.put("isvalid", isvalid);
        Tsmstemplate tsmstemplate = smsTemplateDao.get(hql, params);
        if (tsmstemplate != null) {
            return tsmstemplate.getTplcontent();
        } else {
            return "";
        }
    }

    @Override
    public void updateSMSTpl(Tsmstemplate tsmstemplate) {
        smsTemplateDao.update(tsmstemplate);
    }

    @Override
    public int addSMSTpl(Tsmstemplate tsmstemplate) {
        return Integer.parseInt(smsTemplateDao.save(tsmstemplate).toString());
    }

    @Override
    public void deleteSMSTpl(Tsmstemplate tsmstemplate) {
        smsTemplateDao.delete(tsmstemplate);
    }

}
