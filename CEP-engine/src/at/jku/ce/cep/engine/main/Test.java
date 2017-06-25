package at.jku.ce.cep.engine.main;

import org.apache.log4j.Level;
import org.eclipse.viatra.cep.core.api.engine.CEPEngine;
import org.eclipse.viatra.cep.core.metamodels.automaton.EventContext;
import org.eclipse.viatra.cep.core.streams.EventStream;
import org.junit.After;
import org.junit.Before;

import at.jku.ce.cep.engine.internal.DefaultRealm;
import at.jku.ce.cep.engine.model.CepFactory;

public class Test {

	private DefaultRealm defaultRealm;
	private CEPEngine engine;
	private EventStream eventStream;

	@Before
	public void setUp() {
		defaultRealm = new DefaultRealm();
		engine = CEPEngine.newEngine().eventContext(EventContext.CHRONICLE).rules(CepFactory.getInstance().allRules())
				.prepare();
		eventStream = engine.getStreamManager().newEventStream();
	}

	@After
	public void tearDown() {
		eventStream = null;
		engine = null;
		defaultRealm.dispose();
	}

	@org.junit.Test
	public void test() {
		engine.setCepEngineDebugLevel(Level.DEBUG);

		engine.getLogger().info("starting");
		eventStream.push(CepFactory.getInstance().createGPS_Event());
		eventStream.push(CepFactory.getInstance().createW_CON_Event());
		eventStream.push(CepFactory.getInstance().createTIME_Event());

		// Some debug information about the end of the process.
		engine.getLogger().info("ending");
	}

}
