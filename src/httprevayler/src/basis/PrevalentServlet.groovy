package httprevayler.src.basis

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.prevayler.Prevayler
import org.prevayler.PrevaylerFactory

class PrevalentServlet {
	
	private Object domain
	private SimpleResource service
	private Prevayler prevayler
	
	public PrevalentServlet(Object domain, SimpleResource service) {
		this.domain = domain
		this.service = service
		
		ApplicationServletFactory.configureDomain(domain)
		ApplicationServletFactory.configureService(service)
		
		final PrevaylerFactory factory = new PrevaylerFactory()
		factory.configurePrevalentSystem(domain)
		factory.configureTransactionFiltering(false)
		prevayler = factory.create()
		ApplicationServletFactory.configureDomain(prevayler.prevalentSystem())
	}
	
	public service(HttpServletRequest request, HttpServletResponse response) {
		request.setCharacterEncoding("UTF-8")
		response.setCharacterEncoding("UTF-8")
		service(new SimpleRequest(request), new SimpleResponseImpl(response))
	}
	
	public service(SimpleRequest request, SimpleResponse response) {
		if (request.getMethod().equals("SNAP")) {
			try {
				println 'taking...'
				prevayler.takeSnapshot()
			} catch(ex) {
				println 'ex...'
				ex.printStackTrace()
			}
		} else {
			if (request.getMethod().equals("GET"))
				prevayler.execute(new SimpleQuery(request, response))
			else
				prevayler.execute(new SimpleTransaction(request, response))
		}
	}

}
