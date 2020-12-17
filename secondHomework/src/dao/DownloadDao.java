package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.GetConnectionByJdbc;
import vo.Download;

public class DownloadDao {
	public static List<Download> getAllDowload(){
		Connection con=GetConnectionByJdbc.getConnection();
		String sql="select * from download";
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<Download> downloadList=new ArrayList<Download>();
		try {
			psmt=con.prepareStatement(sql);
			rs=psmt.executeQuery();
			while(rs.next()) {
				Download l=new Download(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getLong(5), rs.getInt(6), rs.getString(7));
				downloadList.add(l);
			}
			return downloadList;
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
