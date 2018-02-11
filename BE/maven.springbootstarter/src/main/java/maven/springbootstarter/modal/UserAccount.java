package maven.springbootstarter.modal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserAccount {
	
	@Id
	private String userID;
	
	private String password;
	
	private String fullName;

	
	public UserAccount() {
	}

	public UserAccount(String userID, String password, String fullName) {
		super();
		this.userID = userID;
		this.password = password;
		this.fullName = fullName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
