package unirita.seat.map.logic;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import liquibase.util.csv.opencsv.CSVReader;
import unirita.seat.map.form.CsvForm;

@Service
public class ReadCsvLogic {

/*	public String[][] getCsvData() throws IOException {
		CSVReader reader = new CSVReader(new FileReader(new File("csv/seatmap.csv")));
	    String [][] data = new String[][] {};
	    String[] temp = new String[3];
	    while ((temp = reader.readNext()) != null) {
	    	data[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] = temp[2];
	    }
	    return data;
	}*/

	public List<CsvForm> getCsvData() throws IOException {
		CSVReader reader = new CSVReader (new FileReader(new File("src/main/resources/csv/seatmap.csv")));
		List<CsvForm> data = new ArrayList<>();
		String[] temp = new String[4];
		temp = reader.readNext(); //1行目は読み込まない
		while ((temp = reader.readNext()) != null) {
			CsvForm csv = new CsvForm();
			csv.setColumn(Integer.parseInt(temp[0]));
	    	csv.setLine(Integer.parseInt(temp[1]));
	    	csv.setNumber(temp[2]);
	    	csv.setName(temp[3]);
	    	data.add(csv);
	    }
		return data;
	}
}