package httprevayler.test.basis;

import static org.junit.Assert.*
import httprevayler.src.basis.SimpleRequest
import httprevayler.src.basis.SimpleResource
import httprevayler.test.basis.helpers.MapableSimpleRequest

import org.junit.Test

class SimpleResourceTest {
	
	@Test
	void shouldRespondToGet() {
		def resource = new SimpleResourceDummy()
		assert "Getted" == resource.run(new MapableSimpleRequest(method:"GET"))
	}
	
	@Test
	void shouldRespondToPost() {
		def resource = new SimpleResourceDummy()
		assert "Posted" == resource.run(new MapableSimpleRequest(method:"POST"))
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