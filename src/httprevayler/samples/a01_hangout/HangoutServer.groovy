package httprevayler.samples.a01_hangout

import httprevayler.samples.a01_hangout.domain.Hangout
import httprevayler.samples.a01_hangout.resources.routing.HangoutRouter
import httprevayler.src.basis.PrevalentServer


/*
 * Sample commands:
 * curl -XPOST -H "Accept:application/json" -H "Content-type:application/json" http://localhost:8484/users -d '{"name":"Altz"}'
 * curl -XPOST -H "Accept:application/json" -H "Content-type:application/json" http://localhost:8484/users -d '{"name":"Lutz"}'
 * curl -XPOST -H "Accept:application/json" -H "Content-type:application/json" http://localhost:8484/messages -d '{"from":"Altz","to":"Lutz","text":"Tudo bom?"}'
 * curl http://localhost:8484/messages
 */
class HangoutServer {
	
	public static void main(String[] args) {
		PrevalentServer server = new PrevalentServer(8484);
		server.startRunning(new Hangout(), new HangoutRouter())
	}

}

