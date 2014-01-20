package httprevayler.test.basis.exceptions;

import static org.junit.Assert.*
import httprevayler.src.basis.ApplicationServlet
import httprevayler.src.basis.SimpleRequest
import httprevayler.src.basis.SimpleResource
import httprevayler.src.basis.SimpleResponse
import httprevayler.src.basis.SimpleResponseDummy
import httprevayler.src.basis.exceptions.BusinessException
import httprevayler.test.basis.helpers.MapableSimpleRequest

import org.junit.Test

class BusinessExceptionTest {
	
	@Test
	void shouldRespondToGet() {
		def application = new ApplicationServlet(null, new SimpleResource() {
			def run(SimpleRequest request, SimpleResponse response) {
				throw new BusinessException(['error1':'message1', 'error2':'message2']);
			}
		})
		def request = new MapableSimpleRequest(method: 'get')
		def response = new SimpleResponseDummy()
		application.service(request, response)
		assert response.wroteData == '{"error1":"message1","error2":"message2"}'
	}
	
}
