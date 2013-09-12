package httprevayler.samples.a02_todo

import httprevayler.src.basis.PrevalentServer
import httprevayler.src.basis.SimpleResource
import httprevayler.src.basis.SimpleRouter
import httprevayler.src.basis.exceptions.BusinessException;

class TodoServer {
	static void main(String[] args) {
		PrevalentServer server = new PrevalentServer(8484);
		server.addResourceFolder('./src/httprevayler/samples/a02_todo/www/')
		server.startRunning(new TodoApp(), new TodoRouter())
	}
}

class TodoApp {
	def lastId = 1
	def items = []
	
	public add(String description) {
		def newItem = new Item(id:nextId(), description:description)
		items << newItem
		newItem
	}
	
	public remove(Integer id) {
		def item = items.find { item -> item.id == id }
		items.remove(item)
	}
	
	private nextId() {
		lastId++
	}
}

class Item {
	def id
	def description
	
	public setDescription(String description) {
		if (description.isEmpty()) throw new BusinessException("Description shouldn't be empty.")
		this.description = description
	}
}

class TodoRouter extends SimpleRouter {
	TodoRouter() {
		attach("/items", ItemsResource.class)
		attach("/items/:id", ItemsResource.class)
		attach("/items_sort", ItemsSortResource.class)
	}
}

class ItemsResource extends SimpleResource {
	def doGet() {
		domain.items
	}
	
	def doPost() {
		domain.add(request.parameterMap.description)
	}
	
	def doDelete() {
		domain.remove(request.namedParamsMap.id.toInteger())
		'ok'
	}
}

class ItemsSortResource extends SimpleResource {
	def doPost() {
		def item = domain.items.remove(request.parameterMap.from.toInteger())
		domain.items.add(request.parameterMap.to.toInteger(), item)
		'ok'
	}	
}