package unirita.seat.map.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonForm {
	private int column;
	private int line;
	private int start_column;
	private int start_line;
	private boolean oc;

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

	public int getStart_column(){
		return start_column;
	}

	public void setStart_column(int start_column){
		this.start_column = start_column;
	}

	public int getStart_line(){
		return start_line;
	}

	public void setStart_line(int start_line){
		this.start_line = start_line;
	}

	public boolean getOc(){
		return oc;
	}

	public void setOc(boolean oc){
		this.oc = oc;
	}
}