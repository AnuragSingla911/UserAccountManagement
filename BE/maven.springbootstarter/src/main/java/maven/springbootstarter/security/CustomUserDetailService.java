package maven.springbootstarter.security;

import maven.springbootstarter.UserAccountManager;
import maven.springbootstarter.modal.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserAccountManager mManager;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        System.out.println("user service requewsted for userid : "+ s);

        try {
            UserAccount userAccount = mManager.getUserAccount(s);
            System.out.println("user service requewsted for user acc: "+ userAccount);
            return User.withUsername(userAccount.getUserID()).password(UserAccount.PASSWORD_ENCODER.encode(userAccount.getPassword())).roles(userAccount.getRole()).build();

        }catch (Exception e){
            return null;
        }


    }
}
