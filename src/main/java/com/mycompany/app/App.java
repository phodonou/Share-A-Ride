package com.mycompany.app;

import io.dropwizard.Application;
import io.dropwizard.jersey.jackson.JsonProcessingExceptionMapper;
import io.dropwizard.setup.Environment;

public class App extends Application<Config> {

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}

	@Override
	public void run(Config config, Environment env) {
		final SarService personService = new SarService();
		env.jersey().register(personService);
		env.jersey().register(new JsonProcessingExceptionMapper(true));

		env.healthChecks().register("template", 
			new AppHealthCheck(config.getVersion()));
	}
}

// Command to run the app:
//mvn package
// java -jar target/shareAride-1.0-SNAPSHOT.jar server config.yml