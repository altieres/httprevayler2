package httprevayler.src.basis

import org.eclipse.jetty.server.Response

class StatusCodes {
	static OK = Response.SC_OK
	static CREATED = Response.SC_CREATED
	static NOT_FOUND = Response.SC_NOT_FOUND
	static METHOD_NOT_ALLOWED = Response.SC_METHOD_NOT_ALLOWED
	static BAD_REQUEST = Response.SC_BAD_REQUEST
	static UNAUTHORIZED = Response.SC_UNAUTHORIZED
	
	static defaultFor(String method) {
		method.toLowerCase() == 'put' ? CREATED : OK
	} 
}
