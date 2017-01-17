package initdata.excel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DBManageMysql {
	public static Connection conn = JDBCForMySql.getConnection();
	/**
	 * �������
	 * @param insertsql
	 * @return
	 */
     public static boolean saveDataBySql(String insertsql){
    	 Statement statement = null;
    	 boolean result = false;
    	 try{
    		statement = conn.createStatement();
    	    statement.execute(insertsql);
    	    result =true;
    	 }catch(Exception e){
    		 e.printStackTrace();
    		 result =false;
    	 }finally{
    		 try{
    			 statement.close();
    		 }catch(Exception e){
    			 result =false;
    		 }
    	 }
    	 if (result){
    		 System.out.println("������ݳɹ�-->"+insertsql);
    	 }else{
    		 System.out.println("�������ʧ��-->"+insertsql);
    	 }
    	 return result;
     }
     public static boolean saveDataByBatchSql(List<String> batchsql){
    	 Statement statement = null;
    	 boolean result = false;
    	 try{
    		 statement = conn.createStatement();
    		 for(String str:batchsql){
    			 statement.addBatch(str);
    		 }
    		 statement.executeBatch();
    		 result =true;
    	 }catch(Exception e){
    		 e.printStackTrace();
    		 result =false;
    	 }finally{
    		 try{
    			 statement.close();
    		 }catch(Exception e){
    			 result =false;
    		 }
    	 }
    	 if (result){
    		 System.out.println("������ݳɹ�-->");
    	 }else{
    		 System.out.println("�������ʧ��-->");
    	 }
    	 return result;
     }
     /**
      * ��ѯ���
      * @param findsql
      */
     public static List<HashMap> queryDataBySql(String findsql){
    	 Statement statement = null;
    	 ResultSet rs = null;
    	 List<HashMap> relist = new ArrayList<HashMap>();
    	 try{
    		 statement = conn.createStatement();
    		 rs = statement.executeQuery(findsql);
    		 ResultSetMetaData meta =  rs.getMetaData();
    		 while(rs.next()){
    			 HashMap hm = new HashMap();
    			 for(int i=1;i<=meta.getColumnCount();i++){
    				 String fieldname = meta.getColumnLabel(i).toLowerCase();
    				 String fieldvalue = rs.getString(fieldname);
    				 hm.put(fieldname, fieldvalue);
    			 }
    			 relist.add(hm);
    		 }
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }finally{
    		 try{
    			 rs.close();
    			 statement.close();
    		 }catch(Exception e){}
    	 }
    	 return relist;
     }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sql = "select * from t_xt_userinfo ";
		List<HashMap> result = DBManageMysql.queryDataBySql(sql);
		for (HashMap mp:result){
			System.out.println(mp.get("username")+"\t"+mp.get("usercode")+"\t"+mp.get("loginid"));
		}
         System.out.println("l��ok"+result.size());
	}
    
}
