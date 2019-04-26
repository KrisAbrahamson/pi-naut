package pi.naut.gpio.bonnet.layout;

import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.trigger.GpioTrigger;
import io.micronaut.context.event.ApplicationEventPublisher;
import pi.naut.ApplicationState;
import pi.naut.gpio.bonnet.Layout;
import pi.naut.gpio.bonnet.OLEDBonnet;
import pi.naut.gpio.bonnet.display.DisplayComponents;
import pi.naut.gpio.input.listener.NavigateToLayoutListener;
import pi.naut.gpio.input.listener.NextStateListener;
import pi.naut.gpio.input.listener.PreviousStateListener;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

import static pi.naut.gpio.PinConfiguration.*;

@Singleton
public class PullRequestLayout implements Layout {

	@Inject
	private DisplayComponents displayComponents;
	@Inject
	private ApplicationState applicationState;
	@Inject
	private ApplicationEventPublisher applicationEventPublisher;

	public static final String TITLE = "PULL REQUESTS";

	@Override
	public boolean isPrimary() { return true; }

	@Override
	public void bufferComponents() {
		displayComponents.titleBar(TITLE);
		displayComponents.scrollableList(applicationState.getOpenPullRequests());
	}

	@Override
	public Map<String, GpioPinListener> applyListeners(OLEDBonnet oledBonnet) {
		Map<String, GpioPinListener> listenerMap = new HashMap<>();

		listenerMap.put(JOYSTICK_UP, new PreviousStateListener(
				applicationEventPublisher, applicationState.getOpenPullRequests(), PullRequestLayout.class));
		listenerMap.put(JOYSTICK_DOWN, new NextStateListener(
				applicationEventPublisher, applicationState.getOpenPullRequests(), PullRequestLayout.class));

		listenerMap.put(BUTTON_B, new NavigateToLayoutListener(oledBonnet, PullRequestDetailsLayout.class));

		return listenerMap;
	}

	@Override
	public Map<String, GpioTrigger> applyTriggers(OLEDBonnet oledBonnet) {
		return new HashMap<>();
	}

}