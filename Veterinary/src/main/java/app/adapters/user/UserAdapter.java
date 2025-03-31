package app.adapters.user;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.adapters.persons.repository.PersonRepository;
import app.adapters.user.entity.UserEntity;
import app.adapters.user.repository.UserRepository;
import app.adapters.persons.entity.PersonEntity;
import app.domain.models.User;
import app.ports.UserPort;


@Service
public class UserAdapter implements UserPort{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PersonRepository personRepository;

	@Override
    public boolean existUserId(long userId) {
        return userRepository.existsByUserId(userId);
    }
	
	@Override
	public boolean existUserName(String userName) {
		return userRepository.existsByUserName(userName);
	}

	@Override
    public void saveUser(User user) {
        PersonEntity personEntity = personRepository.findByDocument(user.getDocument());
        UserEntity userEntity = new UserEntity(user, personEntity);
        userRepository.save(userEntity);
        user.setUserId(userEntity.getUserId());
    }

	@Override
    public List<User> findAllUsers() {
        return userRepository.findAll().stream()
                .map(this::userAdapter)
                .collect(Collectors.toList());
    }
	
	@Override
    public User findByUserName(String userName) {
        UserEntity userEntity = userRepository.findByUserName(userName);
        return userAdapter(userEntity);
    }

	private User userAdapter(UserEntity userEntity) {
        User user = new User();
        user.setUserId(userEntity.getUserId());
        user.setUserName(userEntity.getUserName());
        user.setPassword(userEntity.getPassword());
        user.setRole(userEntity.getRole());
        user.setDocument(userEntity.getPerson().getDocument());
        user.setName(userEntity.getPerson().getName());
        user.setAge(userEntity.getPerson().getAge());
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getRole());
        return user;
    }
}