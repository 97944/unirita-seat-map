package unirita.seat.map.interfaces;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import unirita.seat.map.domain.account.Account;
import unirita.seat.map.domain.account.AccountRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	AccountRepository accountRepository;

	@GetMapping
	public String home(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Account> account = accountRepository.findById(username);
		logger.info("id:" + username + " 名前:" + account.get().getFamilyName() + " " + account.get().getGivenName() + " 管理者:" + account.get().getAdmin());
		return "index";
	}
}
