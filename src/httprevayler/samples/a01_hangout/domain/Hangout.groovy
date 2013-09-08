package httprevayler.samples.a01_hangout.domain;

class Hangout {
	
	def users = [:]
	def messages = []
	
	public int messageCount() {
		return messages.size()
	}
	
	public postMessage(message) {
		messages << message
	}
	
}