package bit.com.last;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

public class AdminDAO {
	public static AdminDTO makeAdmin(ResultSet rs) throws SQLException{
		int a_num = rs.getInt(1);
		String id = rs.getString(2);
		String password = rs.getString(3);
		String rrn = rs.getString(4);
		String name = rs.getString(5);
		String email = rs.getString(6);
		String address = rs.getString(7);
		String phone = rs.getString(8);

		AdminDTO admin = new AdminDTO(a_num, id, password, rrn, name, email, address, phone);
		return admin;
	}
	
	public static AdminDTO adminLogin(String id, String pass){
		AdminDTO admin = null;
		Connection conn = DBUtil.getConnect();
		PreparedStatement st = null;
		String sql="select * from admin where a_id=? and a_password=?";
		ResultSet rs = null;
		try {
			st=conn.prepareStatement(sql);
			st.setString(1, id);
			st.setString(2, pass);
			rs=st.executeQuery();
			if(rs.next())
				admin=makeAdmin(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.dbclose(conn, st, rs);
		}
		return admin;
	}
}
