package pi.naut.gpio.input.listener;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import devlights.client.DevlightClient;
import pi.naut.devlight.DevlightState;
import util.StateList;

public class ChangeDevlightStateListener implements GpioPinListenerDigital {

	private DevlightClient devlightClient;
	private StateList<DevlightState> devlightStates;

	public ChangeDevlightStateListener(DevlightClient devlightClient, StateList<DevlightState> devlightStates) {
		this.devlightClient = devlightClient;
		this.devlightStates = devlightStates;
	}

	@Override
	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		if (event.getState().isHigh()) {
			devlightClient.setLightState(devlightStates.current().getLightState());
		}
	}

}

