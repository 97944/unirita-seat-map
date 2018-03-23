package unirita.seat.map.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import unirita.seat.map.form.CsvForm;
import unirita.seat.map.form.JsonForm;
import unirita.seat.map.form.SeatForm;
import unirita.seat.map.logic.ReadCsvLogic;
import unirita.seat.map.logic.ReadJsonLogic;
import unirita.seat.map.logic.WriteJsonLogic;

@RestController
public class FileController {

	@Autowired
	ReadCsvLogic readCsvLogic;
	@Autowired
	ReadJsonLogic readJsonLogic;
	@Autowired
	WriteJsonLogic writeJsonLogic;

	@GetMapping("/home/getCsv")
	public List<CsvForm> getCsvData() throws IOException {
		return readCsvLogic.getCsvData();
	}

	@GetMapping("/home/getJson")
	public List<JsonForm> getJsonData() throws IOException {
		return readJsonLogic.getJsonData();
	}

	@PostMapping("/home/setJson")
	public void setJsonData(
			@RequestBody List<SeatForm> form) throws JsonProcessingException {
		writeJsonLogic.setJsonData(form);
	}
}
