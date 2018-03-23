package unirita.seat.map.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatForm {
	private int column;
	private int line;
	private int start_column;
	private int start_line;
	private boolean half;
}
