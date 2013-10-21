package httprevayler.src.basis

import httprevayler.src.basis.exceptions.MethodNotAllowedException;

class SimpleResource {
	
	protected static Object domain
	protected SimpleRequest request
	
	def run(SimpleRequest request) {
		this.request = request
		def methodToRun = "do${request.method.toLowerCase().capitalize()}"
		if (!this.respondsTo(methodToRun))
			throw new MethodNotAllowedException(request.method)
		this."${methodToRun}"()
	}
	
}
