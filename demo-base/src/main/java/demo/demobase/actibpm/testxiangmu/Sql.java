package demo.demobase.actibpm.testxiangmu;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sql {
    private Connection con;
    private PreparedStatement statement;
    private ResultSet re;

    private String driver="com.mysql.jdbc.Driver";
    private String url="jdbc:mysql://localhost:3306/testgms?useUnicode=true&characterEncoding=utf8";
    private String user="root";
    private String pwd="root";

    public void test_conn(){
        try {
            Class.forName(driver);
            con= DriverManager.getConnection(url,user,pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("------------连接成功:"+con);
    }
    public Map<String,String> executionQueryMap(String sql,Object[] objs){
       List<Map<String,String>> list= executionQuery(sql,objs);
       if(list!=null&&list.size()>0){
           return list.get(0);
       }
       return null;
    }
    //执行查询
    public List<Map<String,String>> executionQuery(String sql,Object[] objs){
        test_conn();
        List<Map<String,String>> list=new ArrayList<>();
        try{
            statement=con.prepareStatement(sql);
            if(objs!=null&&objs.length>0){
                for(int i=0;i<objs.length;i++){
                    statement.setObject(i+1,objs[i]);
                }
            }
            re=statement.executeQuery();
            ResultSetMetaData meta=re.getMetaData();
            int col=meta.getColumnCount();
            while(re.next()){
                Map<String,String> linMap=new HashMap<>();
                for(int i=0;i<col;i++){
                    String keyName=meta.getColumnName(i+1).toLowerCase();
                    linMap.put(keyName,re.getObject(keyName)+"");
                }
                list.add(linMap);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        close();
        return list;
    }

    //创建请假条
    public String createQinjiatiao(String stno,String info){
        String findMaixId="select max(id)+1 maxId from sinfo";
        Map<String,String> findMap=executionQueryMap(findMaixId,null);
        String insertSql="insert into sinfo(id,stuo,info) values(?,?,?)";
        try {
            executionSql(insertSql,new Object[]{Integer.parseInt(findMap.get("maxid")),stno,info});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return stno+"_"+findMap.get("maxid");
    }

    //执行sql  非查询
    public boolean executionSql(String sql,Object[] objs) throws Exception{
        test_conn();
        boolean flag=false;
        statement=con.prepareStatement(sql);
        if(objs!=null&&objs.length>0){
            for(int i=0;i<objs.length;i++){
                statement.setObject(i+1,objs[i]);
            }
        }
        flag=statement.execute();
        close();
        return flag;
    }

    public void close(){
        try{
            if(con!=null){
                con.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(re!=null){
                re.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //遍历集合
    public void printList(List<Map<String,String>> list){
        if(list!=null&&list.size()>0){
            for(Map<String,String> map:list){
                System.out.println(map);
            }
        }
    }
}
