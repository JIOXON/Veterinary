package app.adapters.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.persons.entity.PersonEntity;
import app.adapters.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	public boolean existsByUserId(long userId);
	public boolean existsByUserName(String userName);
	public UserEntity findByPerson(PersonEntity personEntity);
	public UserEntity findByUserName(String userName);
	
}