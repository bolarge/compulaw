package com.congruent.compulaw.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congruent.compulaw.domain.Role;
import com.congruent.compulaw.repository.RoleRepository;
import com.congruent.compulaw.service.RoleService;
import com.congruent.compulaw.web.mvc.RoleController;
import com.google.common.collect.Lists;

@Service("roleService")
@Repository
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Override
	public List<Role> findAll() {

		return Lists.newArrayList(roleRepository.findAll());
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);

	}

}
