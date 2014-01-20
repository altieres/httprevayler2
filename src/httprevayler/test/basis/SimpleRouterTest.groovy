package httprevayler.test.basis;

import static org.junit.Assert.*
import httprevayler.src.basis.SimpleRequest
import httprevayler.src.basis.SimpleResponse
import httprevayler.src.basis.SimpleRouter
import httprevayler.test.basis.helpers.MapableSimpleRequest

import org.junit.Test

class SimpleRouterTest {
	
	@Test
	void shouldCallAttachedResource() {
		def router = new SimpleRouterDummy()
		router.attach("/fooz", FooResource)
		assert router.run(new MapableSimpleRequest(uri:"/fooz")) == FooResource
	}
	
	@Test
	void shouldCallRightResourceByMissing() {
		def router = new SimpleRouterDummy()
		router.resourcesPackages << "httprevayler.test.inexistent"
		router.resourcesPackages << "httprevayler.test.basis"
		assert router.run(new MapableSimpleRequest(uri:"/foo")) == FooResource
	}
	
	@Test
	void shouldParseQueryStringParams() {
		def router = new SimpleRouterDummy()
		def request = new MapableSimpleRequest(uri:"/fooz/3/5")
		router.attach("/fooz/:id/:counter", FooResource)
		assert router.run(request) == FooResource
		assert request.namedParamsMap == ['id':'3', 'counter':'5']
	}

}

class SimpleRouterDummy extends SimpleRouter {
	def run(SimpleRequest request) {
		super.run(request, null)
		return resourceToCall()
	}
}

class FooResource {
	def run(SimpleRequest request, SimpleResponse response) { }
}
