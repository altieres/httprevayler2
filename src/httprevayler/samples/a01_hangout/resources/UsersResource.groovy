package httprevayler.samples.a01_hangout.resources;

import httprevayler.samples.a01_hangout.domain.User
import httprevayler.src.basis.SimpleResource

class UsersResource extends SimpleResource {
	public doPost() {
		domain.users << [(request.parameterMap.name) : new User(name:request.parameterMap.name)]
		['ok']
	}
}