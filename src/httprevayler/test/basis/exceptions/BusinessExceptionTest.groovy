package httprevayler.test.basis.exceptions;

import static org.junit.Assert.*
import httprevayler.src.basis.ApplicationServlet
import httprevayler.src.basis.SimpleRequest
import httprevayler.src.basis.SimpleResource
import httprevayler.src.basis.SimpleResponse
import httprevayler.src.basis.exceptions.BusinessException

import org.junit.Test

class BusinessExceptionTest {
	
		@Test
	void shouldRespondToGet() {
		def application = new ApplicationServlet(null, new SimpleResource() {
			def run(SimpleRequest request) {
				throw new BusinessException(['error1':'message1', 'error2':'message2']);
			}
		})
		def request = new SimpleRequest(method: 'get')
		def response = new SimpleResponse() {
			def data
			public void setHeader(String header, String value) {}
			public void writeResponse(String data) { this.data = data}
			public void setStatus(int status) {}
		}
		application.service(request, response)
		assert response.data == '{"error1":"message1","error2":"message2"}'
	}
	
}
