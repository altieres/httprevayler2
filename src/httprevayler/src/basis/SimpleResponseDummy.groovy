package httprevayler.src.basis

import java.io.PrintWriter;

class SimpleResponseDummy implements SimpleResponse {

	def wroteData = null
	def settedHeaders = [:]
	def status = null;
	
	@Override
	public void setHeader(String header, String value) {
		this.settedHeaders << [(header) : value]
	}

	@Override
	public void writeResponse(String data) {
		this.wroteData = data;
	}

	@Override
	public void setStatus(int status) {
		this.status = status
	}

	@Override
	public PrintWriter getWriter() {
		return null;
	}

}
