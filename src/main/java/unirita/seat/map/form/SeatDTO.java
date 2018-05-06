package unirita.seat.map.form;

import com.opencsv.bean.CsvBindByPosition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {
//	@CsvBindByName(column = "社員番号")
	@CsvBindByPosition(position = 1)
	private String employeeNum;
//	@CsvBindByName(column = "性")
	@CsvBindByPosition(position = 4)
	private String familyName;
//	@CsvBindByName(column = "名")
	@CsvBindByPosition(position = 5)
	private String givenName;
//	@CsvBindByName(column = "セイ")
	@CsvBindByPosition(position = 2)
	private String familyNameKana;
//	@CsvBindByName(column = "メイ")
	@CsvBindByPosition(position = 3)
	private String givenNameKana;
//	@CsvBindByName(column = "本部")
	@CsvBindByPosition(position = 6)
	private String division;
//	@CsvBindByName(column = "部門")
	@CsvBindByPosition(position = 7)
	private String department;
//	@CsvBindByName(column = "部課")
	@CsvBindByPosition(position = 8)
	private String section;
//	@CsvBindByName(column = "グループ・チーム")
	@CsvBindByPosition(position = 9)
	private String group;
//	@CsvBindByName(column = "役職")
	@CsvBindByPosition(position = 10)
	private String position;
//	@CsvBindByName(column = "入社年")
	@CsvBindByPosition(position = 11)
	private String joinedYear;
//	@CsvBindByName(column = "メールアドレス(Gmail)")
	@CsvBindByPosition(position = 12)
	private String id;
//	@CsvBindByName(column = "携帯電話番号")
	@CsvBindByPosition(position = 13)
	private String phoneNumber;
//	@CsvBindByName(column = "メールアドレス(携帯電話)")
	@CsvBindByPosition(position = 14)
	private String phoneAddress;
//	@CsvBindByName(column = "カラム")
	@CsvBindByPosition(position = 18)
	private int column;
//	@CsvBindByName(column = "ライン")
	@CsvBindByPosition(position = 19)
	private int line;
//	@CsvBindByName(column = "管理者")
	@CsvBindByPosition(position = 20)
	private Boolean admin;

}
