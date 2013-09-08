package httprevayler.src.basis

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.AbstractHandler
import org.eclipse.jetty.server.handler.HandlerList
import org.eclipse.jetty.server.handler.ResourceHandler
import org.eclipse.jetty.server.nio.SelectChannelConnector

class PrevalentServer {
	private int port
	private Server server
	private PrevalentServlet prevalentServlet
	private HandlerList handlers = new HandlerList()
	
	public PrevalentServer(int port) {
		this.port = port
	}
	
	public addResourceFolder(String folder) {
		ResourceHandler resourceHandler = new ResourceHandler()
		resourceHandler.setDirectoriesListed(true)
		resourceHandler.setWelcomeFiles("index.html")
		resourceHandler.setResourceBase(folder)
		handlers.addHandler(resourceHandler)
	}
	
	public startRunning(Object domain, SimpleResource service) {
		server = new Server()
		prevalentServlet = new PrevalentServlet(domain, service)
		
		SelectChannelConnector connector = new SelectChannelConnector()
		connector.setPort(port)
		server.addConnector(connector)
		
		AbstractHandler handler = new AbstractHandler() { void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) {
			baseRequest.setHandled(true)
			prevalentServlet.service(request, response)
		}}
		
		handlers.addHandler(handler)
		server.setHandler(handlers)

		server.start()
	}
	
	public stop() {
		server.stop()
	}
}