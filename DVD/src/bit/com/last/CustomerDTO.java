package bit.com.last;

public class CustomerDTO {

	private int c_num;
	private String id;
	private String password;
	private String rrn;
	private String name;
	private String email;
	private String address;
	private String phone;
	private double point;
	public CustomerDTO(){
		
	}
	public CustomerDTO(int c_num, String id, String password, String rrn, String name, String email, String address,
			String phone, double point) {
		super();
		this.c_num = c_num;
		this.id = id;
		this.password = password;
		this.rrn = rrn;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.point = point;
	}
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRrn() {
		return rrn;
	}
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return String.format(
				"|%5s|%-10s \t|%-10s \t|%-10s\t|%-5s \t|%-25s \t|%-10s \t|%-10s \t|%s \t|",
				c_num, id, password, rrn, name, email, address, phone, point);
	}
	
//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append("번호:").append(c_num).append("\tid:").append(id).append("\tpassword:")
//				.append(password).append("\t주민번호:").append(rrn).append("\t이름:").append(name).append("\temail:")
//				.append(email).append("\t주소:").append(address).append("\tphone:").append(phone).append("\tpoint:")
//				.append(point);
//		return builder.toString();
//	}
	
}
