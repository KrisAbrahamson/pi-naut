package pi.naut.gpio.bonnet.layout;

import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.trigger.GpioTrigger;
import devlights.api.LightStateFactory;
import devlights.client.DevlightClient;
import io.micronaut.context.event.ApplicationEventPublisher;
import pi.naut.devlight.DevlightState;
import pi.naut.gpio.bonnet.Layout;
import pi.naut.gpio.bonnet.OLEDBonnet;
import pi.naut.gpio.bonnet.display.DisplayComponents;
import pi.naut.gpio.input.listener.ChangeDevlightStateListener;
import pi.naut.gpio.input.listener.NextStateListener;
import pi.naut.gpio.input.listener.PreviousStateListener;
import util.StateList;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

import static devlights.api.HueConstants.*;
import static java.util.Arrays.asList;
import static pi.naut.gpio.PinConfiguration.*;

@Singleton
public class DevLightLayout implements Layout {

	@Inject
	private DisplayComponents displayComponents;
	@Inject
	private DevlightClient devlightClient;
	@Inject
	private ApplicationEventPublisher applicationEventPublisher;

	private StateList<DevlightState> devlightStates = new StateList<>(asList(
			new DevlightState("BLINK RED", LightStateFactory.blink(COLOR_RED)),
			new DevlightState("BLINK YELLOW", LightStateFactory.blink(COLOR_YELLOW)),
			new DevlightState("BLINK GREEN", LightStateFactory.blink(COLOR_GREEN)),
			new DevlightState("BLINK BLUE", LightStateFactory.blink(COLOR_BLUE)),
			new DevlightState("SOLID RED", LightStateFactory.solid(COLOR_RED)),
			new DevlightState("SOLID YELLOW", LightStateFactory.solid(COLOR_YELLOW)),
			new DevlightState("SOLID GREEN", LightStateFactory.solid(COLOR_GREEN)),
			new DevlightState("SOLID BLUE", LightStateFactory.solid(COLOR_BLUE)),
			new DevlightState("TURN OFF", LightStateFactory.off())
	));

	public static final String TITLE = "DEV LIGHTS";

	@Override
	public boolean isPrimary() {
		return true;
	}

	@Override
	public void bufferComponents() {
		displayComponents.titleBar(TITLE);
		displayComponents.scrollableList(devlightStates);
	}

	@Override
	public Map<String, GpioPinListener> applyListeners(OLEDBonnet oledBonnet) {
		Map<String, GpioPinListener> listeners = new HashMap<>();

		listeners.put(JOYSTICK_UP, new PreviousStateListener(applicationEventPublisher, devlightStates, this.getClass()));
		listeners.put(JOYSTICK_DOWN, new NextStateListener(applicationEventPublisher, devlightStates, this.getClass()));

		listeners.put(BUTTON_B, new ChangeDevlightStateListener(devlightClient, devlightStates));

		return listeners;
	}

	@Override
	public Map<String, GpioTrigger> applyTriggers(OLEDBonnet oledBonnet) {
		return new HashMap<>();
	}

}