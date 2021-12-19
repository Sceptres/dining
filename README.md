# Dining
My portfolio project of the Codecademy Spring framework course. The goal is to build an API that can be used to rate different 
restaurants' ability to handle different types of allergies. In this project peanut, egg and dairy allergies are only taken into account.


## Restaurants
All the http requests that can be made to the /restaurants endpoint. 
```shell
Get all restaurants:
curl -X GET http://localhost:8080/restaurants

Get a specific restaurant by its id:
curl -X GET http://localhost:8080/restaurants/id

Insert a restaurant into the database:
curl -X POST -H "Content-Type: application/json" -d '{"name":"restaurantName","city":"restaurantCity","state":"restaurantState","zipcode":"restaurantZipcode"}' http://localhost:8080/restaurants/new 
```

## Dining Reviews
All the http requests that can be made to the /reviews endpoint.
```shell
Get all dining reviews
curl -X GET http://localhost:8080/reviews

Get a specific restaurant by its id:
curl -X GET http://localhost:8080/reviews/id

Insert a dining review into the database
curl -X POST -H "Content-Type: application/json" -d '{"name":"username","restaurantId":"reviewRestaurantId","peanutScore":"reviewPeanutScore","eggScore":"reviewEggScore","dairyScore":"reviewDairyScore"}' http://localhost:8080/reviews/new
```

## Users
All the http requests that can be made to the /users endpoint.
```shell
Get all users
curl -X GET http://localhost:8080/users

Get a specific user by their username
curl -X GET http://localhost:8080/users/username

Insert a new user into the database
curl -X POST -H "Content-Type: application/json" -d '{"name":"username","city":"userCity","state":"userState","zipcode":"userZipcode","isPeanutAllergic":isUserAllergicToPeanuts,"isEggAllergic":isUserAllergicToEggs,"isDairyAllergic":isUserAllergicToDairy}' http://localhost:8080/users/new/signup
```