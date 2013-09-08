package httprevayler.src.basis;

class MethodNotAllowedException extends SimpleException {
	
	public MethodNotAllowedException(String method) {
		super(sprintf('%s method not allowed.', method), StatusCodes.METHOD_NOT_ALLOWED)
	}

}
