package httprevayler.src.basis.exceptions

class SimpleException extends Exception {
	
	def statusCode
	def errorData
	
	public SimpleException(String msg, int statusCode) {
		super(msg)
		this.errorData = msg
		this.statusCode = statusCode
	}

}
