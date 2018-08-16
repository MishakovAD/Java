package UserProfile;

public class UserProfile {
	private Integer userId;
	private String name;
	private String secondName;
	private String middleName; //изменить таблицу в бд и добавить эти поля
	private String email;
	private String password;
	private String birthday;
	private String phoneNumber; //
	private String roomNumber;//
	private String position;// должность
	private String userGroup;//группа (например секретари)
	private String role;//привилегии //privilege into DB!!!
	
	
	
	private static UserProfile ourInstance = new UserProfile();
    public static UserProfile getInstance() {
        return ourInstance;
    }

    public UserProfile() {
    }
	
	public UserProfile(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}


	public UserProfile(String name, String email, String password, String birthday) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
	}
	
	
	public UserProfile(Integer userId, String name, String email, String password, String birthday) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
	}
	
	
	
	public UserProfile(Integer userId, String name, String secondName, String email, String password, String birthday) {
		super();
		this.userId = userId;
		this.name = name;
		this.secondName = secondName;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
	}

	public UserProfile(String name, String secondName, String email, String password, String birthday) {
		super();
		this.name = name;
		this.secondName = secondName;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
	}

	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String group) {
		this.userGroup = group;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String privilege) {
		this.role = privilege;
	}

	public static UserProfile getOurInstance() {
		return ourInstance;
	}

	public static void setOurInstance(UserProfile ourInstance) {
		UserProfile.ourInstance = ourInstance;
	}

	@Override
	public String toString() {
		return "UserProfile [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", birthday=" + birthday + "]";
	}
	
	
	
}

