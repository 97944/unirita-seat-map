package unirita.seat.map.domain.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "accounts")
public class Account {
	private String id;
	private String name;
	private String password;
	private boolean admin;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	@Column(name = "NAME", nullable = false)
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	@Column(name = "PASSWORD", nullable = false)
	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	@Column(name = "ADMIN")
	public boolean getAdmin(){
		return admin;
	}

	public void setAdmin(boolean admin){
		this.admin = admin;
	}

}
