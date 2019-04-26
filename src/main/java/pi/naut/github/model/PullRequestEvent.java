package pi.naut.github.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PullRequestEvent {
	private ActionType action;
	@JsonProperty("pull_request")
	private PullRequestPayload pullRequestPayload;

	public ActionType getAction() {
		return action;
	}

	public void setAction(ActionType action) {
		this.action = action;
	}

	public PullRequestPayload getPullRequestPayload() {
		return pullRequestPayload;
	}

	public void setPullRequestPayload(PullRequestPayload pullRequestPayload) {
		this.pullRequestPayload = pullRequestPayload;
	}
}