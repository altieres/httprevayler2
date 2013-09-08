package httprevayler.src.basis

class ApplicationServletFactory {
	
	private static Object domain
	private static SimpleResource service
	
	static void configureDomain(Object domain) {
		this.domain = domain
	}
	
	static void configureService(SimpleResource service) {
		this.service = service
	}
	
	static ApplicationServlet create() {
		return new ApplicationServlet(domain, service)
	}
	
	private ApplicationServletFactory() { }

}
