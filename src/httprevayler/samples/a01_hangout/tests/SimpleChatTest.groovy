package httprevayler.samples.a01_hangout.tests;

import static org.junit.Assert.*
import httprevayler.samples.a01_hangout.domain.Hangout
import httprevayler.samples.a01_hangout.domain.Message
import httprevayler.samples.a01_hangout.domain.User
import httprevayler.samples.a01_hangout.resources.routing.HangoutRouter
import httprevayler.test.basis.PrevalentTestBase
import httprevayler.test.basis.RestClient

import org.junit.Test


class SimpleChatTest extends PrevalentTestBase {
	
	def altz = new User(name:"Altz");
	def lutz = new User(name:"Lutz");
	
	def msgText1 = 'hello! ;-)'
	def msgText2 = 'how are you?'
	
	@Test
	void thereAreUsers() {
		assert altz instanceof User
		assert lutz instanceof User
	}
	
	@Test
	void hangoutsHaveMessages() {
		def hangout = createHangout()
		assert hangout.messageCount() == 2
		assert hangout.messages[0].text == msgText1
		assert hangout.messages[1].text == msgText2
	}
	
	private Hangout createHangout() {
		def hangout = new Hangout()
		hangout.postMessage(new Message(from:altz, to:lutz, text: msgText1))
		hangout.postMessage(new Message(from:lutz, to:altz, text: msgText2))
		return hangout
	}
	
	@Test
	void theRealApp() {
		clearServerData()
		startServer(new Hangout(), new HangoutRouter())
		def responseData = null
		
		new RestClient("http://localhost:8484/", "messages_counter").get { response ->
			responseData = response.data.count
		}
		assert responseData == 0
		
		new RestClient("http://localhost:8484/", "users").post('{"name":"Altz"}') { }
		new RestClient("http://localhost:8484/", "users").post('{"name":"Lutz"}') { }
		
		new RestClient("http://localhost:8484/", "messages").post('{"from":"Altz","to":"Lutz","text":"<3"}') { }
		new RestClient("http://localhost:8484/", "messages").post('{"from":"Lutz","to":"Altz","text":"S3"}') { }
		
		new RestClient("http://localhost:8484/", "messages_counter").get { response ->
			responseData = response.data.count
		}
		assert responseData == 2
		
		stopServer()
		
		println 'server stoped!!!'
		
		startServer(new Hangout(), new HangoutRouter())
		
		new RestClient("http://localhost:8484/", "messages").get { response ->
			responseData = response.data
		}
		assert responseData[0].to.name == 'Lutz'
		assert responseData[0].from.name == 'Altz'
		assert responseData[0].text == '<3'
		assert responseData[1].to.name == 'Altz'
		assert responseData[1].from.name == 'Lutz'
		assert responseData[1].text == 'S3'
		
		stopServer()
		clearServerData()
	}
	
}
