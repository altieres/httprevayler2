package httprevayler.src.basis

import com.fasterxml.jackson.databind.ObjectMapper

class SimpleResponseDummy implements SimpleResponse {

	private ObjectMapper mapper = new ObjectMapper()
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
	public void writeEncoded(data) {
		this.wroteData = mapper.writeValueAsString(data)
	}
	
	@Override
	public void setStatus(int status) {
		this.status = status
	}
	
	@Override
	public PrintWriter getWriter() {
		return null
	}
	
}
