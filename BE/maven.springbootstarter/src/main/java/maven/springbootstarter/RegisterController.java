package maven.springbootstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import maven.springbootstarter.modal.UserAccount;

@RestController
public class RegisterController {
	
	@Autowired
	private UserAccountManager mUserAccountManager;
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.POST , value="/register")
	public void register(@RequestBody UserAccount account) {
		System.out.println(account.getFullName());
		mUserAccountManager.addAccount(account);
	}
}
