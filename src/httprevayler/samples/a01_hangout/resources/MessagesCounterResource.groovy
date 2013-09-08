package httprevayler.samples.a01_hangout.resources;

import httprevayler.src.basis.SimpleResource;

class MessagesCounterResource extends SimpleResource {
	public doGet() {
		['count':domain.messageCount()]
	}
}
