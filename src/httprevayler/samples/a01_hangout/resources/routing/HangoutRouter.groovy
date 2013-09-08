package httprevayler.samples.a01_hangout.resources.routing;

import httprevayler.samples.a01_hangout.resources.MessagesCounterResource
import httprevayler.samples.a01_hangout.resources.MessagesResource
import httprevayler.samples.a01_hangout.resources.UsersResource
import httprevayler.src.basis.SimpleRouter

class HangoutRouter extends SimpleRouter {
	public HangoutRouter() {
		attach("/users", UsersResource.class)
		attach("/messages", MessagesResource.class)
		attach("/messages_counter", MessagesCounterResource.class)
	}
}