package httprevayler.src.basis

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.ServerConnector
import org.eclipse.jetty.server.handler.HandlerList
import org.eclipse.jetty.server.handler.HandlerWrapper
import org.eclipse.jetty.server.handler.ResourceHandler
import org.eclipse.jetty.servlets.gzip.GzipHandler

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
		
		ServerConnector connector = new ServerConnector(server)
		connector.setPort(port)
		server.addConnector(connector)
		
		HandlerWrapper handler = new HandlerWrapper() { void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) {
			baseRequest.setHandled(true)
			prevalentServlet.service(request, response)
		}}
		
		handlers.addHandler(new GzipHandler().with {
			setHandler(handler)
			setMimeTypes("application/json")
			setServer(server)
			it
		})

		server.setHandler(handlers)
		server.start()
	}
	
	public stop() {
		server.stop()
	}
}