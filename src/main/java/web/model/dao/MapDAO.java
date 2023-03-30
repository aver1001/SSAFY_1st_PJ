package web.model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import web.model.vo.Location;
import web.model.vo.locVO;
import web.util.DBUtil;
import web.util.MyException;

public class MapDAO {
	StringBuilder sb;
	public MapDAO() {
		sb = new StringBuilder();
	}

	public ArrayList<Location> searchLoc(locVO locVo) throws MyException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		sb.setLength(0);
		try {
			con =  DBUtil.getConnection();
			String sql = "select * from attraction_info where title like ? and sido_code=? and content_type_id =?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, sb.append("%").append(locVo.getKeyword()).append("%").toString());
			stmt.setString(2, locVo.getAreaCode());
			stmt.setString(3, locVo.getContentTypeId());
			rs =  stmt.executeQuery();
			ArrayList<Location> locations = new ArrayList<Location>();
			while (rs.next()) {
				locations.add(new Location(rs.getString("first_image"),rs.getString("title"),rs.getString("addr1"),rs.getString("addr2"),rs.getString("latitude"),rs.getString("longitude")));
			}
			return locations;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("데이터베이스 검색에 실패하였습니다.");
		}finally {
			DBUtil.close(rs,stmt,con);
		}
		
	}

	public ArrayList<Sido> searchOption() throws MyException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con =  DBUtil.getConnection();
			String sql = "select * from sido";
			stmt = con.prepareStatement(sql);
			rs =  stmt.executeQuery();
			ArrayList<Sido> sidoInfo = new ArrayList<Sido>();
			while (rs.next()) {
				sidoInfo.add(new Sido(rs.getString("sido_code"),rs.getString("sido_name")));
			}
			return sidoInfo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("데이터베이스 검색에 실패하였습니다.");
		}finally {
			DBUtil.close(rs,stmt,con);
		}
		
	}

}
