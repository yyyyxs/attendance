package com.jmhz.device.pageModel;

/**
 * JSON模型
 * <p/>
 * 用户后台向前台返回的JSON对象
 */
public class JsonModel implements java.io.Serializable {

    //请求是否成功
    private boolean success = false;
    //返回消息
    private String msg = "";
    //请求返回内容
    private Object dataObj = null;
    //请求返回内容总页数
    private int totalpages = 0;
    //请求返回内容当前页
    private int currentpage = 0;
    //请求返回内容总记录数
    private  int totalrecords = 0;
    //请求内容分页大小
    private int pagerows = 10;
    //请求内容排序字段
    private String sort;
    //请求内容排序方式
    private String order;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getDataObj() {
        return dataObj;
    }

    public void setDataObj(Object dataObj) {
        this.dataObj = dataObj;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int totalpages) {
        this.totalpages = totalpages;
    }

    public int getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    public int getTotalrecords() {
        return totalrecords;
    }

    public void setTotalrecords(int totalrecords) {
        this.totalrecords = totalrecords;
    }

    public int getPagerows() {
        return pagerows;
    }

    public void setPagerows(int pagerows) {
        this.pagerows = pagerows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }


}
