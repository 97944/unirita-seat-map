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
	private String employeeNum;
	private String division;
	private String department;
	private String section;
	private String group;
	private String position;
	private String joinedYear;
	private String phoneNumber;
	private String phoneAddress;
	private int column;
	private int line;

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

	@Column(name = "EMPLOYEE_NUM", nullable = false)
	public String getEmployeeNum(){
		return employeeNum;
	}

	public void setEmployeeNum(String employeeNum){
		this.employeeNum = employeeNum;
	}

	@Column(name = "DIVISION")
	public String getDivision(){
		return division;
	}

	public void setDivision(String division){
		this.division = division;
	}

	@Column(name = "DEPARTMENT")
	public String getDepartment(){
		return department;
	}

	public void setDepartment(String department){
		this.department = department;
	}

	@Column(name = "SECTION")
	public String getSection(){
		return section;
	}

	public void setSection(String section){
		this.section = section;
	}

	@Column(name = "`GROUP`")
	public String getGroup(){
		return group;
	}

	public void setGroup(String group){
		this.group = group;
	}

	@Column(name = "POSITION")
	public String getPosition(){
		return position;
	}

	public void setPosition(String position){
		this.position = position;
	}

	@Column(name = "JOINED_YEAR")
	public String getJoinedYear(){
		return joinedYear;
	}

	public void setJoinedYear(String joinedYear){
		this.joinedYear = joinedYear;
	}

	@Column(name = "PHONE_NUMBER")
	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "PHONE_ADDRESS")
	public String getPhoneAddress(){
		return phoneAddress;
	}

	public void setPhoneAddress(String phoneAddress){
		this.phoneAddress = phoneAddress;
	}

	@Column(name = "`COLUMN`")
	public int getColumn(){
		return column;
	}

	public void setColumn(int column){
		this.column = column;
	}

	@Column(name = "LINE")
	public int getLine(){
		return line;
	}

	public void setLine(int line){
		this.line = line;
	}
}
