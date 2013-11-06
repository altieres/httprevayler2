package httprevayler.test.basis;

import static org.junit.Assert.*
import httprevayler.src.basis.SimpleResource
import httprevayler.src.basis.exceptions.MethodNotAllowedException
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
	
	@Test
	void shouldThrowOnInvalidMethod() {
		try {
			def resource = new SimpleResourceDummy()
			resource.run(new MapableSimpleRequest(method:"INEXISTENT"))
			fail 'Expected exception not thrown'
		} catch (MethodNotAllowedException ex){ }
	}
	
	@Test
	void shouldThrowOnInexistentMethod() {
		try {
			def resource = new SimpleResourceDummy()
			resource.run(new MapableSimpleRequest(method:"PUT"))
			fail 'Expected exception not thrown'
		} catch (MissingMethodException ex){ }
	}

}

class SimpleResourceDummy extends SimpleResource {
	
	def doGet() {
		"Getted"
	}
	
	def doPost() {
		"Posted"
	}
	
	def doPut() {
		this.inexistent()
	}
}