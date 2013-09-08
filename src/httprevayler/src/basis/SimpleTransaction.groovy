package httprevayler.src.basis

import org.prevayler.SureTransactionWithQuery


class SimpleTransaction extends SimpleCommand implements SureTransactionWithQuery {
	
	public SimpleTransaction(SimpleRequest request, SimpleResponse response) {
		super(request, response);
	}
	
	@Override
	public Object executeAndQuery(Object application, Date date) {
		return process(application, date)
	}

}
