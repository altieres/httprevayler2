package httprevayler.src.basis

import java.util.concurrent.atomic.AtomicLong


class SimpleCommand implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final SimpleRequest request
	
	private final long responseId
	private static AtomicLong nextResponseId = new AtomicLong();
	private static final Map<Long, SimpleResponse> responsesById = new HashMap<Long, SimpleResponse>()
	
	public SimpleCommand(SimpleRequest request, SimpleResponse response) {
		this.request = request
		responseId = nextResponseId.incrementAndGet()
		this.responsesById.put(responseId, response)
	}
	
	protected Object process(Object application, Date date) {
		request.parsedMap['now'] = date
		SimpleResponse response = responsesById.remove(responseId)
		ApplicationServletFactory.create().service(request, response == null ? new SimpleResponseDummy() : response)
	}

}
