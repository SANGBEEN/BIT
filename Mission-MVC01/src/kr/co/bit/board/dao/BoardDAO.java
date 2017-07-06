package kr.co.bit.board.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import kr.co.bit.board.vo.BoardFileVO;
import kr.co.bit.board.vo.BoardVO;
import kr.co.bit.util.ConnectionFactory;
import kr.co.bit.util.JDBCClose;

public class BoardDAO
{

    public BoardDAO()
    {
    }

    public List<BoardVO> selectAllBoard()
    {
        Connection conn;
        PreparedStatement stmt;
        List<BoardVO> list = new ArrayList<>();
        conn = null;
        stmt = null;
        ResultSet rs = null;
        try{
            conn = new ConnectionFactory().getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("select no, title, writer, to_char(reg_date, 'yyyy-mm-dd') as reg_date ");
            sql.append("  from t_board ");
            sql.append(" order by no desc ");
            stmt = conn.prepareStatement(sql.toString());
            rs = stmt.executeQuery();
            
            while(rs.next()){
            	BoardVO board = new BoardVO();
            	int no = rs.getInt("no");
                String title = rs.getString("title");
                String writer = rs.getString("writer");
                String regDate = rs.getString("reg_date");
                board = new BoardVO();
                board.setNo(no);
                board.setTitle(title);
                board.setWriter(writer);
                board.setRegDate(regDate);
                list.add(board);
            }


        }catch(Exception e){
           e.printStackTrace();
        }finally{
        	 JDBCClose.close(conn, stmt);
        }

        return list;
    }
    
    public List<BoardVO> selectAllBoard(int currentpage)
    {
        Connection conn;
        PreparedStatement stmt;
        List<BoardVO> list = new ArrayList<>();
        int count = 10;
        conn = null;
        stmt = null;
        ResultSet rs = null;
        try{
            conn = new ConnectionFactory().getConnection();
            StringBuilder sql = new StringBuilder();
            //서브쿼리들어감
            sql.append("select no, title, writer, to_char(reg_date, 'yyyy-mm-dd') as reg_date ");
            sql.append("  from t_board ");
            sql.append(" order by no desc ");
            
            stmt = conn.prepareStatement(sql.toString());
            rs = stmt.executeQuery();
            
            while(rs.next()){
            	BoardVO board = new BoardVO();
            	int no = rs.getInt("no");
                String title = rs.getString("title");
                String writer = rs.getString("writer");
                String regDate = rs.getString("reg_date");
                board = new BoardVO();
                board.setNo(no);
                board.setTitle(title);
                board.setWriter(writer);
                board.setRegDate(regDate);
                list.add(board);
            }


        }catch(Exception e){
           e.printStackTrace();
        }finally{
        	 JDBCClose.close(conn, stmt);
        }

        return list;
    }

    public void insertBoard(BoardVO board){
        StringBuilder sql;
        sql = new StringBuilder();
        sql.append("insert into t_board(no,title,writer,content) ");
        sql.append(" values(?,?,?,?) ");
        
        try(
        	Connection conn = new ConnectionFactory().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql.toString());
        ){
        	stmt.setInt(1, board.getNo());
        	stmt.setString(2, board.getTitle());
        	stmt.setString(3, board.getWriter());
        	stmt.setString(4, board.getContent());
        	stmt.executeUpdate();
        	
        }catch(Exception e){
        	e.printStackTrace();
        }
       
    }

    public BoardVO detailBoard(int b_no, boolean flag)
    {
        Connection conn;
        PreparedStatement stmt;
        BoardVO board = new BoardVO();
        conn = null;
        stmt = null;
        ResultSet rs = null;
        
        try{
            conn = new ConnectionFactory().getConnection();
            conn.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            if(flag){
            	sql.append("update T_BOARD set view_cnt=view_cnt+1 where no=? ");
            	stmt = conn.prepareStatement(sql.toString());
            	stmt.setInt(1, b_no);
            	stmt.executeUpdate();            	
            	sql = new StringBuilder();
            	stmt = null;
            }
            sql.append(" select no,title,writer,content,view_cnt, to_char(reg_date,'yyyy-mm-dd') as reg_date ");
            sql.append(" from t_board where no = ? ");
            stmt = conn.prepareStatement(sql.toString());
            stmt.setInt(1, b_no);
            rs = stmt.executeQuery();
            conn.commit();
            if(rs.next()){
                int no = rs.getInt("no");
                String title = rs.getString("title");
                String writer = rs.getString("writer");
                String regDate = rs.getString("reg_date");
                String content = rs.getString("content");
                int viewCnt = rs.getInt("view_cnt");
                board.setNo(no);
                board.setTitle(title);
                board.setWriter(writer);
                board.setRegDate(regDate);
                board.setContent(content);
                board.setViewCnt(viewCnt);
            }
   
        }catch(Exception e){
            try
            {
                conn.rollback();
            }
            catch(SQLException e1)
            {
                e1.printStackTrace();
            }
        }finally{
        	JDBCClose.close(conn, stmt);
        }
        return board;
    }

    public void updateBoard(BoardVO board)
    {
        Connection conn;
        PreparedStatement stmt;
        conn = null;
        stmt = null;
        try{
            conn = new ConnectionFactory().getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("update t_board set title=? , content=? where no=?");
            stmt = conn.prepareStatement(sql.toString());
            stmt.setString(1, board.getTitle());
            stmt.setString(2, board.getContent());
            stmt.setInt(3, board.getNo());
            stmt.executeUpdate();
        }
        catch(Exception e){
        	e.printStackTrace();
        }finally{
        	JDBCClose.close(conn, stmt);
        }
    }

    public void deleteBoard(int no)
    {
        Connection conn;
        PreparedStatement stmt;
        conn = null;
        stmt = null;
        try{
            conn = new ConnectionFactory().getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("delete from t_board where no = ?");
            stmt = conn.prepareStatement(sql.toString());
            stmt.setInt(1, no);
            stmt.executeUpdate();
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	JDBCClose.close(conn, stmt);
        }

    }
    /**
	 * 게시글 번호 추출하는 서비스
	 */
	public int selectNo() {
		
		String sql = "select seq_t_board_no.nextval from dual";
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * 첨부파일 등록 서비스
	 */
	public void insertFile(BoardFileVO fileVO) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into t_board_file ");
		sql.append(" values(seq_t_board_file_no.nextval, ?, ?, ?, ?) ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setInt(1, fileVO.getBoardNo());
			pstmt.setString(2, fileVO.getFileOriName());
			pstmt.setString(3, fileVO.getFileSaveName());
			pstmt.setInt(4, fileVO.getFileSize());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 파일리스트 조회
	 */
	public List<BoardFileVO> selectFileByNo(int boardNo) {
		List<BoardFileVO> fileList = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select no, file_ori_name, file_save_name, file_size ");
		sql.append("  from t_board_file ");
		sql.append(" where board_no = ? ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setInt(1, boardNo);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardFileVO fileVO = new BoardFileVO();
				fileVO.setNo(rs.getInt("no"));
				fileVO.setFileOriName(rs.getString("file_ori_name"));
				fileVO.setFileSaveName(rs.getString("file_save_name"));
				fileVO.setFileSize(rs.getInt("file_size"));
				
				fileList.add(fileVO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return fileList;
	}
	
}
