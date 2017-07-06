package kr.co.bit.member.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import kr.co.bit.member.vo.MemberVO;
import kr.co.bit.util.ConnectionFactory;
import kr.co.bit.util.JDBCClose;

public class MemberDAO
{

    public MemberDAO()
    {
    }

    public MemberVO Login(String id, String password)
    {
        Connection conn;
        PreparedStatement stmt;
        MemberVO m = new MemberVO();
        conn = null;
        stmt = null;
        ResultSet rs = null;
        try{
        	conn = (new ConnectionFactory()).getConnection();
        	StringBuilder sql = new StringBuilder();
        	sql.append("select * from t_member where id=? and password=?");
        	stmt = conn.prepareStatement(sql.toString());
        	stmt.setString(1, id);
        	stmt.setString(2, password);
        	rs = stmt.executeQuery();
        	if(rs.next()){
        		m.setName(rs.getString("name"));
        		m.setEmailId(rs.getString("email_id"));
        		m.setEmailDomain(rs.getString("email_domain"));
        		m.setBasicAddr(rs.getString("basic_addr"));
        		m.setDetailAddr(rs.getString("detail_addr"));
        		m.setTel1(rs.getString("tel1"));
        		m.setTel2(rs.getString("tel2"));
        		m.setTel3(rs.getString("tel3"));
        		m.setType(rs.getString("type"));
        		m.setRegDate(rs.getString("reg_date"));
        		m.setPost(rs.getString("post"));
        		m.setId(id);
        		return m;
        	}
        	
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	JDBCClose.close(conn, stmt);
        }
       
        return null;
    }

    public List<MemberVO> selectAllMember()
    {
        Connection conn;
        PreparedStatement stmt;
        conn = null;
        stmt = null;
        ResultSet rs = null;
        List<MemberVO> list = new ArrayList<>();
        try{
            conn = new ConnectionFactory().getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("select * from t_member order by id");
            stmt = conn.prepareStatement(sql.toString());
            rs = stmt.executeQuery();
            while(rs.next()){
                MemberVO m = new MemberVO();
                m.setId(rs.getString("id"));
                m.setName(rs.getString("name"));
                m.setEmailId(rs.getString("email_id"));
                m.setEmailDomain(rs.getString("email_domain"));
                m.setTel1(rs.getString("tel1"));
                m.setTel2(rs.getString("tel2"));
                m.setTel3(rs.getString("tel3"));
                m.setBasicAddr(rs.getString("basic_addr"));
                m.setDetailAddr(rs.getString("detail_addr"));
                m.setPost(rs.getString("post"));
                m.setRegDate(rs.getString("reg_date"));
                m.setType(rs.getString("type"));
                list.add(m);
            }

        }
        catch(Exception e){
        	e.printStackTrace();
        }finally{
        	JDBCClose.close(conn, stmt);
        }
       
        return list;
    }
    public boolean selectMemberById(String id){
    	Connection conn = null;
    	PreparedStatement stmt = null;
    	try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" select id from t_member where id=?");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, id);
			ResultSet rs = null;
			rs = stmt.executeQuery();
			if(rs.next()){
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCClose.close(conn, stmt);;
		}
    	return false;
    }
}
