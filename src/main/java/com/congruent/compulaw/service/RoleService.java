package com.congruent.compulaw.service;

import java.util.List;

import com.congruent.compulaw.domain.Role;

public interface RoleService {
	
	// Find all transactions
		public List<Role> findAll();
		public Role save(Role role);
		//public Role delete(Role role);
		//public Role findById(Long id);
}

