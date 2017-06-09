# API.AI webhook chuck norris fun fact for integration with Google Home.

It just returns a simple JSON payload respecting the API.AI requisites.


A response example:
```java
{
  "speech" : "The Dynamic Duo was originally called Chuck Norris and Batman. When Chuck Norris got bored and retired, Batman got promoted and added Robin to the team.",
  "displayText" : "The Dynamic Duo was originally called Chuck Norris and Batman. When Chuck Norris got bored and retired, Batman got promoted and added Robin to the team.",
  "data" : null,
  "contextOut" : null,
  "source" : "apiai-chuck-norris-fact-sample"
}
```

### How to run


```sh
$ mvn clean package exec:exec
```
Or build it with `mvn package`and just execute it:
```sh
java -jar target/chuck-norris-fact.jar
```