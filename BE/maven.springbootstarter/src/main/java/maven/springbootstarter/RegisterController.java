package maven.springbootstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import maven.springbootstarter.modal.UserAccount;

@RestController
public class RegisterController {
	
	@Autowired
	private UserAccountManager mUserAccountManager;
	
	
	@CrossOrigin(origins = "http://localhost:3000/register")
	@RequestMapping(method=RequestMethod.POST , value="/register")
	public void register(@RequestBody UserAccount account) {
		System.out.println(account.getFullName() + " userid "+ account.getUserID());
		account.setRole("ADMIN");
		mUserAccountManager.addAccount(account);
	}


}
