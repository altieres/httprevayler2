package httprevayler.test.basis;

import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*
import static org.junit.Assert.*
import groovyx.net.http.*
import httprevayler.src.basis.SimpleResource

import org.apache.http.message.BasicStatusLine;
import org.junit.Test

class FileResourceHandlingTest extends PrevalentTestBase {
	
	@Test
	void shouldRespondWithFile() {
		server.addResourceFolder('./src/httprevayler/test/basis/static_files/')
		startServer()
		HTTPBuilder http = new HTTPBuilder('http://localhost:8484/')
		def responseStatus = null;
		def responseText = null
		def response = http.request(GET, TEXT) {
			uri.path = 'index.html'
			response.success = { resp, data ->
				responseStatus = resp.statusLine.statusCode
				responseText = new Scanner(data).useDelimiter("\\A").next()
			}
		}
		assertEquals(200, responseStatus)
		assert '<html><body>Hello!</body></html>' == responseText
		stopServer()
	}

	protected startServer() {
		super.startServer(new Object(), new SimpleResource())
	}
	
}