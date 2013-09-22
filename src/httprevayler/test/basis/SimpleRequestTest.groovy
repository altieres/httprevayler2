package httprevayler.test.basis;

import static org.junit.Assert.*
import httprevayler.src.basis.SimpleRequest

import javax.servlet.http.HttpServletRequest

import org.junit.Before
import org.junit.Test

class SimpleRequestTest {
	
	HttpServletRequest httpRequest
	SimpleRequest simpleRequest
	
	@Before void before() {
		simpleRequest = new SimpleRequest(new FakeHttpRequest())
	}
	
	@Test void shouldHaveMethod() {
		assert simpleRequest.method == 'get'
	}
	
	@Test void shouldHaveRequestUrlAndUri() {
		assert simpleRequest.url == 'http://localhost/fooz'
		assert simpleRequest.uri == '/fooz'
	}
	
	@Test void shouldHaveParameter() {
		assert simpleRequest.parameterMap == ['one':'two']
	}
	
	@Test void shouldHaveHeaders() {
		assert simpleRequest.headersMap == ['fooz':'baaz']
	}

}

class FakeHttpRequest {
	def headers = ['fooz':'baaz']
	
	String getMethod() { 'get' }
	StringBuffer getRequestURL() { new StringBuffer('http://localhost/fooz') }
	String getRequestURI() { '/fooz' }
	def getParameterMap() { [one:'two'] }
	def getHeaderNames() { headers.keySet() }
	String getHeader(name) { headers[name] }
}