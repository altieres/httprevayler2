package httprevayler.test.basis;

import static org.junit.Assert.*
import httprevayler.src.basis.SimpleGuard
import httprevayler.src.basis.SimpleRequest
import httprevayler.src.basis.SimpleResource
import httprevayler.src.basis.exceptions.UnauthorizedException
import httprevayler.test.basis.helpers.MapableSimpleRequest

import org.junit.Before
import org.junit.Test

class SimpleGuardTest {
	
	def simpleGuard
	
	@Before void before() {
		simpleGuard = new SimpleDummyGuard(resource: new BarResource())
	}
	
	@Test void shouldGrantAccess() {
		assert simpleGuard.run(new MapableSimpleRequest(uri:"/fooz")) == 'ran!'
	}
	
	@Test void shouldDenyAccess() {
		try {
			simpleGuard.run(new MapableSimpleRequest(uri:"/baaz"))
			fail 'expected exception not thrown'
		} catch(UnauthorizedException ex) { }
	}

}

class SimpleDummyGuard extends SimpleGuard {
	boolean grant() { request.uri == '/fooz' }
}

class BarResource extends SimpleResource {
	def run(SimpleRequest request) { 'ran!' }
}