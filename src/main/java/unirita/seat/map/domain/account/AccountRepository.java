package unirita.seat.map.domain.account;

import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends org.springframework.data.repository.Repository<Account,String> {
	Account findById(String id);

	void save(Account account);
}
