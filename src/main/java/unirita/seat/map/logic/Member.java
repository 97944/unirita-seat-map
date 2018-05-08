package unirita.seat.map.logic;

public class Member {

	//メンバ変数の定義
    private String priority;
	private String memberId;
	private String lastNameKana;
	private String firstNameKana;
	private String lastName;
	private String firstName;
	private String division;
	private String department;
	private String section;
	private String team;
	private String position;
	private int    entryYear;
	private String phoneNumber;
	private String gmailAddress;
	private String phoneAddress;
	private String SFA;
	//コンストラクタ(名簿データ格納)
	public Member(String data)throws NumberFormatException{

			//カンマ区切り
			String [] memberdata = data.split(",",16);

			//入社年なしの場合
			if(memberdata[11].length() < 1){
				memberdata[11] = "9999";
			}
			//就任日がない場合
			if(memberdata[0].length() < 1){
			    memberdata[0] = "99/99/9999";
			}
			//データが空の場合全角スペースを代入
			for(int i = 0; i < memberdata.length; i++ ){
				if(i != 6 && i != 7&&
				   i != 8 && i != 9){
					if(memberdata[i].length() < 1){
						memberdata[i]="�@";
					}
				}
			}

			String[] mmddyyyy = memberdata[0].split("/",3);
			memberdata[0] = mmddyyyy[0]+mmddyyyy[1]+mmddyyyy[2];
			//各データを格納
			priority = memberdata[0];
			memberId = memberdata[1];
			lastNameKana = memberdata[2];
			firstNameKana = memberdata[3];
			lastName = memberdata[4];
			firstName = memberdata[5];
			division = memberdata[6];
			department = memberdata[7];
			section = memberdata[8];
			team = memberdata[9];
			position = memberdata[10];
			entryYear = Integer.parseInt(memberdata[11]);
			gmailAddress = memberdata[12];
			phoneNumber = memberdata[13];
			phoneAddress = memberdata[14];
			SFA = memberdata[15];
		}

	public Member() {
	}


	//各データのgetter

	public String getMemberId() {
		return memberId;
	}

	public String getLastNameKana() {
		return lastNameKana;
	}

	public String getFirstNameKana() {
		return firstNameKana;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getDivision() {
		return division;
	}

	public String getDepartment() {
		return department;
	}

	public String getSection() {
		return section;
	}

	public String getTeam() {
		return team;
	}

	public String getPosition() {
		return position;
	}

	public int getEntryYear() {
		return entryYear;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getGmailAddress() {
		return gmailAddress;
	}

	public String getPhoneAddress() {
		return phoneAddress;
	}

	public String getSFA() {
		return SFA;
	}

    public String getPriority() {
        return priority;
    }
}