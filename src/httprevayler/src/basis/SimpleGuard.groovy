package httprevayler.src.basis;

import httprevayler.src.basis.exceptions.UnauthorizedException

public abstract class SimpleGuard extends SimpleResource {
	
	SimpleResource resource
	
	def run(SimpleRequest request) {
		this.request = request
		if (!grant()) throw new UnauthorizedException()
		resource.run(request)
	}
	
	abstract boolean grant()

}
