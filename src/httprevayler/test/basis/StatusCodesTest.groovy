package httprevayler.test.basis;

import static org.junit.Assert.*
import httprevayler.src.basis.ApplicationServlet
import httprevayler.src.basis.SimpleRequest
import httprevayler.src.basis.SimpleResource
import httprevayler.src.basis.SimpleResponseDummy
import httprevayler.src.basis.SimpleRouter
import httprevayler.src.basis.StatusCodes

import org.junit.Test


class StatusCodesTest {
	
	@Test
	void shouldRespond200OKByDefault() {
		def application = new ApplicationServlet(new App(), new MyRouter())
		def response = new SimpleResponseDummy()
		application.service(new SimpleRequest(method:'GET', uri:'/gettable'), response)
		assertEquals('"getted!"', response.wroteData)
		assertEquals(StatusCodes.OK, response.status)
	}
	
	@Test
	void shouldRespond201CreatedOnPut() {
		def application = new ApplicationServlet(new App(), new MyRouter())
		def response = new SimpleResponseDummy()
		application.service(new SimpleRequest(method:'PUT', uri:'/puttable'), response)
		assertEquals('"putted!"', response.wroteData)
		assertEquals(StatusCodes.CREATED, response.status)
	}
	
	@Test
	void shouldRespond404NotFoundOnNoRoute() {
		def application = new ApplicationServlet(new App(), new MyRouter())
		def response = new SimpleResponseDummy()
		application.service(new SimpleRequest(method:'GET', uri:'/innexistent'), response)
		assertEquals('"/innexistent resource not found."', response.wroteData)
		assertEquals(StatusCodes.NOT_FOUND, response.status)
	}
	
	@Test
	void shouldRespond405MethodNotAllowedOnNoMethod() {
		def application = new ApplicationServlet(new App(), new MyRouter())
		def response = new SimpleResponseDummy()
		application.service(new SimpleRequest(method:'POST', uri:'/gettable'), response)
		assertEquals('"POST method not allowed."', response.wroteData)
		assertEquals(StatusCodes.METHOD_NOT_ALLOWED, response.status)
	}

}

class App {
	
}

class MyRouter extends SimpleRouter {
	def MyRouter() {
		attach('/gettable', GettableResource.class)
		attach('/puttable', PuttableResource.class)
	}
}

class GettableResource extends SimpleResource {
	def doGet() {
		'getted!'
	}
}

class PuttableResource extends SimpleResource {
	def doPut() {
		'putted!'
	}
}