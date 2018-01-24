package com.congruent.compulaw.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.congruent.compulaw.domain.User;

public interface PersonRepository extends PagingAndSortingRepository<User, Long> {
	
	public User findByEmail(String username);
	
	//public User getCurrentUser();
	
}

