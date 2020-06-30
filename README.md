# Chatbot playground in Java

Web chatbot concept proof implemented with Java Stack

[See](https://dzone.com/articles/implementing-a-web-chat-with-ai-in-java)

The project is a standard Maven project, so you can import it to your IDE of choice. 
You'll need to have Java 11+ and Node.js 10+ installed.

To run from the command line, use `./mvnw spring-boot:run` and 
open [http://localhost:8080](http://localhost:8080) in your browser.

## Which technologies are being used ?

- Vaddin v14.2.1
- SpringBoot v2.2.0.RELEASE
- JDK 11
- Docker

## Project structure

- `MainView.java` in `src/main/java` contains the navigation setup. It uses [App Layout](https://vaadin.com/components/vaadin-app-layout).
- `views` package in `src/main/java` contains the server-side Java views of your application.
- `views` folder in `frontend/src/` contains the client-side JavaScript views of your application.

## What next?

- Fix Chatbot answers delay
- Add WebSocket chatroom](https://dzone.com/articles/a-microservice-websocket-chat)

[vaadin.com](https://vaadin.com) has lots of material to help you get you started:

- Follow the tutorials in [vaadin.com/tutorials](https://vaadin.com/tutorials). Especially [vaadin.com/tutorials/getting-started-with-flow](https://vaadin.com/tutorials/getting-started-with-flow) is good for getting a grasp of the basic Vaadin concepts.
- Read the documentation in [vaadin.com/docs](https://vaadin.com/docs).
- For a bigger Vaadin application example, check out the Full Stack App starter from [vaadin.com/start](https://vaadin.com/start).

## Deploying using Docker

To build the Dockerized version of the project, run

```
docker build . -t myapp:latest
```

Once the Docker image is correctly built, you can test it locally using

```
docker run -p 8080:8080 myapp:latest
```
