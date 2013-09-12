package httprevayler.src.basis.exceptions

import httprevayler.src.basis.StatusCodes;

class ResourceNotFoundException extends SimpleException {
	
	public ResourceNotFoundException(String uri) {
		super(sprintf('%s resource not found.', uri), StatusCodes.NOT_FOUND)
	}

}
