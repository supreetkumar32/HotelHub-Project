HotelHub Project

SETUP THE HOTELMANAGER APIs TO MANAGE HOTEL,ROOM,INVENTORY

//hotels
1. POST http://localhost:8080/api/v1/admin/hotels
{
"name":"Hotel Lotus",
"city":"Delhi",
"contactInfo":{
"address": "Central Delhi",
"email":"hello@lotushotels.com",
"phoneNumber":"8643565456",
"location":"74.2381,28.43124"
},
"amenities":["AC","Lake view","Pool Area"],
"photos":["http://via.placeholder.com/50"]
}
2. GET http://localhost:8080/api/v1/admin/hotels/1
3. PUT http://localhost:8080/api/v1/admin/hotels/1
   {
   "name":"Hotel Lotus",
   "city":"Delhi",
   "contactInfo":{
   "address": "Central Delhi",
   "email":"hello@lotushotels.com",
   "phoneNumber":"8643565456",
   "location":"74.2381,28.43124"
   },
   "amenities":["Lake view","Pool Area"],
   "photos":["http://via.placeholder.com/50"],
   "active":false
   }
4. DELETE http://localhost:8080/api/v1/admin/hotels/1
5. PATCH http://localhost:8080/api/v1/admin/hotels/2/activate

//ROOMS API
1. POST http://localhost:8080/api/v1/admin/hotels/2/rooms
   {
   "type":"Single Room",
   "basePrice":40.00,
   "capacity":2,
   "totalCount":40,
   "amenities":["WiFi", "Air Conditioning", "Mini Bar"],
   "photos": ["http://via.placeholder.com/50","http://via.placeholder.com/150"]
}

2. Get all rooms in hotel
    GET http://localhost:8080/api/v1/admin/hotels/2/rooms
3. Get room by id 
    GET http://localhost:8080/api/v1/admin/hotels/2/rooms/1

//search 
Criteris for inventory:
startDate<= date <=endDate
city 
availability :(totalCount-bookedCount)>= roomsCount
closed=false

Group the response by room and get the response by unique hotels

API for search
GET http://localhost:8080/api/v1/hotels/search
   {
   "city":"Delhi",
   "startDate":"2026-01-09",
   "endDate":"2026-01-10",
   "roomsCount":2,
   "page":0,
   "size":4
   }

API FOR hotel details
GET http://localhost:8080/api/v1/hotels/3/info

now building the booking apis(initiate booking api, add guests, initiate payments)

//initialize booking
added the user manually  in the database in app_user table(1,Supreet@gmail.com,Supreet,Supreet) i didnt created the spring security

POST http://localhost:8080/api/v1/bookings/init
   {
   "hotelId":3,
   "roomId":3,
   "checkInDate":"2026-01-10",
   "checkOutDate":"2026-01-13",
   "roomsCount":2
   }
//after running the above post request inventory should be filled with id 3 for thr date 10 to 13

//ADD GUESTS API
after the initialize booking, we got the id ,then we put the id in  POST as follows:
POST : http://localhost:8080/api/v1/bookings/4/addGuests

   [
      {
      "name":"Ram",
      "gender":"MALE",
      "age":20
      },
      {
      "name":"Shyam",
      "gender":"MALE",
      "age":25
      }
   ]


//decorator design pattern has ctrreated for thr pricing strategies
and also i have schedule the job after 1 hour

also we have changed the search api
GET http://localhost:8080/api/v1/hotels/search


SPRING SECURITY
1. JWTService is almost same.

//REST API
SIGNUP
POST http://localhost:8080/api/v1/auth/signup
{
"name":"Supreet",
"email":"Supreet@gmail.com",
"password":"password"
}

//LOGIN
POST http://localhost:8080/api/v1/auth/login
{
"email":"Supreet@gmail.com",
"password":"password"
}
//we get the access token in the response of login..check the decode in jwt.io

now handling the exceptions
gave the owner id as same id in app_user in hotel manually

NOW initialize the booling
POST http://localhost:8080/api/v1/bookings/init

{
"hotelId":1,
"roomId":1,
"checkInDate":"2026-01-21",
"checkOutDate":"2026-01-23",
"roomsCount":2
}

//REFRESH TOKEN (make sure there is refresh token something in localhost in cookies)
POST http://localhost:8080/api/v1/auth/refresh

