package pi.naut.gpio.bonnet.layout;

import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.trigger.GpioTrigger;
import pi.naut.ApplicationState;
import pi.naut.github.model.PullRequest;
import pi.naut.gpio.bonnet.Layout;
import pi.naut.gpio.bonnet.OLEDBonnet;
import pi.naut.gpio.bonnet.display.DisplayComponents;
import pi.naut.gpio.input.listener.NavigateToLayoutListener;
import util.StateList;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;
import static pi.naut.gpio.PinConfiguration.BUTTON_A;

@Singleton
public class PullRequestDetailsLayout implements Layout {

	@Inject
	private DisplayComponents displayComponents;
	@Inject
	private ApplicationState applicationState;

	@Override
	public boolean isPrimary() { return false; }

	@Override
	public void bufferComponents() {
		displayComponents.titleBar(applicationState.getOpenPullRequests().current().getTitle(), false);
		displayComponents.paginatedList(getPullRequestDetails());
	}

	private StateList<String> getPullRequestDetails() {
		PullRequest currentPullRequest = applicationState.getOpenPullRequests().current();
		return new StateList<>(asList(
				"NUMBER: " + currentPullRequest.getNumber(),
				"MERGEABLE: " + currentPullRequest.isMergable(),
				"MERGE STATE: " + currentPullRequest.getMergableState(),
				"REVIEW COMMENTS: " + currentPullRequest.getReviewComments()
		));
	}

	@Override
	public Map<String, GpioPinListener> applyListeners(OLEDBonnet oledBonnet) {
		Map<String, GpioPinListener> listenerMap = new HashMap<>();
		listenerMap.put(BUTTON_A, new NavigateToLayoutListener(oledBonnet, PullRequestLayout.class));
		return listenerMap;
	}

	@Override
	public Map<String, GpioTrigger> applyTriggers(OLEDBonnet oledBonnet) {
		return new HashMap<>();
	}

}