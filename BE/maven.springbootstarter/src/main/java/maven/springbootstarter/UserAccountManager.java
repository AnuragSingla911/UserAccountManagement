package maven.springbootstarter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import maven.springbootstarter.database.DbManager;
import maven.springbootstarter.modal.UserAccount;

@Service
public class UserAccountManager {
	
	@Autowired
	private DbManager mDbManager;
	
	
	public List<UserAccount> getUserAccounts(){
		List<UserAccount> accounts = new ArrayList<>();
		mDbManager.findAll().forEach(accounts::add);
		return accounts;
	}
	
	public UserAccount getUserAccount(String userId) {
		return mDbManager.findOne(userId);
	}
	
	public void addAccount(UserAccount account) {
		mDbManager.save(account);
	}

}
