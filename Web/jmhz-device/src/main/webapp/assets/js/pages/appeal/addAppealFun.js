$(function(){
    var path = window.location.pathname.split("/")[0];
    // 日期插件
    $('#birthday').mask('9999年99月');

    //下面是cxselect的配置
    //先引入数据和cxselect的js，jquery最先引入
    //然后是下面的配置
    //设置全局默认值，引入jquery.cxselect.js之后，调用之前设置
    $.cxSelect.defaults.url = path + "/assets/js/jquery-cxselect/cityData.min.jsonData.js";
    $.cxSelect.defaults.nodata = "none";
    // selects 为数组形式，请注意顺序
    $("#snackLocation").cxSelect({
        selects: ["province", "city", "area"],
        nodata: "none"
    });
    $("#workLocation").cxSelect({
        selects: ["province", "city", "area"],
        nodata: "none"
    });
    //上面是cxselect的配置
});