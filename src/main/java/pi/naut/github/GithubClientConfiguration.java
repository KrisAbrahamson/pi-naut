package pi.naut.github;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;

import static pi.naut.github.GithubClientConfiguration.PREFIX;

@ConfigurationProperties(PREFIX)
public class GithubClientConfiguration {

	static final String PREFIX = "github";
	static final String GITHUB_API_URL   = "https://api.github.com/";

	@Getter
	@Setter
	private String user;

	@Getter
	@Setter
	private String token;

}