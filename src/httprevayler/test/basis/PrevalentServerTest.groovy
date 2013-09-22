package httprevayler.test.basis;

import static org.junit.Assert.*
import httprevayler.src.basis.SimpleResource
import httprevayler.test.basis.helpers.RestClient;

import org.junit.After
import org.junit.Before
import org.junit.Test


class PrevalentServerTest extends PrevalentTestBase {
	
	@Before public void setUp() {
		clearServerData()
		startServer()
	}
	
	@After public void tearDown() {
		stopServer()
		clearServerData()
	}
	
	@Test void shouldRespondToGet() {
		def called = 0
		new RestClient("http://localhost:8484/", "something").get { response ->
			assert response.data.name == "Altz"
			called = 1
		}
		assert called == 1
	}
	
	@Test void shouldPersist() {
		new RestClient("http://localhost:8484/", "something").get { response ->
			assert response.data.name == 'Altz'
			called += 1
		}
		new RestClient("http://localhost:8484/", "something").post('{"name":"Lutz"}') { response ->
			assert response.data.name == 'Lutz'
			called += 1
		}
		stopServer()
		
		startServer()
		new RestClient("http://localhost:8484/", "something").get { response ->
			assert response.data.name == 'Lutz'
			called += 1
		}

		assert called == 3
	}
	
	protected startServer() {
		super.startServer(new FakeDomain(), new FakeResource())
	}
	
}

class FakeDomain implements Serializable {
	String name = 'Altz'
}

class FakeResource extends SimpleResource {
	def doGet() {
		return domain
	}
	
	def doPost() {
		domain.name = request.parameterMap.name
		return domain
	}
}
