# unit-testing-spring

All what you need is to run `SpringUnitTestingApplication` in your IDE.

```java

@SpringBootApplication
public class SpringUnitTestingApplication {
    //...
}
```

The project is already include all pre-data you need to test the application.

#### Database

There are 3 users in our database

| Id  | Firstname | Lastname | Birthday             | 
|:----|:----------|:---------|:---------------------|
| `1` | `Monica`  | `Geller` | `1994-10-11`         | 
| `2` | `Ross`    | `Geller` | `1990-11-15`         |      
| `3` | `Alex`    | `Fox`    | `2000-04-01`         |      

### 🌿 Start

Let's try to send the request to the endpoint.

`http://localhost:8080/api/users/{id}`

```
{
    "id": 1,
    "firstname": "Monica",
    "lastname": "Geller",
    "birthday": "1994-10-11",
    "age": 28
}
```

If the user doesn't exist the server return the following: 

```
{
    "statusCode": 400,
    "timestamp": "2023-03-06T15:51:06.413+00:00",
    "message": "The user with id: [100] does not exist",
    "request": "uri=/api/users/100"
}
```
### Tests ⚙️

The project is covered with tests (Unit and E2E).