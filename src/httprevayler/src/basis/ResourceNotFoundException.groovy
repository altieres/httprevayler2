package httprevayler.src.basis

class ResourceNotFoundException extends SimpleException {
	
	public ResourceNotFoundException(String uri) {
		super(sprintf('%s resource not found.', uri), StatusCodes.NOT_FOUND)
	}

}
