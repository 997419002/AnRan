package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tools.GetConnectionByJdbc;
import vo.Page;
import vo.User;

public class UserDao {
	public static  User findUserByUserName(String userName) {
		//获取数据库连接
		Connection con=GetConnectionByJdbc.getConnection();
		PreparedStatement psmt=null;
		ResultSet rs=null;
		User user=null;
		String sql="select * from t_user where userName=?";
		try {
			 psmt=con.prepareStatement(sql);
			psmt.setString(1, userName);
			rs=psmt.executeQuery();
			while(rs.next()) {
				user=new User(rs.getString("userName"), rs.getString("userPwd"), rs.getString("chrName"), rs.getString("role"),rs.getString("email"),rs.getString("provinceCode"),rs.getString("province"),rs.getString("cityCode"),rs.getString("city"));
			}
			return user;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			GetConnectionByJdbc.closeAll(con, psmt, rs);
		}
		return null;
	}
	public static void insertUser(User u) {
		Connection con=GetConnectionByJdbc.getConnection();
		String sql="insert into t_user value(?,?,?,?,?,?,?)";
		PreparedStatement psmt=null;
		ResultSet rs=null;
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, u.getUserName());
			psmt.setString(2,u.getUserPwd());
			psmt.setString(3,u.getChrName());
			psmt.setString(4, u.getRole());
			psmt.setString(5,u.getEmail());
			psmt.setString(6, u.getProvince());
			psmt.setString(7, u.getCity());
			psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			GetConnectionByJdbc.closeAll(con, psmt, rs);
		}
	}
	public static ArrayList<User> query(User user, Page page) {
		ArrayList<User> list = new ArrayList<User>(); // 存放查询结果的集合
		StringBuffer condition = new StringBuffer();// 查询条件
		if (user.getUserName() != null && !"".equals(user.getUserName())) { // 判断是否有该查询条件
			condition.append(" and userName like '%")
					.append(user.getUserName()).append("%'");
		}
		if (user.getChrName() != null && !"".equals(user.getChrName())) { //
			condition.append(" and chrName like '%").append(user.getChrName())
					.append("%'");
		}
		if (user.getEmail() != null && !"".equals(user.getEmail())) { //
			condition.append(" and mail like '%").append(user.getEmail())
					.append("%'");
		}
		if (user.getProvince() != null
				&& !"".equals(user.getProvince())) { //
			condition.append(" and provinceName like '%")
					.append(user.getProvince()).append("%'");
		}
		if (user.getCity() != null && !"".equals(user.getCity())) { //
			condition.append(" and cityName like '%")
					.append(user.getCity()).append("%'");
		}

		int begin = page.getPageSize() * (page.getPageNumber() - 1);
		//limit begin,end中是从begin+1开始取end个数据
		String sql = "select userName,userpwd,chrName,email,A.provinceCode provinceCode,";
		sql = sql + " B.provinceName provinceName,A.cityCode cityCode,C.cityName cityName ";
		sql = sql + " from t_user A left join t_province B ";
		sql = sql + " on A.provinceCode = B.provinceCode left join t_city C on A.cityCode = C.cityCode ";
		sql = sql + " where  1=1 ";
		sql = sql + condition + " order by " + page.getSort() + " "
				+ page.getSortOrder() + " limit " + begin + ","
				+ page.getPageSize();
		Connection con = GetConnectionByJdbc.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				User u=new User(rs.getString("userName"), rs.getString("userPwd"), rs.getString("chrName"), rs.getString("role"),rs.getString("email"),rs.getString("provinceCode"),rs.getString("province"),rs.getString("cityCode"),rs.getString("city"));
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}

		return list;
	}
	public static boolean delete(String ids) {
		String id[]=ids.split(",");
		for(int i=0;i<id.length;i++) {
		Connection con=GetConnectionByJdbc.getConnection();
		PreparedStatement psmt=null;
		ResultSet rs=null;
		String sql="delete from t_user where userName=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, id[i]);
			psmt.executeUpdate();
			psmt.close();
			rs.close();
			con.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return false;
	}
	public static boolean update(User user) {
		Connection con=GetConnectionByJdbc.getConnection();
		PreparedStatement psmt=null;
		ResultSet rs=null;
		String sql="update t_user set userName=?,userpwd=?,";
		return false;
	}
	public static boolean insert(User user) {
		Connection con=GetConnectionByJdbc.getConnection();
		PreparedStatement psmt=null;
		ResultSet rs=null;
		String sql="insert into t_user value(?,?,?,?,?,?)";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, user.getUserName());
			psmt.setString(2, user.getUserPwd());
			psmt.setString(3, user.getChrName());
			psmt.setString(4, user.getRole());
			psmt.setString(5, user.getEmail());
			psmt.setString(6, user.getProvinceCode());
			psmt.setString(7, user.getProvince());
			psmt.setString(8, user.getCityCode());
			psmt.setString(9, user.getCity());
			psmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			GetConnectionByJdbc.closeAll(con, psmt, rs);
		}
		return false;
	}
	public static int count(User user,Page page) {
		return query(user, page).size();
	}
}
