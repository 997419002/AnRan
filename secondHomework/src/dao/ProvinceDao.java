package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.GetConnectionByJdbc;
import vo.Province;

public class ProvinceDao {
	public static List<Province> getAllProvince(){
		Connection con=GetConnectionByJdbc.getConnection();
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<Province> provinces=new ArrayList<Province>();
		String sql="select * from t_province";
		try {
			psmt=con.prepareStatement(sql);
			rs=psmt.executeQuery();
			while(rs.next()) {
				Province p=new Province(rs.getInt(1), rs.getString(2));
				provinces.add(p);
			}
			return provinces;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			GetConnectionByJdbc.closeAll(con, psmt, rs);
		}
		return null;
	}
	public static String getProvinceCode(String name) {
		Connection con=GetConnectionByJdbc.getConnection();
		PreparedStatement psmt=null;
		ResultSet rs=null;
		Province p=null;
		String sql="select * from t_province where name=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, name);
			rs=psmt.executeQuery();
			while(rs.next()) {
				 p=new Province(rs.getInt(1), rs.getString(2));
			}
			return p.getId()+"";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			GetConnectionByJdbc.closeAll(con, psmt, rs);
		}
		return null; 
	}
}
