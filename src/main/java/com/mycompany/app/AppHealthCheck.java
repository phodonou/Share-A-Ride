package com.mycompany.app;

import com.codahale.metrics.health.HealthCheck;

public class AppHealthCheck extends HealthCheck {
	private final String version;

	public AppHealthCheck(String version) {
		this.version = version;
	}

	@Override
	protected Result check() throws Exception {
        // TODO
        return Result.healthy("HEALTH CHECK NOT YET IMPLMENTED v" + version);
	}
}