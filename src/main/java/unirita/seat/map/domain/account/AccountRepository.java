package unirita.seat.map.domain.account;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends org.springframework.data.repository.Repository<Account,String> {
	@PostAuthorize("returnObject != null")
	Account findById(String id);
}
