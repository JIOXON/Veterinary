package app.adapters.users.entity;

import app.adapters.persons.entity.PersonEntity;
import app.domain.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {
	public UserEntity(User user) {
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private long userId;
	@JoinColumn(name = "personId")
	@OneToOne
	private PersonEntity personId;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "password")
	private String password;

	public long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserEntity() {
	}

	public PersonEntity getPersonId() {
		return personId;
	}

	public void setPersonId(PersonEntity personId) {
		this.personId = personId;
	}

}
