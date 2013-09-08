package httprevayler.src.basis

class SimpleResource {
	
	protected static Object domain
	protected SimpleRequest request
	
	def run(SimpleRequest request) {
		this.request = request
		
		try {
			switch (request.method) {
				case "GET": return doGet()
				case "POST": return doPost()
				case "PUT": return doPut()
				case "DELETE": return doDelete()
			}
		} catch (MissingMethodException ex) { }
		
		throw new MethodNotAllowedException(request.method)
	}
	
}
