package web.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import web.model.vo.MemberVO;
import web.util.DBUtil;
import web.util.MyException;

public class MemberDAO {

	public void memberInsert(MemberVO memberVO) throws MyException {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = DBUtil.getConnection();
			String sql = "insert into user(name, id, pw, email, loc) values(?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, memberVO.getName());
			stmt.setString(2, memberVO.getId());
			stmt.setString(3, memberVO.getPw());
			stmt.setString(4, memberVO.getEmail());
			stmt.setString(5, memberVO.getLoc());
			int i = stmt.executeUpdate();
			System.out.println(i+"행이 추가되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("회원가입에 실패했습니다.");
		} finally {
			DBUtil.close(stmt, con);
		}
	}

	public MemberVO login(MemberVO memberVO) throws MyException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			String sql="select * from user where id=? and pw=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, memberVO.getId());
			stmt.setString(2, memberVO.getPw());
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				memberVO.setName(rs.getString("name"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setLoc(rs.getString("loc"));
				return memberVO;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("로그인에 실패했습니다.");
		} finally {
			DBUtil.close(rs, stmt, con);
		}
		
	}

	public void updateInfo(MemberVO memberVO) throws MyException {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = DBUtil.getConnection();
			String sql = "update user set pw=?, email=?, loc=? where id=?";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, memberVO.getPw());
			stmt.setString(2, memberVO.getEmail());
			stmt.setString(3, memberVO.getLoc());
			stmt.setString(4, memberVO.getId());
			int i = stmt.executeUpdate();
			System.out.println(i+"행이 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("수정에 실패했습니다.");
		} finally {
			DBUtil.close(stmt, con);
		}
	}

	public void deleteUser(MemberVO memberVO) throws MyException {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = DBUtil.getConnection();
			String sql = "delete from user where id=?";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, memberVO.getId());
			int i = stmt.executeUpdate();
			System.out.println(i+"행이 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("탈퇴에 실패했습니다.");
		} finally {
			DBUtil.close(stmt, con);
		}
	}

	public boolean isCheck(String id) throws MyException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select id from user where id=?";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("다시 시도해주세요.");
		} finally {
			DBUtil.close(stmt, con);
		}
	}

}
