package com.congruent.compulaw.repository;

import org.springframework.data.repository.CrudRepository;

import com.congruent.compulaw.domain.Role;


public interface UserRoleRepository extends CrudRepository<Role, Long>{

}
