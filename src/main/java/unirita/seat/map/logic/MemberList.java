package unirita.seat.map.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MemberList {
	public ArrayList<Member> memberList = new ArrayList<Member>();
	private boolean memerror = false;
	private boolean paraerror = false;


	public MemberList(){
		BufferedReader br = null ;

		try{
			String path=System.getProperty("user.dir");
			File blank = new File(path+"\\src\\main\\resources\\csv\\名簿データ.csv");
			if(blank.length()==0){
				paraerror = true;
			}
			else{
//				br = new BufferedReader(new FileReader(path+"\\src\\main\\resources\\csv\\名簿データ.csv"));
				br = new BufferedReader(new InputStreamReader(new FileInputStream(blank),"SJIS"));
				String data;
				char comma = ',';
				data=br.readLine();
				while ((data = br.readLine())!=null && !memerror && !paraerror){
					//1文字目
                    Character first  = data.charAt(0);
                    //2文字目
                    Character second = data.charAt(1);
                    //2セル目が空でなければ読み取り開始。
                    if(! (first.equals(comma)&&second.equals(comma))){
                        memberList.add(new Member(data));
					}
                    //1セル目が空で、2セル目が空でなければエラー(それ以外も空なようなら読み飛ばし)
					else if(! second.equals(comma)){
						paraerror = true;
					}
                    //入社年がマイナスならエラー
					if(memberList.get(memberList.size()-1).getEntryYear() <=0){
						paraerror = true;
					}
				}
			}
		}catch (IOException e){
			memerror = true;
		}catch (NumberFormatException e) {
			paraerror = true;
		}catch (StringIndexOutOfBoundsException e){
			paraerror = true;
		}
		finally{
			try {
					br.close();
			}
			catch (IOException e) {
				memerror = true;
			}
			catch (NullPointerException e) {
				memerror = true;
			}
		}
	}

	public ArrayList<Member> getMemberList() {
		return memberList;
	}

	public boolean memError(){
		return memerror;
	}
	public boolean paraError(){
		return paraerror;
	}
}
