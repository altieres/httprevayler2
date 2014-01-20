package httprevayler.src.basis

import httprevayler.src.basis.exceptions.MethodNotAllowedException

class SimpleResource {
	
	protected static Object domain
	protected SimpleRequest request
	protected SimpleResponse response
	
	def run(SimpleRequest request, SimpleResponse response) {
		this.request = request
		this.response = response
		def methodToRun = "do${request.method.toLowerCase().capitalize()}"
		if (!this.respondsTo(methodToRun))
			throw new MethodNotAllowedException(request.method)
		this."${methodToRun}"()
	}
	
}
