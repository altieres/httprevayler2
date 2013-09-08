package httprevayler.src.basis

import javax.servlet.http.HttpServletResponse


class SimpleResponseImpl implements SimpleResponse {

	HttpServletResponse response

	public SimpleResponseImpl(HttpServletResponse response) {
		this.response = response
	}

	public void setHeader(String header, String value) {
		response.setHeader(header, value)
	}

	public void writeResponse(String data) {
		response.getWriter().write(data)
	}
	
	public void setStatus(int status) {
		response.setStatus(status)
	}
}
