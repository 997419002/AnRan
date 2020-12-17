package vo;

public class User {
	private String userName;
	private String userPwd;
	private String chrName;
	private String role;
	private String email;
	private String provinceCode;
	private String province;
	private String cityCode;
	private  String city;
	public User() {}
	public User(String userName, String userPwd, String chrName, String role,String email,String privinceCode,String province,String cityCode,String city) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
		this.chrName = chrName;
		this.role = role;
		this.email=email;
		this.provinceCode=privinceCode;
		this.province=province;
		this.cityCode=cityCode;
		this.city=city;
	}
	
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getChrName() {
		return chrName;
	}

	public void setChrName(String chrName) {
		this.chrName = chrName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
