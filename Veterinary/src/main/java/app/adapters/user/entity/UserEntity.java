package app.adapters.user.entity;

import app.adapters.persons.entity.PersonEntity;
import app.domain.models.User;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity{
    public UserEntity(User user, PersonEntity personEntity) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.person = personEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @JoinColumn(name = "person_id")
    @OneToOne
    private PersonEntity person;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public PersonEntity getPerson() {
		return person;
	}

	public void setPerson(PersonEntity person) {
		this.person = person;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserEntity() {
    }
}
