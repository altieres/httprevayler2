package httprevayler.src.basis

class SimpleException extends Exception {
	
	def statusCode
	
	public SimpleException(String msg, int statusCode) {
		super(msg)
		this.statusCode = statusCode
	}

}
