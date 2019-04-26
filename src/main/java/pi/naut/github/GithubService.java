package pi.naut.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import pi.naut.github.model.EventType;
import pi.naut.github.model.PullRequest;
import pi.naut.github.model.PullRequestEvent;
import pi.naut.github.model.PullRequestPayload;
import pi.naut.github.model.PullRequestState;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Objects;

import static io.netty.util.internal.StringUtil.EMPTY_STRING;
import static java.util.stream.Collectors.toList;
import static pi.naut.github.GithubClientConfiguration.GITHUB_API_URL;

@Singleton
public class GithubService {

	@Inject
	private GithubClient gitHubClient;

	public List<PullRequest> getOpenPullRequests() {
			return gitHubClient.getCurrentUserEvents()
					.stream()
					.filter(event -> event.getType().equals(EventType.PullRequestEvent.name()))
					.map(event -> new ObjectMapper().convertValue(event.getPayload(), PullRequestEvent.class))
					.map(PullRequestEvent::getPullRequestPayload)
					.map(PullRequestPayload::getUrl)
					.map(this::retrievePullRequest)
					.filter(Objects::nonNull)
					.filter(pr -> pr.getState() == PullRequestState.open)
					.collect(toList());
	}

	private PullRequest retrievePullRequest(String url) {
		String[] splitUrl = url.replace(GITHUB_API_URL, EMPTY_STRING).split("/");
		return gitHubClient.getPullRequest(splitUrl[1], splitUrl[2], splitUrl[4]);
	}

}