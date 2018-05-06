package unirita.seat.map.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import unirita.seat.map.domain.account.Account;
import unirita.seat.map.domain.account.AccountRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	AccountRepository accountRepository;

	@GetMapping
	public ModelAndView home(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Account account = accountRepository.findById(username);
		logger.info("id:" + username + " 名前:" + account.getFamilyName() + " " + account.getGivenName() + " 管理者:" + account.getAdmin());
		if(account == null || !account.getAdmin()){
			throw new AccessDeniedException("");
		}
		ModelAndView mav = new ModelAndView("seatForm");
		mav.addObject("user", account);

		return mav;
	}
}
