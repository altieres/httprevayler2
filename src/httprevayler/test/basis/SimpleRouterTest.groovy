package httprevayler.test.basis;

import static org.junit.Assert.*
import httprevayler.src.basis.SimpleRequest
import httprevayler.src.basis.SimpleRouter
import httprevayler.test.basis.helpers.MapableSimpleRequest

import org.junit.Test

class SimpleRouterTest {
	
	@Test
	void shouldCallAttachedResource() {
		def router = new SimpleRouterDummy()
		router.attach("/fooz", FooResource.class)
		assert router.run(new MapableSimpleRequest(uri:"/fooz")) == FooResource.class
	}
	
	@Test
	void shouldCallRightResourceByMissing() {
		def router = new SimpleRouterDummy()
		router.resourcesPackages << "httprevayler.test.inexistent"
		router.resourcesPackages << "httprevayler.test.basis"
		assert router.run(new MapableSimpleRequest(uri:"/foo")) == FooResource.class
	}
	
	@Test
	void shouldParseQueryStringParams() {
		def router = new SimpleRouterDummy()
		def request = new MapableSimpleRequest(uri:"/fooz/3/5")
		router.attach("/fooz/:id/:counter", FooResource.class)
		assert router.run(request) == FooResource.class
		assert request.namedParamsMap == ['id':'3', 'counter':'5']
	}

}

class SimpleRouterDummy extends SimpleRouter {
	def run(SimpleRequest request) {
		super.run(request)
		return resourceToCall()
	}
}

class FooResource {
	def run(SimpleRequest request) { }
}
