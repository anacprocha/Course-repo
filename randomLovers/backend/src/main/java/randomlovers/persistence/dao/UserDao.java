package randomlovers.persistence.dao;

import org.springframework.stereotype.Repository;
import randomlovers.persistence.model.User;

@Repository
public class UserDao extends GenericDao<User> {

    public UserDao() {
        super(User.class);
    }

}
