package bit.com.last;

public class AdminDTO {
	private int a_num;
	private String id;
	private String password;
	private String rrn;
	private String name;
	private String email;
	private String address;
	private String phone;
	public AdminDTO(){
		
	}
	public AdminDTO(int a_num, String id, String password, String rrn, String name, String email, String address,
			String phone) {
		super();
		this.a_num = a_num;
		this.id = id;
		this.password = password;
		this.rrn = rrn;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}
	public int getA_num() {
		return a_num;
	}
	public void setA_num(int a_num) {
		this.a_num = a_num;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdminDTO [a_num=").append(a_num).append(", id=").append(id).append(", password=")
				.append(password).append(", rrn=").append(rrn).append(", name=").append(name).append(", email=")
				.append(email).append(", address=").append(address).append(", phone=").append(phone).append("]");
		return builder.toString();
	}
	
}
