# Url Shortener Application

## Introduction

This projects shows how to generate unique hash for given url 

The code is implemented by using Kotlin, but it's easy for Java developers to understand as well.


## How to run

The project needs Redis to run. There's a `docker-compose` file contains all necessary configuration to run Redis flawlessly.

To use it. Just run:

```bash
$ docker-compose -f docker-compose.yml up -d
``` 

After that the Redis should be accessible via port `6379` on `localhost`

Then you can run the application like below:

```bash
$  gradle bootRun
```

After run this command application start 8080 default port. Available from [http://localhost:8080]()

To interact with the APIs, after running the project, just open [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html). You should see Swagger to interact with.