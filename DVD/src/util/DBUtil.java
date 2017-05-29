package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

   public static Connection getConnect(){
      
      Connection conn = null;
      String url="jdbc:oracle:thin:@192.168.1.35:1521:xe";
      String id="hr";
      String pass="hr";
      
      try {
         Class.forName("oracle.jdbc.OracleDriver");
         conn = DriverManager.getConnection(url, id, pass);
      } catch (ClassNotFoundException e) {
    	  System.out.println(e.getMessage());
    	  e.printStackTrace();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      return conn;
   }
   
   public static void dbclose(Connection conn, Statement st, ResultSet rs){
      try {
         if(rs!=null) rs.close();
         if(st!=null) st.close();
         if(conn!=null) conn.close();
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
   }
}