package com.congruent.compulaw.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.congruent.compulaw.domain.User;
import com.congruent.compulaw.domain.Role;
import com.congruent.compulaw.exception.AuthenticationException;
import com.congruent.compulaw.repository.PersonRepository;
import com.congruent.compulaw.service.PersonService;

import com.google.common.collect.Lists;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service("userAuthenticationService")
@Repository
@Transactional
public class PersonServiceImpl implements PersonService, UserDetailsService {

	@Autowired
	private PersonRepository personRepository;
	
	final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return Lists.newArrayList(personRepository.findAll()); 
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(Long id) {
		return personRepository.findOne(id);
	}

	@Override
	public User save(User user) {	
		return personRepository.save(user);
	}
	
	@Override
	public User getAccount(String username) {
		return this.personRepository.findByEmail(username);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Page<User> findAllByPage(Pageable pageable) {
		return personRepository.findAll(pageable);
	}
	
	@Transactional(readOnly=true)
    public User findByUsername(String email) {
       
		User user = personRepository.findByEmail(email);       
        if (logger.isDebugEnabled()) {
            if (user==null) {
                logger.trace("User not found: " + email);
            } else {
                logger.trace("User found: " + email);
                Iterator<Role> roleIterator = user.getRoles().iterator();
                while(roleIterator.hasNext()) {
                    //
                	Role role = roleIterator.next();
                    logger.trace("\tUser role: " + role.getRoleName());
                }
            }
        }
        return user;
    }
	
	/**
     * Implementation of UserDetailsService interface's loadUserByUsername method. The returned 
     * User object facilitate spring security to validate user credentials and decide on access 
     * to resources.
     * @param username username of the logged in user
     * @return Spring security user object for the logged in user
     */
    /*@Override
	public org.springframework.security.core.userdetails.User loadUserByUsername(String email) {
        logger.debug("Spring security loading user details for user: " + email);
        User user = personRepository.findByEmail(email);
        Set<Role> roles = user.getRoles();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        //
        Iterator<Role> iterator = roles.iterator();
        while(iterator.hasNext()) {
            authorities.add(new SimpleGrantedAuthority(iterator.next().getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(email, user.getPassword(), authorities);
    }*/

	@Override
	/**
     * This method returns the User entity object of the session's signed in user
     * @return User entity object of the current session's signed in user
     */
    public User getCurrentUser() {
        return personRepository.findByEmail(
                ((org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().
                        getAuthentication().getPrincipal()).getUsername());
    }

	@Override
	public User login(String username, String password) throws AuthenticationException{ 
		User account = this.personRepository.findByEmail(username);
		if (account != null) {
			String pwd = DigestUtils.sha256Hex(password);//+ "{" + username + "}"
			if (!account.getPassword().equalsIgnoreCase(pwd)) {
				throw new AuthenticationException("Wrong username/password combination.", "invalid.password");
				//System.out.println("Wrong username/password combination. " + "invalid.password");
			}
		} else {
			throw new AuthenticationException("Wrong username/password combination.", "invalid.username");
			//System.out.println("Wrong username/password combination. " + "invalid.username");
		}
		return account;
	}

	@Override
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
			logger.info("Loading user record for user name: {}", userName);
		
		UserDetails userDetails = null;   
		
		User user = personRepository.findByEmail(userName);
		
		if (user != null) {
			
			String password = user.getPassword();
            
            Set<Role> roles = user.getRoles();
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            for (Role role: roles) {
                String roleName = role.getRoleName();
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleName);
                authorities.add(grantedAuthority);
            }   
            
            userDetails = new org.springframework.security.core.userdetails.User(userName, password, authorities);
			
		} else {
            // If username not found, throw exception
            throw new UsernameNotFoundException("User " + userName + " not found");
		}
		
		return userDetails;
	}
	 
}
