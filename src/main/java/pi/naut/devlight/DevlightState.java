package pi.naut.devlight;

import devlights.api.LightState;

public class DevlightState {

	private String description;
	private LightState lightState;

	public DevlightState(String description, LightState lightState) {
		this.description = description;
		this.lightState = lightState;
	}

	public LightState getLightState() {
		return lightState;
	}

	public void setLightState(LightState lightState) {
		this.lightState = lightState;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return description;
	}

}
