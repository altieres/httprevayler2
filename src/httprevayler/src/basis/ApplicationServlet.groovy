package httprevayler.src.basis

import groovy.json.JsonBuilder
import httprevayler.src.basis.exceptions.SimpleException

class ApplicationServlet {
	
	static SimpleResource service
	
	public ApplicationServlet(Object domain, SimpleResource service) {
		this.service = service
		service.domain = domain
	}
	
	public service(SimpleRequest request, SimpleResponse response) {
		def responseData = null
		response.setHeader("content-type", "application/json")
		response.setStatus(StatusCodes.defaultFor(request.method))
		try {
			responseData = service.run(request)
		} catch (SimpleException ex) {
			response.setStatus(ex.statusCode)
			responseData = ex.errorData
		}
		def encodedResponseData = encodeResponseData(responseData)
		println '(' + request.method + ':' + request.uri + '): ' + encodedResponseData
		response.writeResponse(encodedResponseData)
	}
	
	public String encodeResponseData(responseData) {
		JsonBuilder builder = new JsonBuilder(responseData);
		builder.toString();
	}
	
}
