# Description

Httprevayler is a groovy implementation of a transparent prevalent rest server.

A Jetty server is embeded to do the Http job. The prevalence is provided by Prevayler. The prevalent transparency is achieved journaling Http requests and snapshoting the domain models tree.

# The basics

This is the simplest server (listening at localhost on port 8484) you can create:

```
class MyServer {
	static void main(String[] args) {
		PrevalentServer server = new PrevalentServer(8484);
		server.startRunning(new Object(), new MyResource())
	}
}
```

```
class MyResource extends SimpleResource {
	def doGet() {
		'hello!'
	}
}
```

Run on a command line to see the result:

```
curl localhost:8484
```

# Doing something

The startRunning method receives two parameters: the root of domain models tree and a SimpleResource.

```
class MessagesServer {
	static void main(String[] args) {
		PrevalentServer server = new PrevalentServer(8484);
		server.startRunning(new MessagesApp(), new MessagesResource())
	}
}
```

```
class MessagesApp {
	def messages = []
}
```

```
class MessagesResource extends SimpleResource {
	def doGet() {
		domain.messages
	}
	
	def doPost() {
		domain.messages << request.parameterMap['text']
	}
}
```

Try this:

```
curl -XPOST -H "Content-type:application/json" http://localhost:8484 -d '{"text":"Some message"}'
curl -XPOST -H "Content-type:application/json" http://localhost:8484 -d '{"text":"Another message"}'
curl localhost:8484
-- stop the server and start again --
curl localhost:8484
```

It just works, like a magick ;-)

# Routing

If more than one Resource is needed, it is necessary to use a router. As a SimpleRouter is a SimpleResource, just use the Router on startRunning call.

```
class MyRouter extends SimpleRouter {
	MyRouter() {
		attach("/messages", MessagesResource.class)
		attach("/messages/:id", MessagesResource.class)
		attach("/tasks/:year/:month", DreSemestralResource.class)
	}
}
```

...


