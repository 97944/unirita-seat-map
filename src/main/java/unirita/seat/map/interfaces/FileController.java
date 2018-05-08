package unirita.seat.map.interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import unirita.seat.map.domain.account.Account;
import unirita.seat.map.domain.account.AccountRepository;
import unirita.seat.map.form.CsvForm;
import unirita.seat.map.form.JsonForm;
import unirita.seat.map.form.SeatDTO;
import unirita.seat.map.form.SeatForm;
import unirita.seat.map.logic.Member;
import unirita.seat.map.logic.MemberList;
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
	@Autowired
	AccountRepository accountRepository;

	@GetMapping("/home/getCsv")
	public List<CsvForm> getCsvData() throws IOException {
		return readCsvLogic.getCsvData();
	}

	@GetMapping("/home/getJson/{floor}")
	public List<JsonForm> getJsonData(@PathVariable(value = "floor") String floor) throws IOException {
		return readJsonLogic.getJsonData(floor);
	}

	@PostMapping("/home/setJson")
	public void setJsonData(
			@RequestBody List<SeatForm> form) throws JsonProcessingException {
		writeJsonLogic.setJsonData(form);
	}

	@RequestMapping(path = "home/home")
	@ResponseBody
	public  List<Member> memberList() {
		MemberList ml = new MemberList();
		ArrayList<Member> mlist = ml.getMemberList();
		return mlist;
    }

	@PostMapping("/home/import")
	public ModelAndView csvImport(@RequestParam("csv")MultipartFile file) throws IOException, CsvException{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Account loginUser = accountRepository.findById(username);
		if(loginUser == null || !loginUser.getAdmin()){
			throw new AccessDeniedException("");
		}
		InputStreamReader in = new InputStreamReader(file.getInputStream(),"Shift-JIS");
		Reader reader = new BufferedReader(in);

		@SuppressWarnings({ "unchecked", "rawtypes" })
		CsvToBean<SeatDTO> csvToBean = new CsvToBeanBuilder(reader)
	    		.withType(SeatDTO.class)
	    		.withIgnoreLeadingWhiteSpace(true)
	    		.build();

		List<SeatDTO> dtos = new ArrayList<>();
		try{
			dtos = csvToBean.parse();
		}catch(RuntimeException e){
			throw new CsvException();
		}

		for(SeatDTO dto : dtos){
//			/System.out.println(dto.getEmployeeNum() + ":" + dto.getFamilyName() + " " + dto.getGivenName() + ":" + dto.getPhoneNumber() + ":" + dto.getDivision());
			Account account = accountRepository.findById(dto.getId());
			if(account == null){
				account = new Account();
				account.setId(dto.getId());
//				account.setAdmin(dto.getAdmin());
				account.setDivision(dto.getDivision());
				account.setDepartment(dto.getDepartment());
				account.setSection(dto.getSection());
				account.setEmployeeNum(dto.getEmployeeNum());
				account.setFamilyName(dto.getFamilyName());
				account.setFamilyNameKana(dto.getFamilyNameKana());
				account.setGivenName(dto.getGivenName());
				account.setGivenNameKana(dto.getGivenNameKana());
				account.setGroup(dto.getGroup());
				account.setPhoneNumber(dto.getPhoneNumber());
				account.setPhoneAddress(dto.getPhoneAddress());
//				account.setColumn(dto.getColumn());
//				account.setLine(dto.getLine());
				account.setPosition(dto.getPosition());
				account.setJoinedYear(dto.getJoinedYear());

				accountRepository.save(account);
			}
		}

		return new ModelAndView("redirect:/home");
	}
}