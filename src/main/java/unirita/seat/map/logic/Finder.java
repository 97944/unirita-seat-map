package unirita.seat.map.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author b1670
 *
 */

public class Finder {
    //部署まわりエラー検出用
    public boolean sectionErrorFlag = false;

    public Member memberInst = new Member();
    private String[] str;

    //コンストラクタ
    public Finder() {
        ArrayList<String> sectionLine = new ArrayList<String>();
        String path=System.getProperty("user.dir");
        BufferedReader br =null;
        try {
            br = new BufferedReader(new FileReader(path+"\\src\\main\\resources\\csv\\部署マスタ.csv"));

            br.readLine();
            while(br.ready()){
                String readstr = br.readLine();
                Character first = readstr.charAt(0);
                if(! first.equals(',')){
                    sectionLine.add(readstr);
                }
            }
            str = new String[sectionLine.size()];
            for(int i=0;i<sectionLine.size();i++){
                String[] sectionSplit=sectionLine.get(i).split("\\,");
                str[i] = sectionSplit[4];

            }

        }catch (FileNotFoundException e) {
            sectionErrorFlag = true;
        } catch (IOException e) {
            sectionErrorFlag = true;
        } finally{
            try {
                br.close();
            } catch (IOException e) {
                sectionErrorFlag = true;
            } catch (NullPointerException e){
                sectionErrorFlag =true;
            }
        }
    }


    //名前検索
    public ArrayList<Member> findName(ArrayList<Member> memberlist, String name){

        ArrayList<Member> result = new ArrayList<Member>();

        int hitFlag=0;

        for(int i=0;i < memberlist.size(); i++ ){
            Member imem = memberlist.get(i);

            //姓(かな)
            if(imem.getLastNameKana().contains(name)){
                hitFlag=1;
            }
            //名(かな)
            else if(imem.getFirstNameKana().contains(name)){
                hitFlag=1;
            }
            //姓
            else if(imem.getLastName().contains(name)){
                hitFlag=1;
            }
            //名
            else if(imem.getFirstName().contains(name)){
                hitFlag=1;
            }
            //フルネーム漢字(スペース無し)
            else if((imem.getLastName()+imem.getFirstName()) .contains(name) ){
                hitFlag=1;
            }
            //フルネームかな(スペース無し)
            else if( (imem.getLastNameKana()+imem.getFirstNameKana()).contains(name) ){
                hitFlag=1;
            }

            else{
                String fullNameSpace = imem.getLastName() +" "+ imem.getFirstName();
                String fullNameKanaSpace = imem.getLastNameKana() +" "+ imem.getFirstNameKana();
                String fullNameFullSpace = imem.getLastName() +"　"+ imem.getFirstName();
                String fullNameKanaFullSpace = imem.getLastNameKana() +"　"+ imem.getFirstNameKana();
                //フルネーム漢字（半角スペースあり）
                if( name.equals(fullNameSpace)){
                    hitFlag=1;
                }
                //フルネームかな(半角スペースあり)
                else if( name.equals(fullNameKanaSpace) ){
                    hitFlag=1;
                }
                //フルネーム漢字（全角スペースあり）
                else if( name.equals(fullNameFullSpace)){
                    hitFlag=1;
                }
                //フルネームかな(全角スペースあり)
                else if( name.equals(fullNameKanaFullSpace) ){
                    hitFlag=1;
                }
            }
            if(hitFlag == 1){
                result.add(imem);
                hitFlag = 0;
            }
        }//for
        return result;
    }
    /********************************************************************/
    //部署
    public ArrayList<Member> findSection(ArrayList<Member> memberlist,String reqSection){
        ArrayList<Member> result = new ArrayList<Member>();
        //全件検索時
        if(reqSection.equals("all")||reqSection.equals("0")){
            return memberlist;
        }
        //リクエストされた部署番号を取得
        int req = Integer.parseInt(reqSection);
        //部署番号に該当する部署名を取得
        String section=str[req];
        //検索開始
        for(int i=0;i < memberlist.size();i++ ){
            //事業部
            if(section.equals(memberlist.get(i).getDivision())){
                result.add(memberlist.get(i));
            }
            //部
            else if(section.equals(memberlist.get(i).getDepartment())){
                result.add(memberlist.get(i));
            }
            //課
            else if(section.equals(memberlist.get(i).getSection())){
                result.add(memberlist.get(i));
            }
            //チーム
            else if(section.equals(memberlist.get(i).getTeam())){
                result.add(memberlist.get(i));
            }
        }
        return result;
    }

    //検索条件表示用
    public String getSections(String section){
        //全件表示パターン
        if(section.equals("all")){
            return "全件表示";
        }
        //部署選択パターン
        else{
            int sectionNum = Integer.parseInt(section);
            return str[sectionNum];
        }
    }

    public boolean sectionError() {
        return sectionErrorFlag;
    }
    /***********************************************************************/
    //入社年
    public ArrayList<Member> findYear(ArrayList<Member> memberlist,String styear){

        ArrayList<Member> result = new ArrayList<Member>();

        int year = Integer.parseInt(styear);

        for(int i=0;i < memberlist.size();i++ ){
            if(memberlist.get(i).getEntryYear() == year){
                result.add(memberlist.get(i));
            }
        }
        return result;
    }

    /********************************************************************/
    //社員番号
    public Member findId(ArrayList<Member> memberlist,String reqId){
        Member result = new Member();
        //検索開始
        for(int i=0;i < memberlist.size();i++ ){
            if(reqId.equals(memberlist.get(i).getMemberId())){
                result = memberlist.get(i);
            }
        }
        return result;
    }
}