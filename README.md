# videoRental project
# Joana Socrates

Instructions: project was developed in IntelliJ IDEA. It was set as a Maven project configured to run in Tomcat 8.0.30 localhost port 8080. 
The Api's Endpoints are:

- GET http://localhost:8080/videorental/movies - responds with all movies in the catalogue

- GET http://localhost:8080/videorental/movies/movie/{movieId} - responds with selected movie by movieId, eg.: GET http://localhost:8080/videorental/movies/movie/001

- GET http://localhost:8080/videorental/customers - responds with all registered customers

- GET http://localhost:8080/videorental/customers/customer/{customerId} - responds with selected customer by customerId, eg.: GET http://localhost:8080/videorental/customers/customer/MYS

- POST http://localhost:8080/videorental/rental/rent/{customerId}?movieId={movieId}&daysRent={dayToRent} - rents a specific movie (by movieId) in the current day of the operation for the specific customer (by customerId). If operation is successful, the rented movie is inserted in the list of rented movies in the customer data, the movies is set as unavailable with respective return date in movie data and amount to be paid for the rented days is informed in the response and stored in movie data. If the movie is not available for rent a message informing so is repplied. eg.: POST http://localhost:8080/videorental/rental/rent/MYS?movieId=003&daysRent=2

- POST http://localhost:8080/videorental/rental/rent/{customerId} with JSON body parameter. - rents a list of movies (by movieId) for days specific for each movie request in the current day of the operation for the specific customer (by customerId). If operation is successful, the rented movie is inserted in the list of rented movies in the customer data, the movies is set as unavailable with respective return date in movie data and amount to be paid for the rented days is informed in the response and stored in movie data. response may contain data regarding the movies succesfully rent and/or movies with failed operation. eg.: POST http://localhost:8080/videorental/rental/rent/MYS
JSON example for body in the call: 
```
[
   {
    	"movieId": "001",
    	"numberOfDays": 2
   },
   {
    	"movieId": "002",
    	"numberOfDays": 2
   }
]
```

- POST http://localhost:8080/videorental/rental/return/{customerId}?movieId={movieId} - returns a specific movie (by movieId) in the current day of the operation for he specific customer (by customerId). If the operation is successful the returned movie is taken out from the list of rented movies in the customer data, the movie is set as available in the movie data and the amount to be paid for extra days beyond already rented days paid is informed  in the reply message. If the movie was not originally rented by the customer a messaging informing so is repplied. eg.: http://localhost:8080/videorental/rental/return/MYS?movieId=003

- POST http://localhost:8080/videorental/rental/return/{customerId} with JSON body parameter - returns a list of movies (by movieId) in the current day of the operation for he specific customer (by customerId). For each movie, if the operation is successful the returned movie is taken out from the list of rented movies in the customer data, the movie is set as available in the movie data and the amount to be paid for extra days beyond already rented days paid is informed  in the reply message. If the movie was not originally rented by the customer this information will be part of the message response. eg.: http://localhost:8080/videorental/rental/return/MYS
JSON example for body in the call: 
```
[
   {
    	"movieId": "001"
   },
   {
    	"movieId": "002"
   }
]
```

There are two different calls for renting and returning movies, because I have developed the call for just one movie and later I realised there was a requirement for renting more than one at once. So I fastly developed the multiple movie call later. And decided to keep the other one as well (it is easier to test as it does not require a call body)

There is no rental history recorded. Once a movie is returned there is not history registered of the rent of the specific movie by the customer. 

The Api's system design assumes the customer pays for the rent days when they rent a movie, and pays for the extra days when the movie is returned. There is not record of amount to be paid if the customer does not pay when they should.

In order to simulate a DB system the customer and movie data is lazy loaded from a JSON file each time API is deployed on server. If the API is restarted the recorded info is lost and movie and customer data is reloaded with reseted data. All CRUD opeartions are performed only in memory.

Currency was set as int, for simplicity, but in a real environment should relly on BigDecimal.
