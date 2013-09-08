package httprevayler.src.basis

class SimpleCommand implements Serializable {

	private final SimpleRequest request
	private static SimpleResponse response = new SimpleResponseDummy()
	
	public SimpleCommand(SimpleRequest request, SimpleResponse response) {
		this.request = request
		this.response = response
	}
	
	protected Object process(Object application, Date date) {
		ApplicationServletFactory.create().service(request, response)
	}

}
