package httprevayler.src.basis

import httprevayler.src.basis.exceptions.SimpleException

import com.fasterxml.jackson.databind.ObjectMapper

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
			responseData = service.run(request, response)
		} catch (SimpleException ex) {
			response.setStatus(ex.statusCode)
			responseData = ex.errorData
		}
		
		if (responseData != null) {
			def encodedResponseData = encodeResponseData(responseData)
			println '(' + request.method + ':' + request.uri + '): ' + encodedResponseData
			response.writeResponse(encodedResponseData)
		}
	}
	
	public String encodeResponseData(responseData) {
		ObjectMapper mapper = new ObjectMapper()
		mapper.writeValueAsString(responseData)
	}
	
}
