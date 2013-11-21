package httprevayler.src.basis


class SimpleCommand implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final SimpleRequest request
	private static final ThreadLocal<SimpleResponse> response = new ThreadLocal<SimpleResponse>()
	
	public SimpleCommand(SimpleRequest request, SimpleResponse response) {
		this.request = request
		this.response.set(response)
	}
	
	protected Object process(Object application, Date date) {
		request.parsedMap['now'] = date
		ApplicationServletFactory.create().service(request, response.get() == null ? new SimpleResponseDummy() : response.get())
	}

}
