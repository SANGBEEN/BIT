package bit.com.last;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class CustomerDAO {
	public static CustomerDTO makeCus(ResultSet rs) throws SQLException{
		int c_num = rs.getInt(1);
		String id = rs.getString(2);
		String password = rs.getString(3);
		String rrn = rs.getString(4);
		String name = rs.getString(5);
		String email = rs.getString(6);
		String address = rs.getString(7);
		String phone = rs.getString(8);
		double point = rs.getDouble(9);
		CustomerDTO cus = new CustomerDTO(c_num, id, password, rrn, name, email, address, phone, point);
		return cus;
	}
	
	public static List<CustomerDTO> cusSelectAll(){
		List<CustomerDTO> cuslist = new ArrayList<>();
		Connection conn = DBUtil.getConnect();
		Statement st = null;
		String sql="select * from customer order by c_num";
		ResultSet rs = null;
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				cuslist.add(makeCus(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.dbclose(conn, st, rs);
		}
		return cuslist;
	}
	public static CustomerDTO cusSelectByName(String name){
		CustomerDTO cus = null;
		Connection conn = DBUtil.getConnect();
		PreparedStatement st = null;
		String sql="select * from customer where name=?";
		ResultSet rs = null;
		try {
			st=conn.prepareStatement(sql);
			st.setString(1, name);
			rs=st.executeQuery();
			if(rs.next()){
				cus=makeCus(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.dbclose(conn, st, rs);
		}
		return cus;
	}
	public static CustomerDTO login(String id, String pass){
		CustomerDTO cus = null;
		Connection conn = DBUtil.getConnect();
		PreparedStatement st = null;
		String sql="select * from customer where id=? and password=?";
		ResultSet rs = null;
		try {
			st=conn.prepareStatement(sql);
			st.setString(1, id);
			st.setString(2, pass);
			rs=st.executeQuery();
			if(rs.next())
				cus=makeCus(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.dbclose(conn, st, rs);
		}
		return cus;
	}
	public static boolean signUp(CustomerDTO cus){
		
		Connection conn=DBUtil.getConnect();
		PreparedStatement st=null;
		String sql="insert into customer values(AAA.NEXTVAL,?,?,?,?,?,?,?,0.0)";
		try {
			st=conn.prepareStatement(sql);
			st.setString(1, cus.getId());
			st.setString(2, cus.getPassword());
			st.setString(3, cus.getRrn());
			st.setString(4, cus.getName());
			st.setString(5, cus.getEmail());
			st.setString(6, cus.getAddress());
			st.setString(7, cus.getPhone());
			
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return false;
		}finally {
			DBUtil.dbclose(conn, st, null);
		}
		return true;
	}
	public static boolean cusDelete(int num){
		Connection conn = DBUtil.getConnect();
		PreparedStatement st = null;
		try {
			String sql="delete from customer where C_NUM = ?";
			st=conn.prepareStatement(sql);
			st.setInt(1,num);
			st.executeUpdate();

			
		}catch(SQLIntegrityConstraintViolationException e){
			//System.out.println(e.getMessage());
			return false;
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}finally{
			DBUtil.dbclose(conn, st, null);
		}
		return true;
	}
	
	
}














