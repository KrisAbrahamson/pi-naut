package pi.naut.gpio.input.listener;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import pi.naut.gpio.bonnet.OLEDBonnet;

public class NavigateToLayoutListener implements GpioPinListenerDigital {

	private OLEDBonnet oledBonnet;
	private Class layoutClass;

	public NavigateToLayoutListener(OLEDBonnet oledBonnet, Class layoutClass) {
		this.oledBonnet = oledBonnet;
		this.layoutClass = layoutClass;
	}

	@Override
	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		if (event.getState().isHigh()) {
			oledBonnet.displayLayout(layoutClass);
		}
	}

}