package maven.springbootstarter.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import maven.springbootstarter.UserAccountManager;
import maven.springbootstarter.modal.UserAccount;

@RestController
public class AdminController {
	
	@Autowired
	private UserAccountManager mUserAccountManager;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/admin")
	public List<UserAccount> getListOfAccounts(){
		System.out.println("fetch list"+ mUserAccountManager.getUserAccounts().size());
		return mUserAccountManager.getUserAccounts();
	}
	@RequestMapping("/admin/{userID}")
	public UserAccount getUserAccount(@PathVariable String userID) {
		return mUserAccountManager.getUserAccount(userID);
	}

}
