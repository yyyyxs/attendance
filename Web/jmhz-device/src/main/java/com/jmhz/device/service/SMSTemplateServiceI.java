package com.jmhz.device.service;

import com.jmhz.device.model.Tsmstemplate;

import java.util.List;

public interface SMSTemplateServiceI {

    public List<Tsmstemplate> findAllSMSTplForCurUser(String username);

    public List<Tsmstemplate> findAllValidSMSTplForCurUserAndName(String username, String tplname);

    public Tsmstemplate findSMSTplById(int tplId);

    public String findSMSTplByTplnameAndValid(String tplname, int isvalid);

    public void updateSMSTpl(Tsmstemplate tsmstemplate);

    public int addSMSTpl(Tsmstemplate tsmstemplate);

    public void deleteSMSTpl(Tsmstemplate tsmstemplate);

}
