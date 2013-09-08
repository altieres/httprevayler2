package httprevayler.test.basis

import httprevayler.src.basis.PrevalentServer

class PrevalentTestBase {
	
	PrevalentServer server = new PrevalentServer(8484)
	def called = 0

	protected void startServer(domain, resource) {
		server.startRunning(domain, resource)
	}
	
	protected void stopServer() {
		server.stop()
	}
	
	protected void clearServerData() {
		for(File journal : new File("Prevalence").listFiles())
			journal.delete()
	}
	
}
