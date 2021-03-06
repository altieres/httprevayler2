package httprevayler.test.basis.helpers;

import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.JSON

class RestClient {
	private String url, path

	RestClient(String url, path) {
		this.url = url
		this.path = path
	}

	void get(Closure closure) {
		def http = new RESTClient(url)
		def args = [requestContentType:JSON, path:path]
		println args
		closure.call(http.get(args))
	}
	
	void post(String data, Closure closure) {
		// more info: http://groovy.codehaus.org/modules/http-builder/doc/rest.html
		def http = new RESTClient(url)
		def args = [requestContentType:JSON, path:path, body:data]
		closure.call(http.post(args))
	}
}