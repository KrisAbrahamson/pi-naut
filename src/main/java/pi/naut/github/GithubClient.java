package pi.naut.github;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;
import pi.naut.github.model.Event;
import pi.naut.github.model.PullRequest;

import java.util.List;

import static pi.naut.github.GithubClientConfiguration.GITHUB_API_URL;

@Client(GITHUB_API_URL)
@Header(name = "User-Agent", value = "${github.user}")
@Header(name = "Authorization", value = "token ${github.token}")
public interface GithubClient {

	@Get("repos/{owner}/{repo}/pulls/{number}")
	PullRequest getPullRequest(String owner, String repo, String number);

	@Get("users/${github.user}/events")
	List<Event> getCurrentUserEvents();

}