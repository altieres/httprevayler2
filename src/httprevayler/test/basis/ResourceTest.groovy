package httprevayler.test.basis;

import static org.junit.Assert.*
import httprevayler.src.basis.SimpleRequest
import httprevayler.src.basis.SimpleResource

import org.junit.Test

class ResourceTest {
	
	@Test
	void shouldRespondToGet() {
		def resource = new SimpleResourceDummy()
		assert "Getted" == resource.run(new SimpleRequest(method:"GET"))
	}
	
	@Test
	void shouldRespondToPost() {
		def resource = new SimpleResourceDummy()
		assert "Posted" == resource.run(new SimpleRequest(method:"POST"))
	}

}

class SimpleResourceDummy extends SimpleResource {
	
	def doGet() {
		"Getted"
	}
	
	def doPost() {
		"Posted"
	}
}