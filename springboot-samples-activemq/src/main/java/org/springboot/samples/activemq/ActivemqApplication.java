package org.springboot.samples.activemq;

import org.springboot.samples.common.SamplesApplication;
import org.springboot.samples.common.SamplesApplicationRunner;
import org.springframework.jms.annotation.EnableJms;

@SamplesApplication
@EnableJms
public class ActivemqApplication {

	public static void main(String[] args) {
		SamplesApplicationRunner.run(ActivemqApplication.class, args);
	}

}
