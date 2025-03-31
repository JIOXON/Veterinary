package app.ports;

import java.util.List;
import app.domain.models.User;

public interface UserPort {
	public boolean existUserId(long userId);
	public boolean existUserName(String userName);
    public void saveUser(User user);
    public User findByUserName(String userName);
    public List<User> findAllUsers();
}