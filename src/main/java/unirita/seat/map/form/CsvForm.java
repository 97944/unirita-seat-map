package unirita.seat.map.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsvForm {

	private int column;
	private int line;
	private String number;
	private String name;

	public int getColumn(){
		return column;
	}

	public void setColumn(int column){
		this.column = column;
	}

	public int getLine(){
		return line;
	}

	public void setLine(int line){
		this.line = line;
	}

	public String getNumber(){
		return number;
	}

	public void setNumber(String number){
		this.number = number;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

}