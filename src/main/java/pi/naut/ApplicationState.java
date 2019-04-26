package pi.naut;

import io.micronaut.context.event.ApplicationEventPublisher;
import io.micronaut.scheduling.annotation.Scheduled;
import pi.naut.github.GithubService;
import pi.naut.github.model.PullRequest;
import pi.naut.gpio.bonnet.display.RefreshDisplayEvent;
import pi.naut.gpio.bonnet.layout.PullRequestDetailsLayout;
import pi.naut.gpio.bonnet.layout.PullRequestLayout;
import pi.naut.gpio.bonnet.layout.RuntimeStatsLayout;
import util.StateList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

@Singleton
public class ApplicationState {

	@Inject
	private ApplicationEventPublisher applicationEventPublisher;
	@Inject
	private GithubService githubService;

	private StateList<PullRequest> openPullRequests = new StateList<>(emptyList());
	private StateList<String> runtimeStats = new StateList<>(emptyList());

	@PostConstruct
	void initialize() {
		openPullRequests.next(githubService.getOpenPullRequests());
		runtimeStats = getRuntimeStats();
	}

	@Scheduled(fixedRate = "1m")
	void updatePullRequests() {
		openPullRequests.next(githubService.getOpenPullRequests());
		applicationEventPublisher.publishEvent(new RefreshDisplayEvent(PullRequestLayout.class, PullRequestDetailsLayout.class));
	}
	public StateList<PullRequest> getOpenPullRequests() { return openPullRequests; }

	@Scheduled(fixedRate = "1s")
	void updateRuntimeStats() {
		runtimeStats = runtimeStats();
		applicationEventPublisher.publishEvent(new RefreshDisplayEvent(RuntimeStatsLayout.class));
	}
	public StateList<String> getRuntimeStats() { return runtimeStats; }

	private StateList<String> runtimeStats() {
		return new StateList<>(asList(
				"Cores: " + Runtime.getRuntime().availableProcessors(),
				"Free mem: " + Runtime.getRuntime().freeMemory(),
				"Max mem: " + Runtime.getRuntime().maxMemory(),
				"Tot mem: " + Runtime.getRuntime().totalMemory()));
	}

}