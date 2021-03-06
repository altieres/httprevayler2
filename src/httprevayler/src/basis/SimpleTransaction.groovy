package httprevayler.src.basis

import org.prevayler.SureTransactionWithQuery


class SimpleTransaction extends SimpleCommand implements SureTransactionWithQuery {
	
	private static final long serialVersionUID = 1L;

	public SimpleTransaction(SimpleRequest request, SimpleResponse response) {
		super(request, response);
	}
	
	@Override
	public Object executeAndQuery(Object application, Date date) {
		return process(application, date)
	}

}
