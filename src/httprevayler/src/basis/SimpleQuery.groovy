package httprevayler.src.basis

import java.util.Date;

import org.prevayler.Query

class SimpleQuery extends SimpleCommand implements Query {

	public SimpleQuery(SimpleRequest request, SimpleResponse response) {
		super(request, response);
	}
	
	@Override
	public Object query(Object application, Date date) {
		return process(application, date)
	}

}
