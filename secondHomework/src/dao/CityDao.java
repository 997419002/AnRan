package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.GetConnectionByJdbc;
import vo.City;

public class CityDao {
	public static List<City> getCityByPid(int id){
		Connection con=GetConnectionByJdbc.getConnection();
		String sql="select * from t_city where pid=?";
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<City> citys=new ArrayList<City>();
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, id);
			rs=psmt.executeQuery();
			while(rs.next()) {
				City c=new City(rs.getInt(1), rs.getInt(2), rs.getString(3));
				citys.add(c);
			}
			return citys;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			GetConnectionByJdbc.closeAll(con, psmt, rs);
		}
		return null;
	}
	public static String getCityCode(String name) {
		Connection con=GetConnectionByJdbc.getConnection();
		String sql="select * from t_city where name=?";
		PreparedStatement psmt=null;
		ResultSet rs=null;
		City c=null;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, name);
			rs=psmt.executeQuery();
			while(rs.next()) {
				 c=new City(rs.getInt(1), rs.getInt(2), rs.getString(3));
			}
			return c.getId()+"";
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
