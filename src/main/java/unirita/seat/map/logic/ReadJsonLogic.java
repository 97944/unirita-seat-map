package unirita.seat.map.logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import unirita.seat.map.form.JsonForm;

@Service
public class ReadJsonLogic {
	public List<JsonForm> getJsonData() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(new File("src/main/resources/json/seatmap.json"));
		List<JsonForm> data = new ArrayList<>();
		for (int i = 0; i < (root.get("seat")).size(); i++) {
			JsonForm json = new JsonForm();
			int column = root.get("seat").get(i).get("column").asInt();
			int line = root.get("seat").get(i).get("line").asInt();
			int start_column = root.get("seat").get(i).get("start_column").asInt();
			int start_line = root.get("seat").get(i).get("start_line").asInt();
			boolean oc = root.get("seat").get(i).get("oc").asBoolean();
			json.setColumn(column);
			json.setLine(line);
			json.setStart_column(start_column);
			json.setStart_line(start_line);
			json.setOc(oc);
			data.add(json);
		}
		return data;
	}
}