package randomlovers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import randomlovers.persistence.dao.UserDao;
import randomlovers.persistence.model.Comment;
import randomlovers.persistence.model.User;

import java.util.List;

@Service
public class UserService {

    private UserDao userDao;

    @Transactional
    public void addUser(User user) {
        userDao.saveOrUpdate(user);
    }

    public User getUserById(Integer id) {
        return userDao.findById(id);
    }

    public boolean validateUser(String username, String password) {

        List<User> userList = userDao.findAll();
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    @Transactional
    public void addComment(Integer id, Comment comment) {
        User user = userDao.findById(id);
        userDao.saveOrUpdate(user);
    }

    // getters && setters
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
