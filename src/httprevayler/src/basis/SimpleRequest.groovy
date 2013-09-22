package httprevayler.src.basis

import groovy.json.JsonSlurper

import javax.servlet.http.HttpServletRequest

class SimpleRequest implements Serializable {
	
	private static final long serialVersionUID = 1L
	
	String method, url, uri
	Map<String, String[]> parameterMap
	Map<String, String> headersMap = [:]
	// TODO: transient or static?!
	transient Map<String, String> namedParamsMap = [:]
	
	public SimpleRequest() { }
	
	public SimpleRequest(def request) {
		method = request.getMethod()
		url = request.getRequestURL()
		uri = request.getRequestURI()
		
		request.getHeaderNames().each { name ->
			headersMap += [(name):request.getHeader(name)]
		}
		
		if (request.getHeader('Content-Type') == 'application/json')
			parameterMap = mapJsonData(request.getReader())
		else
			parameterMap = request.getParameterMap()
	}
		
	public Map<String, String[]> mapJsonData(BufferedReader reader) {
		return new JsonSlurper().parse(reader)
	}

}
