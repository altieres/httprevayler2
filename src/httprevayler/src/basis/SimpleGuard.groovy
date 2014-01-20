package httprevayler.src.basis;

import httprevayler.src.basis.exceptions.UnauthorizedException

public abstract class SimpleGuard extends SimpleResource {
	
	SimpleResource resource
	
	def run(SimpleRequest request, SimpleResponse response) {
		this.request = request
		this.response = response
		if (!grant()) throw new UnauthorizedException()
		resource.run(request, response)
	}
	
	abstract boolean grant()

}
