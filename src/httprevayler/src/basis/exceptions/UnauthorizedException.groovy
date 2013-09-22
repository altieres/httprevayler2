package httprevayler.src.basis.exceptions;

import httprevayler.src.basis.StatusCodes;

class UnauthorizedException extends SimpleException {
	
	public UnauthorizedException() {
		super(sprintf('Unauthorized.'), StatusCodes.UNAUTHORIZED)
	}

}
