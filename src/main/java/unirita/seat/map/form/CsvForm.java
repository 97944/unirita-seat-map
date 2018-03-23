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

}
