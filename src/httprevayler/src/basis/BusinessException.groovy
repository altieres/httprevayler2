package httprevayler.src.basis;

class BusinessException extends SimpleException {
	
	public BusinessException(String message) {
		super(message, StatusCodes.BAD_REQUEST)
	}

}
