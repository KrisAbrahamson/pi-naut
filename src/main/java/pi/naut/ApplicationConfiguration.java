package pi.naut;

import javax.inject.Singleton;

import devlights.client.DevlightClient;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Value;

@Factory
class ApplicationConfiguration {

	@Value("${github.user}")
	private String user;
	@Value("${github.token}")
	private String token;

	@Bean
	@Primary
	@Singleton
	DevlightClient devlightClient() {
		return new DevlightClient(user, token, "http://172.16.1.67:8080");
	}

}
