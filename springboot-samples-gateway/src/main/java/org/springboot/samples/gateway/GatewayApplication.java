package org.springboot.samples.gateway;

import org.springboot.samples.common.SamplesApplication;
import org.springboot.samples.common.SamplesApplicationRunner;
import org.springframework.web.reactive.config.EnableWebFlux;

@SamplesApplication
@EnableWebFlux
public class GatewayApplication {

	public static void main(String[] args) {
		SamplesApplicationRunner.run(GatewayApplication.class, args);
	}

}
