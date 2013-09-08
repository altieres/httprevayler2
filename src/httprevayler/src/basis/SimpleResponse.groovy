package httprevayler.src.basis

interface SimpleResponse {

	public void setHeader(String header, String value)
	public void writeResponse(String data)
	public void setStatus(int status)
	
}
