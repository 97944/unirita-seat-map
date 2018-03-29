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
	private String familyName;
	private String givenName;
	private String familyNameKana;
	private String givenNameKana;
	private boolean admin;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	@Column(name = "FAMILY_NAME", nullable = false)
	public String getFamilyName(){
		return familyName;
	}

	public void setFamilyName(String familyName){
		this.familyName = familyName;
	}

	@Column(name = "GIVEN_NAME", nullable = false)
	public String getGivenName(){
		return givenName;
	}

	public void setGivenName(String givenName){
		this.givenName = givenName;
	}

	@Column(name = "FAMILY_NAME_KANA", nullable = false)
	public String getFamilyNameKana(){
		return givenNameKana;
	}

	public void setGivenNameKana(String givenNameKana){
		this.givenNameKana = givenNameKana;
	}

	@Column(name = "GIVEN_NAME_KANA", nullable = false)
	public String getGivenNameKana(){
		return familyNameKana;
	}

	public void setFamilyNameKana(String familyNameKana){
		this.familyNameKana = familyNameKana;
	}

	@Column(name = "ADMIN")
	public boolean getAdmin(){
		return admin;
	}

	public void setAdmin(boolean admin){
		this.admin = admin;
	}

}
