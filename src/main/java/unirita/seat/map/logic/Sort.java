package unirita.seat.map.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class Sort {
    Member member = new Member();
    //役職まわりエラー検出用
    public boolean posiErrorFlag = false;


    /******50音順でソート(デフォルト、役職表示あり。)*******/
    public ArrayList<Member> nameSort(ArrayList<Member> arraylist) {

        List<Member> list = arraylist;

        Collections.sort(list, new Comparator<Member>() {
            public int compare(Member name1, Member name2) {
                String name_1 = name1.getLastNameKana()+" "+name1.getFirstNameKana();
                String name_2 = name2.getLastNameKana()+" "+name2.getFirstNameKana();

                return name_1.compareTo(name_2);
            }
        });
        ArrayList<Member> sortedArraylist = (ArrayList<Member>) list;
        //役職表示
        sortedArraylist = getSortedListYakushoku(sortedArraylist);
        return sortedArraylist;
    }

    /******入社年順で表示******/
    public ArrayList<Member> yearSort(ArrayList<Member> arraylist){

        List<Member> list = arraylist;

        Collections.sort(arraylist, new Comparator<Member>() {
            public int compare(Member year1, Member year2) {
                int intyear_1 = year1.getEntryYear();
                int intyear_2 = year2.getEntryYear();

                String year_1 = String.valueOf(intyear_1);
                String year_2 = String.valueOf(intyear_2);
                return year_1.compareTo(year_2);
            }
        });
        ArrayList<Member> sortedArraylist = (ArrayList<Member>) list;
        //役職表示させる場合はコメントを外す
//      sortedArraylist = getSortedListYakushoku(sortedArraylist);
        return sortedArraylist;
    }



    /******社員番号順でソート******/
    public ArrayList<Member> idSort(ArrayList<Member> arraylist){
        ArrayList<Member> sortedArrayList = new ArrayList<Member>();
        //一般社員、契約社員、上海の方の社員番号を仮数字に置き換える。
        final String generalStaff  = "B";
        final String contractStaff = "C";
        final String ShanghaiStaff = "bc";
        final String generalStaffNum  = "a";
        final String contractStaffNum = "b";
        final String ShanghaiStaffNum = "c";
        List<Member> list = arraylist;

        Collections.sort(list, new Comparator<Member>() {
            public int compare(Member id1, Member id2) {
                String stid_1 = id1.getMemberId();
                String stid_2 = id2.getMemberId();

                stid_1=stid_1.replaceFirst(generalStaff,  generalStaffNum);
                stid_1=stid_1.replaceFirst(contractStaff, contractStaffNum);
                stid_1=stid_1.replaceFirst(ShanghaiStaff, ShanghaiStaffNum);
                stid_2=stid_2.replaceFirst(generalStaff,  generalStaffNum);
                stid_2=stid_2.replaceFirst(contractStaff, contractStaffNum);
                stid_2=stid_2.replaceFirst(ShanghaiStaff, ShanghaiStaffNum);

                String id_1 = String.valueOf(stid_1);
                String id_2 = String.valueOf(stid_2);

                return id_1.compareTo(id_2);
            }
        });
        sortedArrayList = (ArrayList<Member>) list;
        return sortedArrayList;
    }


    /*************************************役職優先表示****************************************/
    public ArrayList<Member> getSortedListYakushoku(ArrayList<Member> list){
        ArrayList<String> position = new ArrayList<String>();
        //役職マスタ読込み
        BufferedReader br = null ;
        try{
            String path=System.getProperty("user.dir");
            File blank = new File(path+"\\webapps\\jsp\\data\\��E�}�X�^.csv");
            if(blank.length()==0){
                     posiErrorFlag = true;
            }
            else{
                br = new BufferedReader(new FileReader(path+"\\webapps\\jsp\\data\\��E�}�X�^.csv"));
                String data;
                data=br.readLine();
                while ((data = br.readLine())!=null && !posiErrorFlag){
                    String[] datasplit = data.split(",",2);
                    position.add(datasplit[0]);
                }
            }
        }catch (IOException e){
            posiErrorFlag = true;
        }catch (NumberFormatException e) {
            posiErrorFlag = true;
        }catch (StringIndexOutOfBoundsException e){
            posiErrorFlag = true;
        }
        finally{
            try {
                br.close();
            }
            catch (IOException e) {
                posiErrorFlag = true;
            }
            catch (NullPointerException e) {
                posiErrorFlag = true;
            }
        }

        //役職順にソートしたリスト(結果)を作成
        ArrayList<Member> sortedImportantList = new ArrayList<Member>();
        //役職ごとに格納を行うリストを作成
        ArrayList<ArrayList<Member>> importantList = new ArrayList<ArrayList<Member>>();
        for(int i=0;i < (position.size());i++){
            importantList.add(new ArrayList<Member>());
        }
        importantList.add(new ArrayList<Member>());

        //役職者を各々のリストに格納
        for(int i=0;i < list.size(); i++){
            Boolean a = true;
            for(int j=0;j<position.size();j++){ /*役職の数(役職なし含む)*/
                if(list.get(i).getPosition().equals(position.get(j))){
                    importantList.get(j).add(list.get(i));
                    a=false;
                }
            }
            if(a){
                importantList.get(importantList.size()-1).add(list.get(i));
            }
        }

      //各役職者を格納したリストを、優先して表示させる順に新たなリストに格納。
        for(int i=0;i < importantList.size()-1;i++){
            sortedImportantList.addAll(importantList.get(i));
        }
        sortedImportantList = margeList(sortedImportantList);
        return sortedImportantList;
    }

    public boolean posiError() {
        return posiErrorFlag;
    }

    /**
     * @param list
     * @return
     */
    public ArrayList<Member> margeList(ArrayList<Member> list){

        ArrayList<Member> marge = new ArrayList<Member>();

        int listsize = list.size();
        int count = 0;

        for(int i=0; i<listsize; i++){

            marge.add(list.get(i));
            if(i == listsize){
            }else{
                for(int next=i+1; next<listsize; next++){
                    if(marge.get(i+count).getMemberId().equals(list.get(next).getMemberId())){
                        marge.add(list.get(next));
                        list.remove(next);
                        listsize--;
                        next--;
                        count++;
                    }//if・・・次のリストのＩＤが同じならmargeに追加してlistから削除
                }
            }
        }
        return marge;
    }

    public ArrayList<Member> spriSort(ArrayList<Member> arraylist) {

    	//50音ロジック
        List<Member> list = arraylist;

        Collections.sort(list, new Comparator<Member>() {
            public int compare(Member name1, Member name2) {
                String name_1 = name1.getLastNameKana()+" "+name1.getFirstNameKana();
                String name_2 = name2.getLastNameKana()+" "+name2.getFirstNameKana();

                return name_1.compareTo(name_2);
            }
        });
        ArrayList<Member> sortedArraylist = (ArrayList<Member>) list;


        //執行役員のみ上部へ

        //役員格納リスト
        ArrayList<Member> sikkou = new ArrayList<Member>();
        //役員外格納リスト
        ArrayList<Member> sikkouigai = new ArrayList<Member>();
        //並びかえ格納
        ArrayList<Member>sortedSikkou;

                    for(int i=0 ; i<sortedArraylist.size(); i++){
                        if(sortedArraylist.get(i).getPosition().indexOf("���s����")!=-1 || sortedArraylist.get(i).getPosition().indexOf("�����")!=-1){
                        	//部分一致なら
                            sikkou.add(sortedArraylist.get(i));
                        }else{
                        	//一致なしなら
                            sikkouigai.add(sortedArraylist.get(i));
                        }
                    }
                     //役員のみ、年次・役職ソートを行う
                     sikkou = prioritySort(sikkou);
                     sikkou = getSortedListYakushoku(sikkou);
                     //ソート済み役員リストに役員外リストを追加
                     sikkou.addAll(sikkouigai);
                     //同ＩＤデータをまとめる
                     sortedSikkou = margeList(sikkou);


      return sortedSikkou;
    }

    /******優先度順ソート******/
    public ArrayList<Member> prioritySort(ArrayList<Member> list){
        ArrayList<Member> sortedList = new ArrayList<Member>();
        //優先度ごとに分けたリストを格納するリスト
        ArrayList<ArrayList<Member>> prioList = new ArrayList<ArrayList<Member>>();
        //優先度の数字を格納していく。
        TreeSet<Integer> tree = new TreeSet<Integer>();

        //受け取ったリストの社員情報を一つづつ抜き出す。
        for(int i=0;i<list.size();i++){
            Integer priority = Integer.parseInt(list.get(i).getPriority());

            //既存の優先度の場合
            if(tree.contains(priority)){
                for(int j=0; j<prioList.size();j++){
                    if(prioList.get(j).get(0).getPriority().equals(list.get(i).getPriority())){
                        Member newMember = list.get(i);
                        prioList.get(j).add(newMember);
                    }
                }
            }
            //未発見の優先度の場合
            else{
               tree.add(priority);
               ArrayList<Member> newList = new ArrayList<Member>();
               newList.add(list.get(i));
               prioList.add(newList);
            }
        }
        //優先度の数字をStringで抜き出す。
        Object[]  intTree;
        intTree = tree.toArray();
        String[] trees = new String[intTree.length];
        for(int i=0; i<intTree.length;i++){
            trees[i] = String.valueOf(intTree[i]);
        }
        //優先度の数だけ繰り返す。
        for(int i=0; i<trees.length;i++){
        	//優先度ごとに分けたリストを一つづつ取り出す
            for(int j=0; j<tree.size(); j++){
            	//現在の優先度(i)と同じリストを探しだす。
                if(trees[i].equals(prioList.get(j).get(0).getPriority())){
                	//結果格納用リストに、現在の優先度の社員が格納されているリストを追加。
                    sortedList.addAll(prioList.get(j));
                }
            }
        }
        return sortedList;
    }
}