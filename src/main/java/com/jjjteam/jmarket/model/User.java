package com.jjjteam.jmarket.model;

import java.util.HashSet;
import java.util.Set;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.jjjteam.jmarket.model.Role.RoleType;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다 ===> auto_increment
	private Long id;
	

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))

	private Set<Role> roles = new HashSet<>();

	public User() {

	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
	//2022.10.19수진추가
	
	@Column(nullable = false, length = 30)
	private String cu_id;
	
	@Column(nullable = false, length = 100)
	private String cu_pwd;
	
	@Column(nullable = false, length = 50)
	private String cu_email;
	
//	@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING) //db에는 enumtype 자료형이 없으므로 string으로 변환해준다
	private RoleType role;
	
	@CreationTimestamp //비워놔도 자동으로 등록한 현재시간을 입력해준다
	private Timestamp cu_created;
	
}