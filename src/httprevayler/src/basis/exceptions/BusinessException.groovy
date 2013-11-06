package httprevayler.src.basis.exceptions;

import httprevayler.src.basis.StatusCodes

class BusinessException extends SimpleException {
	
	public BusinessException(String message) {
		super(message, StatusCodes.BAD_REQUEST)
	}
	
	public BusinessException(Map errors) {
		super(errors.toString(), StatusCodes.BAD_REQUEST)
		this.errorData = errors
	}

}
