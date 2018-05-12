package org.springboot.samples.consul;

import org.springboot.samples.common.SamplesApplication;
import org.springboot.samples.common.SamplesApplicationRunner;

@SamplesApplication
public class ConsulApplication {

	public static void main(String[] args) {
		SamplesApplicationRunner.run(ConsulApplication.class, args);
	}

}
