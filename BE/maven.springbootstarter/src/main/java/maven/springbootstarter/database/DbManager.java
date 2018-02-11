package maven.springbootstarter.database;

import org.springframework.data.repository.CrudRepository;

import maven.springbootstarter.modal.UserAccount;

public interface DbManager extends CrudRepository<UserAccount, String>{

}
