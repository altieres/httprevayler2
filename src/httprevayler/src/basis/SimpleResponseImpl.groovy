package httprevayler.src.basis

import javax.servlet.http.HttpServletResponse

import com.fasterxml.jackson.databind.ObjectMapper;


class SimpleResponseImpl implements SimpleResponse {
	
	private ObjectMapper mapper = new ObjectMapper()
	HttpServletResponse response
	
	public SimpleResponseImpl(HttpServletResponse response) {
		this.response = response
	}
	
	@Override
	public void setHeader(String header, String value) {
		response.setHeader(header, value)
	}
	
	@Override
	public void writeResponse(String data) {
		response.getWriter().write(data)
	}
	
	@Override
	public void writeEncoded(data) {
		mapper.writeValue(writer, data)
	}
	
	@Override
	public void setStatus(int status) {
		response.setStatus(status)
	}
	
	@Override
	public PrintWriter getWriter() {
		response.writer
	}
	
}
