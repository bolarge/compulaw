/**
 * Created on Oct 21, 2011
 */
package com.congruent.compulaw.serviceImpl;

import org.springframework.stereotype.Service;

import com.congruent.compulaw.service.HelloService;

/**
 * @author Clarence
 *
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {

	/* (non-Javadoc)
	 * @see com.apress.prospring3.springblog.service.HelloService#sayHello()
	 */
	@Override
	public String sayHello() {
		return "Hello JPA Imlementation!";
	}

}
