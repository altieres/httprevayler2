package httprevayler.src.basis

import httprevayler.src.basis.exceptions.ResourceNotFoundException;

class SimpleRouter extends SimpleResource {
	
	private Map routes = [:]
	
	List resourcesPackages = []

	def run(SimpleRequest request) {
		this.request = request
		def resourceToCall = resourceToCall()
		if (resourceToCall == null)
			throwNotFound()
		else
			resourceToCall.newInstance().run(request)
	}
	
	void attach(String uri, Class clazz) {
		routes << [(uri):clazz]
	}
	
	protected resourceToCall() {
		for (def route : routes) {
			def routeMatcher = (route.key =~ /\:(\w+)/)
			def routePattern = '^' + routeMatcher.replaceAll('(?<$1>\\\\w+)').replace('/', '\\/') + '\$'
			
			def matcher = (request.uri =~ routePattern)
			if (matcher.matches()) {
				request.namedParamsMap = matcher.pattern().namedGroups().collectEntries { key, value ->
					return [(key):matcher.group(key)]
				}
				return route.value
			}
			
			if (route.key == request.uri)
				return route.value
		}
		return resourceGuess()
	}
	
	private resourceGuess() {
		String urlFirstPart = request.uri.split("/")[1]
		resourcesPackages.findResult({ resourcesPackage ->
			try {
				Class.forName(resourcesPackage + "." + urlFirstPart.capitalize() + "Resource")
			} catch(e) { }
		})
	}
	
	private throwNotFound() {
		throw new ResourceNotFoundException(request.uri)
	}

}
