package initdata.excel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.jeecg.util.excel.ExcelUtil;

public class ExcelTool extends DBManageMysql
{
    public static void importArticleInfo(){
        List<String[]> datalist = new ArrayList<String[]>();
        try
        {
            datalist = ExcelUtil.readExcel("c:\\品种信息.xls");
        }
        catch (Exception e)
        {
            datalist = null;
            e.printStackTrace();
        }
        List<String> sqlList = new ArrayList<String>();
        sqlList.add("delete from t_xx_article ");
        if (datalist!=null&&datalist.size()>0){
            int count = -1;
            for (String[] row:datalist){
                count++;
                if (count==0) continue;
                String articleid = getArticleKey(count+"");
                String articlename = row[1].trim().replace(" ","");
                String aliasname = ChnToPinYin.getPYString(row[1].trim().replace(" ",""));
                String factory = row[2].trim().replace(" ","");;
                String articlespec = row[3].trim().replace(" ","");
                String unit = "01";//01盒 02袋 03瓶 04包
                String retaiprice = row[4].trim();
                if (articlespec.indexOf("盒")>-1){
                    unit = "01";
                }
                if (articlespec.indexOf("袋")>-1){
                    unit = "04";
                }
                if (articlespec.indexOf("瓶")>-1||articlespec.indexOf("ml")>-1){
                    unit = "03";
                }
                if (articlespec.indexOf("包")>-1){
                    unit = "04";
                }
                String catelog = "02";//中成药
                String pfprice = "0";
                if (row.length==6)
                pfprice = (row[5]==null||row[5].trim().equals(""))?"0":row[5].trim();
                String sql = "insert into t_xx_article(articleid,articlename" +
                		",articlealias,articlespec,factory,pfprice,retailprice,ordernum,createdate,modifydate,unit,catelog) " +
                		" values ('"+articleid+"','"+articlename+"','"+aliasname+"','"+articlespec+"'" +
                		",'"+factory+"',"+pfprice+","+retaiprice+","+count+",now(),now(),'"+unit+"','"+catelog+"')";
                System.out.println(sql);
                sqlList.add(sql);
            }
            System.out.println("开始插入品种信息...");
            saveDataByBatchSql(sqlList);
            System.out.println("插入["+sqlList.size()+"]品种信息成功！");
        }
    }
    public static String getArticleKey(String num){
        String key = num;
        while(key.length()<5){
            key = "0"+key;
        }
        return key;
    }
    public static void main(String[] args)
    {
        importArticleInfo();
    }
}
