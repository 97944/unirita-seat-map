package unirita.seat.map.logic;

import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import unirita.seat.map.form.SeatForm;

@Service
public class WriteJsonLogic {

	public void setJsonData(List<SeatForm> form) throws JsonProcessingException {
		JSONObject json = new JSONObject();

		for (int i = 0; i < form.size(); i++) {

		}
	}
}