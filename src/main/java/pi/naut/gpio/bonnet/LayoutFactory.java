package pi.naut.gpio.bonnet;

import static java.util.Arrays.asList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Primary;
import pi.naut.gpio.bonnet.layout.PullRequestLayout;
import pi.naut.gpio.bonnet.layout.RuntimeStatsLayout;
import pi.naut.gpio.bonnet.layout.WelcomeLayout;
import util.StateList;

@Factory
class LayoutFactory {

	@Inject
	private WelcomeLayout welcomeLayout;
	
	@Inject
	private PullRequestLayout pullReqeustLayout;

	@Bean
	@Primary
	@Singleton
	StateList<Layout> layouts() {
		return new StateList<>(asList(
				welcomeLayout, pullReqeustLayout
		), true);
	}

}
