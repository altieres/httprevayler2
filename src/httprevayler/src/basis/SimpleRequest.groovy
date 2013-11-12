package httprevayler.src.basis

import com.fasterxml.jackson.databind.ObjectMapper

class SimpleRequest implements Serializable {
	
	private static final long serialVersionUID = 1L
	
	String method, url, uri
	Map<String, String[]> parameterMap
	Map<String, String> headersMap = [:]
	// TODO: transient or static?!
	// transient properties can't be instantiated here
	protected transient Map parsedMap
	protected transient Map<String, String> namedParamsMap
	
	public Map getParsedMap() {
		if (parsedMap == null) parsedMap = [:]
		parsedMap
	}
	
	public Map getNamedParamsMap() {
		if (namedParamsMap == null) namedParamsMap = [:]
		namedParamsMap
	}
	
	public SimpleRequest() { }
	
	public SimpleRequest(def request) {
		parsedMap = [:]
		method = request.getMethod()
		url = request.getRequestURL()
		uri = request.getRequestURI()
		
		request.getHeaderNames().each { name ->
			headersMap += [(name):request.getHeader(name)]
		}
		
		if (request.getHeader('Content-Type')?.startsWith('application/json'))
			parameterMap = mapJsonData(request.getReader())
		else {
			parameterMap = [:]
			parameterMap << request.getParameterMap()
		}
	}
		
	public Map<String, String[]> mapJsonData(BufferedReader reader) {
		ObjectMapper mapper = new ObjectMapper()
		mapper.readValue(reader, Object)
	}

}
