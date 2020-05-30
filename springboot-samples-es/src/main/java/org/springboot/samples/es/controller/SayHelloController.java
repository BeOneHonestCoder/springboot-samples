package org.springboot.samples.es.controller;

import org.springboot.samples.es.repository.ElasticSearchRepository;
import org.springboot.samples.es.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {

	@Autowired
	private ElasticSearchRepository repository;

	@RequestMapping("/sayHello")
	public String sayHello() throws Exception {
		repository.indexRequest(new User(1, "zhangsan"));

		return "Hello";
	}

}
