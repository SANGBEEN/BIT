package bit.com.last;

import java.security.KeyStore.ProtectionParameter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import util.DateUtil;

public class DvdDAO {
	public static DvdDTO makeDVD(ResultSet rs) throws SQLException{
		int d_num=rs.getInt(1);
		String director = rs.getString(2);
		Date release_date = rs.getDate(3);
		String title = rs.getString(4);
		String genre = rs.getString(5);
		int price = rs.getInt(6);
		String rating = rs.getString(7);
		String actor = rs.getString(8);
		String enable = rs.getString(9);
		DvdDTO dvd =  new DvdDTO(d_num, director, release_date, title, genre, price, rating, actor,enable);
		return dvd;
	}
	public static List<DvdDTO> dvdSelectAll(){
		List<DvdDTO> dvdlist = new ArrayList<>();
		Connection conn = DBUtil.getConnect();
		Statement st = null;
		String sql="select * from dvd order by title";
		ResultSet rs = null;
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				dvdlist.add(makeDVD(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.dbclose(conn, st, rs);
		}
		return dvdlist;
	}
	public static List<DvdDTO> dvdSelectByDirector(String director){
		List<DvdDTO> dvdlist = new ArrayList<>();
		Connection conn = DBUtil.getConnect();
		PreparedStatement st = null;
		String sql="select * from dvd where director like ? order by title";
		ResultSet rs = null;
		try {
			st=conn.prepareStatement(sql);
			st.setString(1, "%"+director+"%");
			rs=st.executeQuery();
			while(rs.next()){
				dvdlist.add(makeDVD(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.dbclose(conn, st, rs);
		}
		return dvdlist;
	}
	public static List<DvdDTO> dvdSelectByTitle(String title){
		List<DvdDTO> dvdlist = new ArrayList<>();
		Connection conn = DBUtil.getConnect();
		PreparedStatement st = null;
		String sql="select * from dvd where title like ? order by title";
		ResultSet rs = null;
		try {
			st=conn.prepareStatement(sql);
			st.setString(1, "%"+title+"%");
			rs=st.executeQuery();
			while(rs.next()){
				dvdlist.add(makeDVD(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.dbclose(conn, st, rs);
		}
		return dvdlist;
	}
	public static List<DvdDTO> dvdSelectByDate(Date start_date, Date end_date){
		List<DvdDTO> dvdlist = new ArrayList<>();
		Connection conn = DBUtil.getConnect();
		PreparedStatement st = null;
		String sql="select * from dvd where release_date between ? and ? order by title";
		ResultSet rs = null;
		System.out.println(start_date+"  "+end_date);
		try {
			st=conn.prepareStatement(sql);
			st.setDate(1, start_date);
			st.setDate(2, end_date);
			rs=st.executeQuery();
			while(rs.next()){
				dvdlist.add(makeDVD(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.dbclose(conn, st, rs);
		}
		return dvdlist;
	}
	public static List<DvdDTO> dvdSelectByActor(String actor){
		List<DvdDTO> dvdlist = new ArrayList<>();
		Connection conn = DBUtil.getConnect();
		PreparedStatement st = null;
		String sql="select * from dvd where actor like ? order by title";
		ResultSet rs = null;
		try {
			st=conn.prepareStatement(sql);
			st.setString(1, "%"+actor+"%");
			rs=st.executeQuery();
			while(rs.next()){
				dvdlist.add(makeDVD(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.dbclose(conn, st, rs);
		}
		return dvdlist;
	}
	public static DvdDTO dvdSelectByNum(int num){
		DvdDTO dvd = null;
		Connection conn = DBUtil.getConnect();
		PreparedStatement st = null;
		String sql="select * from dvd where D_NUM = ? ";
		ResultSet rs = null;
		try {
			st=conn.prepareStatement(sql);
			st.setInt(1, num);
			rs=st.executeQuery();
			if(rs.next())
				dvd=makeDVD(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.dbclose(conn, st, rs);
		}
		return dvd;
	}
	
	public static boolean addDvd(DvdDTO dvd){
		
		Connection conn=DBUtil.getConnect();
		PreparedStatement st=null;
		String sql="insert into dvd values(BBB.NEXTVAL,?,?,?,?,?,?,?,'Y')";
		try {
			st=conn.prepareStatement(sql);
			st.setString(1, dvd.getDirector());
			st.setDate(2, dvd.getRelease_date());
			st.setString(3, dvd.getTitle());
			st.setString(4, dvd.getGenre());
			st.setInt(5, dvd.getPrice());
			st.setString(6, dvd.getRating());
			st.setString(7,  dvd.getActor());
			
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
	public static boolean deleteDvd(int num){
		Connection conn = DBUtil.getConnect();
		PreparedStatement st = null;
		String sql="delete from dvd where D_NUM=?";
		try {
			st=conn.prepareStatement(sql);
			st.setInt(1,num);
			st.executeUpdate();
		}catch(SQLIntegrityConstraintViolationException e){
			return false;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			DBUtil.dbclose(conn, st, null);
		}
		return true;
	
	}
	public static boolean priceUpdate(int price, int num){
		Connection conn = DBUtil.getConnect();
		PreparedStatement st = null;
		String sql="update dvd set price=? where D_NUM=? ";
		try {
			st=conn.prepareStatement(sql);
			st.setInt(1,price);
			st.setInt(2, num);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			DBUtil.dbclose(conn, st, null);
		}
		return true;
	
	}
	
	public static boolean enableCheck(int num){
		Connection conn = DBUtil.getConnect();
		PreparedStatement st = null;
		String sql="select * from dvd where d_num=?";
		ResultSet rs = null;
		try {
			st=conn.prepareStatement(sql);
			st.setInt(1, num);
			rs=st.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.dbclose(conn, st, null);
		}
		return true;
	}
//	public static void changeEnable(DvdDTO dvd){
//		Connection conn = DBUtil.getConnect();
//		PreparedStatement st = null;
//		String sql;
//		if(dvd.getEnable().equals("Y"))
//			sql="update dvd set enable='N' where D_NUM=?";
//		else
//			sql="update dvd set enable='Y' where D_NUM=?";
//
//		try {
//			st=conn.prepareStatement(sql);
//			st.setInt(1, dvd.getD_num());
//			st.executeUpdate();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			DBUtil.dbclose(conn, st, null);
//		}
//	}
//	
	
}
