package pi.naut.github.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PullRequest {

	private long id;
	private int number;
	private String url;
	private String title;
	private PullRequestState state;
	private boolean mergable;
	@JsonProperty("mergeable_state")
	private String mergableState;
	@JsonProperty("review_comments")
	private int reviewComments;

	public long getId() { return id; }

	public void setId(long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isMergable() {
		return mergable;
	}

	public void setMergable(boolean mergable) {
		this.mergable = mergable;
	}

	public String getMergableState() {
		return mergableState;
	}

	public void setMergableState(String mergableState) {
		this.mergableState = mergableState;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getReviewComments() {
		return reviewComments;
	}

	public void setReviewComments(int reviewComments) {
		this.reviewComments = reviewComments;
	}

	public PullRequestState getState() { return state; }

	public void setState(PullRequestState state) { this.state = state; }

	@Override
	public String toString() {
		return title;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof PullRequest) {
			return ((PullRequest) o).getId() == id;
		}
		return false;
	}

}