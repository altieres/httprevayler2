package httprevayler.src.basis

import groovy.json.JsonBuilder;
import groovy.json.JsonSlurper;

import javax.servlet.http.HttpServletRequest

import org.eclipse.jetty.jmx.ObjectMBean;

class SimpleRequest implements Serializable {

	def method, url, uri
	Map<String, String[]> parameterMap
	// TODO: transient or static?!
	transient Map<String, String> namedParamsMap = [:]
	
	public SimpleRequest() { }
	
	public SimpleRequest (HttpServletRequest request) {
		method = request.getMethod()
		url = request.getRequestURL()
		uri = request.getRequestURI()
		if (request.getHeader('Content-Type') == 'application/json')
			parameterMap = mapJsonData(request.getReader())
		else
			parameterMap = request.getParameterMap()
	}
	
	public Map<String, String[]> mapJsonData(BufferedReader reader) {
		return new JsonSlurper().parse(reader)
	}
	
}
