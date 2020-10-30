# Webhook for chuck norris fun fact for integration with Google Actions.


A response example:
```json
{
    "prompt": {
        "firstSimple": {
            "speech": "Alright, listen to this one. Chuck Norris ain't no diamond in the rough. He's just fucking rough.  Do you want to hear another joke?",
            "text": "Alright, listen to this one. Chuck Norris ain't no diamond in the rough. He's just fucking rough.  Do you want to hear another joke?"
        }
    },
    "scene": {
        "name": "SceneName",
        "next": {
            "name": "next.intent"
        },
        "slotFillingStatus": "UNSPECIFIED",
        "slots": {}
    },
    "session": {
        "id": "example_session_id",
        "params": {
            "additionalProperties": {}
        },
        "typeOverrides": []
    }
}
```

### How to run


```sh
$ mvn clean compile quarkus:dev
```

And to perform a example request:
```sh
 curl -X POST -H 'Content-Type:application/json' -d @request-example.json http://localhost:8080/fact 
```

