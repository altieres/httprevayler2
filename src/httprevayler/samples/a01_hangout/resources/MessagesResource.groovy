package httprevayler.samples.a01_hangout.resources;

import httprevayler.samples.a01_hangout.domain.Message
import httprevayler.src.basis.SimpleResource

class MessagesResource extends SimpleResource {
	public doPost() {
		def userFrom = domain.users[request.parameterMap.from]
		def userTo = domain.users[request.parameterMap.to]
		domain.postMessage(new Message(from:userFrom, to:userTo, text: request.parameterMap.text))
		['ok']
	}
	public doGet() {
		return domain.messages
	}
}