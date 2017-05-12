/**
 * Created by fjfzuhqc on 2015/10/19.
 */
function setSecond(obj){
    var val = obj.value;
    var sec = document.getElementById('second');
    if(val == 'time'){
        sec.options[0] = new Option("2010","2010");
        sec.options[1] = new Option("2011","2011");
        sec.options[2] = new Option("2012","2012");
        sec.options[3] = new Option("2013","2013");
        sec.options[4] = new Option("2014","2014");
        sec.options[5] = new Option("2015","2015");
    }else if(val== 'position'){
        sec.options[0] = new Option("301","0");
        sec.options[1] = new Option("302","1");
        sec.options[2] = new Option("303","2");
        sec.options[3] = new Option("304","3");
        sec.options[4] = new Option("305","4");
        sec.options[5] = new Option("306","5");
    }else
    {
        sec.options[0] = new Option("---请选择---","choice2");
        sec.options[1] = new Option();
        sec.options[2] = new Option();
        sec.options[3] = new Option();sec.options[4] = new Option();sec.options[5] = new Option();

    }
}