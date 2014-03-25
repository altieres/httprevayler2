package httprevayler.src.basis

import java.io.PrintWriter;

interface SimpleResponse {

	public void setHeader(String header, String value)
	public void writeResponse(String data)
	public void writeEncoded(responseData)
	public void setStatus(int status)
	public PrintWriter getWriter()
	
}
