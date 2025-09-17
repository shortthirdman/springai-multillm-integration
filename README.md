# springai-multillm-integration

Spring AI Multi-LLM Integration - Gemini, OpenAI, Ollama

---

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.5/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.5/maven-plugin/build-image.html)
* [GraalVM Native Image Support](https://docs.spring.io/spring-boot/3.5.5/reference/packaging/native-image/introducing-graalvm-native-images.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.5.5/reference/using/devtools.html)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/3.5.5/specification/configuration-metadata/annotation-processor.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.5/reference/web/servlet.html)
* [Ollama](https://docs.spring.io/spring-ai/reference/api/chat/ollama-chat.html)
* [OpenAI](https://docs.spring.io/spring-ai/reference/api/chat/openai-chat.html)

---

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

---

### Additional Links
These additional references should also help you:

* [Configure AOT settings in Build Plugin](https://docs.spring.io/spring-boot/3.5.5/how-to/aot.html)

---

## GraalVM Native Support

This project has been configured to let you generate either a lightweight container or a native executable.
It is also possible to run your tests in a native image.

---

### Lightweight Container with Cloud Native Buildpacks
If you're already familiar with Spring Boot container images support, this is the easiest way to get started.
Docker should be installed and configured on your machine prior to creating the image.

Make sure to set your environment variables:

```
export OPENAI_API_KEY="your-openai-api-key"
export GEMINI_API_KEY="your-gemini-api-key"
```

To create the image, run the following goal:

```
$ ./mvnw spring-boot:build-image -Pnative
```

Then, you can run the app like any other container:

```
$ docker run --rm -p 8080:8080 multillm-integration:0.0.1-SNAPSHOT
```

---

### Executable with Native Build Tools
Use this option if you want to explore more options such as running your tests in a native image.
The GraalVM `native-image` compiler should be installed and configured on your machine.

NOTE: GraalVM 22.3+ is required.

To create the executable, run the following goal:

```
$ ./mvnw native:compile -Pnative
```

Then, you can run the app as follows:
```
$ target/multillm-integration
```

You can also run your existing tests suite in a native image.
This is an efficient way to validate the compatibility of your application.

To run your existing tests in a native image, run the following goal:

```
$ ./mvnw test -PnativeTest
```

---

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

---

### References

- [Integrate Multiple LLMs with Spring AI: OpenAI, Ollama and Gemini](https://medium.com/javarevisited/integrate-multiple-llms-with-spring-ai-openai-ollama-and-gemini-40d382136ce1)

---

### Testing OpenAI

```
http GET localhost:8100/chat \
  message=="What model are you? Please provide your name, version, and key capabilities." \
  llm==openai
```

**_Expected Sample Response_**

```json
{
    "llm": "openai",
    "originalMessage": "What model are you? Please provide your name, version, and key capabilities.",
    "response": "I am ChatGPT, based on the GPT-4 architecture developed by OpenAI. My version includes improvements in understanding and generating human-like text, enabling me to assist with a wide range of tasks such as answering questions, providing explanations, composing creative writing, and more. I can understand context, handle complex prompts, and generate coherent and relevant responses across various topics.",
    "timestamp": 1753268746222
}

```

---

### Testing Ollama

```
http GET localhost:8100/chat \
  message=="What model are you? Please tell me your name, version, and what you're good at." \
  llm==ollama
```

**_Expected Sample Response_**

```json
{
    "llm": "ollama",
    "originalMessage": "What model are you? Please tell me your name, version, and what you're good at.",
    "response": " I am a model of the Chat Model developed by Mistral AI. My primary function is to assist with various tasks by providing information, answering questions, and engaging in conversation. I strive to provide precise, helpful, and courteous responses.\n\nWhile I don't have a personal name, you can think of me as your digital assistant designed to make your interactions more enjoyable and productive. My capabilities include but are not limited to: answering questions, providing explanations, discussing a wide range of topics, assisting with scheduling and organization, offering recommendations, and much more.\n\nIn terms of my version, I am part of the latest generation of models, continually learning and improving from the data it encounters during interactions like this one.",
    "timestamp": 1753268772790
}
```

---

### Testing Gemini

```
http GET localhost:8100/chat \
  message=="Please identify yourself. What model are you, what version, and what are your strengths?" \
  llm==gemini
```

**_Expected Sample Response_**

```json
{
    "llm": "gemini",
    "originalMessage": "Please identify yourself. What model are you, what version, and what are your strengths?",
    "response": "I am a large language model, trained by Google.\n\n**Model & Version:**\nUnlike traditional software with specific version numbers, large language models like me are continuously updated and refined. There isn't a single, publicly accessible \"version number\" in the way you might think of software like....",
    "timestamp": 1753268800297
}

```