package tools;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class GetConnectionByJdbc {
	private static String userName;
	private static String userPwd;
	private static String driver;
	private static String url;
	static {
		SAXReader reader=new SAXReader();
		try {
			Document doc=reader.read(new File("D:\\JavaWeb\\FirstHomework\\src\\base\\db.xml"));
			Element root=doc.getRootElement();
			Iterator<Element> els=root.elementIterator();
			while(els.hasNext()) {
				Element el=els.next();
				if("username".equals(el.getName())) {
					userName=el.getText();
				}
				else if("userpwd".equals(el.getName())) {
					userPwd=el.getText();
				}
				else if("driver".equals(el.getName())) {
					driver=el.getText();
				}
				else if("url".equals(el.getName())) {
					url=el.getText();
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, userName, userPwd);
		} catch (Exception e) {//�Ŵ��쳣
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void closeAll(Connection con,PreparedStatement psmt,ResultSet rs) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(psmt!=null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
