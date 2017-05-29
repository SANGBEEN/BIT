package bit.com.last;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class BorrowDAO {
	public static BorrowDTO makeBor(ResultSet rs) throws SQLException{
		int c_num=rs.getInt(1);
		int d_num=rs.getInt(2);
		int price = rs.getInt(3);
		Date start_date=rs.getDate(4);
		Date end_date=rs.getDate(5);
		BorrowDTO bor = new BorrowDTO(c_num, d_num, price, start_date, end_date);
		return bor;
	}
	
	public static boolean borrowDVD(CustomerDTO cus, DvdDTO dvd){
		Connection conn=DBUtil.getConnect();
		
		PreparedStatement st = null;
		
		try {
			String sql="insert into borrow values(?,?,?,sysdate,sysdate+7)";
			conn.setAutoCommit(false);
			st=conn.prepareStatement(sql);
			st.setInt(1, cus.getC_num());
			st.setInt(2, dvd.getD_num());
			st.setInt(3, dvd.getPrice());
			st.executeUpdate();
			
			st = null;
			sql="update customer set point=point+? where C_NUM=?";
			st = conn.prepareStatement(sql);
			st.setDouble(1, dvd.getPrice()*0.01);
			st.setInt(2, cus.getC_num());
			st.executeUpdate();
			
			st=null;
			if(dvd.getEnable().equals("Y"))
				sql="update dvd set enable='N' where D_NUM=?";
			else
				sql="update dvd set enable='Y' where D_NUM=?";
			st=conn.prepareStatement(sql);
			st.setInt(1, dvd.getD_num());
			st.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}finally{
			DBUtil.dbclose(conn, st, null);
		}
		
		return true;
	}
	public static boolean borrowDVDusingPoint(CustomerDTO cus, DvdDTO dvd, int point){
		Connection conn=DBUtil.getConnect();
		
		PreparedStatement st = null;
		
		try {
			String sql="update customer set point=? where C_NUM=?";
			conn.setAutoCommit(false);
			st=conn.prepareStatement(sql);
			st.setDouble(1, (cus.getPoint()-point));
			st.setInt(2, cus.getC_num());
			st.executeUpdate();
			
			st=null;
			sql="insert into borrow values(?,?,?,sysdate,sysdate+7)";
			st=conn.prepareStatement(sql);
			st.setInt(1, cus.getC_num());
			st.setInt(2, dvd.getD_num());
			st.setInt(3, dvd.getPrice());
			st.executeUpdate();
			
			st = null;
			sql="update customer set point=point+? where C_NUM=?";
			st = conn.prepareStatement(sql);
			st.setDouble(1, dvd.getPrice()*0.01);
			st.setInt(2, cus.getC_num());
			st.executeUpdate();
			
			st=null;
			if(dvd.getEnable().equals("Y"))
				sql="update dvd set enable='N' where D_NUM=?";
			else
				sql="update dvd set enable='Y' where D_NUM=?";
			st=conn.prepareStatement(sql);
			st.setInt(1, dvd.getD_num());
			st.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}finally{
			DBUtil.dbclose(conn, st, null);
		}
		
		return true;
	}
	public static List<BorrowDTO> borSelectByCnum(int num){
		List<BorrowDTO> borlist = new ArrayList<>();
		Connection conn = DBUtil.getConnect();
		PreparedStatement st = null;
		String sql="select * from borrow where C_B_NUM = ?";
		ResultSet rs = null;
		try {
			st=conn.prepareStatement(sql);
			st.setInt(1,num);
			rs=st.executeQuery();
			while(rs.next()){
				borlist.add(makeBor(rs));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.dbclose(conn, st, rs);
		}
		return borlist;
	}
	public static BorrowDTO borSelectByDnum(int num){
		BorrowDTO bor = null;
		Connection conn = DBUtil.getConnect();
		PreparedStatement st = null;
		String sql="select * from borrow where D_B_NUM = ?";
		ResultSet rs = null;
		try {
			st=conn.prepareStatement(sql);
			st.setInt(1,num);
			rs=st.executeQuery();
			if(rs.next())
				bor=makeBor(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.dbclose(conn, st, rs);
		}
		return bor;
	}
	public static boolean borDelete(DvdDTO dvd){
		Connection conn = DBUtil.getConnect();
		PreparedStatement st = null;
		try {
			conn.setAutoCommit(false);
			String sql="delete from borrow where D_B_NUM = ?";
			st=conn.prepareStatement(sql);
			st.setInt(1,dvd.getD_num());
			st.executeUpdate();
			
			st=null;
			if(dvd.getEnable().equals("Y"))
				sql="update dvd set enable='N' where D_NUM=?";
			else
				sql="update dvd set enable='Y' where D_NUM=?";
			st=conn.prepareStatement(sql);
			st.setInt(1, dvd.getD_num());
			st.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			DBUtil.dbclose(conn, st, null);
		}
		return true;
	}
}
