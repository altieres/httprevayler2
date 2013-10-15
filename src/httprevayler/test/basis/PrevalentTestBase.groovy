package httprevayler.test.basis

import org.junit.After;

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
		def prevalenceFolder = new File("Prevalence")
		for(File journal : prevalenceFolder.listFiles())
			journal.delete()
		prevalenceFolder.delete()
	}
	
}
