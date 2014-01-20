package httprevayler.test.basis;

import static org.junit.Assert.*
import httprevayler.src.basis.SimpleRequest
import httprevayler.src.basis.SimpleResource
import httprevayler.src.basis.SimpleResponse
import httprevayler.src.basis.SimpleResponseDummy
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
	
	@Test
	void shouldBeAbleToSetHeaders() {
		def resource = new SimpleResourceDummy()
		def response = new SimpleResponseDummy()
		resource.run(new MapableSimpleRequest(method:"OPTIONS"), response)
		assert response.settedHeaders == ['a':'b']
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
	
	def doOptions() {
		response.setHeader('a', 'b')
	}
	
	def run(SimpleRequest request) {
		run(request, null)
	}
	
}