package httprevayler.src.basis

import java.io.Serializable;

class SimpleCommand implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final SimpleRequest request
	private static SimpleResponse response = new SimpleResponseDummy()
	
	public SimpleCommand(SimpleRequest request, SimpleResponse response) {
		this.request = request
		this.response = response
	}
	
	protected Object process(Object application, Date date) {
		request.parsedMap['now'] = date
		ApplicationServletFactory.create().service(request, response)
	}

}
