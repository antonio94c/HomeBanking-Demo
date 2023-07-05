# HomeBanking-Demo

This is a demo project to provide some functions about bank world using Fabrick API. Functions implemented:

* Get available balance
* Get list of transactions
* Wire transfer

## Build With

technologies used for development:

* Java 17
* Maven 3.8.1

## Installation and execution (for Linux system )

Below there are instructions on installing and setting up the demo. 

1. Get a Fabrick API Key

2. Clone the repo
   ```sh
   git clone https://github.com/antonio94c/HomeBanking-Demo
   ```
3. Enter in repository directory
    ```
    cd HomeBanking-Demo
    ```

4. Enter your API in `conf/application.properties`
   ```js
   apiKey = {API_KEY}
   ```
5. Build with Maven

   ```
   mvn clean compile assembly:single
   ```
8. To execute unit test:

    ``` 
    mvn test
    ```

7. To execute the demo run:

    ``` 
    java -jar target/HomeBanking-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```