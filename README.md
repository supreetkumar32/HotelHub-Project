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

//Payment system (stripe)
https://docs.stripe.com/payments/checkout/how-checkout-works
i have created the account in stripe.
take the secret key from https://dashboard.stripe.com/acct_1Snw5iKIowOaoyGO/test/apikeys

after implementing the payment code, now check it:
Step 1: login
POST http://localhost:8080/api/v1/auth/login
{
"email":"Supreet@gmail.com",
"password":"password"
}
copy the access token and then go to the init booking, in authorisation paste the bearer token

Step 2: init booking
POST http://localhost:8080/api/v1/bookings/init
{
"hotelId":1,
"roomId":1,
"checkInDate":"2026-01-21",
"checkOutDate":"2026-01-23",
"roomsCount":2
}

in the data we get the booking id.in the above, we get the id as 2
{
"timeStamp": "2026-01-10T17:54:55.5019788",
"data": {
"id": 2,
"roomsCount": 2,
"checkInDate": "2026-01-21",
"checkOutDate": "2026-01-23",
"createdAt": "2026-01-10T17:54:55.024323",
"updatedAt": "2026-01-10T17:54:55.024323",
"bookingStatus": "RESERVED",
"guests": null
},
"error": null
}

STEP 3: ADD GUESTS
dont forget to add the bearer token
POST http://localhost:8080/api/v1/bookings/2/addGuests (put the id 2 in the url of add guests)
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

output:
{
"timeStamp": "2026-01-10T17:59:29.8606911",
"data": {
"id": 2,
"roomsCount": 2,
"checkInDate": "2026-01-21",
"checkOutDate": "2026-01-23",
"createdAt": "2026-01-10T17:54:55.024323",
"updatedAt": "2026-01-10T17:54:55.024323",
"bookingStatus": "GUESTS_ADDED",
"guests": [
{
"id": 2,
"user": {
"id": 1,
"email": "Supreet@gmail.com",
"password": "$2a$10$a/gpBRQcqSaRNBRo7p.2PusteiUXonkry7pQySiF//gP72Tohc4a2",
"name": "Supreet",
"roles": [
"GUEST"
],
"authorities": [
{
"authority": "ROLE_GUEST"
}
],
"username": "Supreet@gmail.com",
"enabled": true,
"credentialsNonExpired": true,
"accountNonExpired": true,
"accountNonLocked": true
},
"name": "Shyam",
"gender": "MALE",
"age": 25
},
{
"id": 1,
"user": {
"id": 1,
"email": "Supreet@gmail.com",
"password": "$2a$10$a/gpBRQcqSaRNBRo7p.2PusteiUXonkry7pQySiF//gP72Tohc4a2",
"name": "Supreet",
"roles": [
"GUEST"
],
"authorities": [
{
"authority": "ROLE_GUEST"
}
],
"username": "Supreet@gmail.com",
"enabled": true,
"credentialsNonExpired": true,
"accountNonExpired": true,
"accountNonLocked": true
},
"name": "Ram",
"gender": "MALE",
"age": 20
}
]
},
"error": null
}

STEP 4:INIT PAYMENT
POST http://localhost:8080/api/v1/bookings/2/payments (put the id 2 in the url of init payment)

in the output, we get the sessionUrl..we can copy paste in google to see the interface
interface will open, fill the card information 4242 4242 4242  and other details
and click on pay button
we dont have frontend url so it will redirect to backend url
but we can see our transaction in https://dashboard.stripe.com/test/payments

installed the stripe cli
open the windows powershell

step 1:stripe --version
step 2:stripe login
        it will generate a link...go to that link
        click on allow access

we have to run the cli and keep  the cli running in order to listening all teh webhook and send the webhook 
event to the server.
to do this: run stripe listen --forward-to localhost:8080/api/v1/webhook/payment
when we run this, we get a webhook secret..copy that and paste it in application.properties
stripe.webhook.secret= whsec_2a8b7fcf8d6e4a85e5b7797b49fb4311cd1d9c456984dab447d3d3de0a4091c5

now create the webhook controller

booking confirmed is implemented ..we can see in the database
steps:
1. Log in
2. Init Booking
3. Add guests
4. Init Payments
5. Cancel booking
   for cancel booking
    POST http://localhost:8080/api/v1/bookings/13/cancel (bearer token from login)

Now we will make an api through which client know the status of booking (we have to implement polling mechanism)
Our frontend can keep calling the api in order to get the current status. Once the status is marked as confirmed, 
frontend can redirect the user to some other page.

creating the admin apis

1. GetAllHotels
GET http://localhost:8080/api/v1/admin/hotels (having the role of HOTEL_MANAGER)

2. Get All Bookings
GET http://localhost:8080/api/v1/admin/hotels/1/bookings

3. Generate Report
GET http://localhost:8080/api/v1/admin/hotels/1/reports

4. Update room by id
PUT http://localhost:8080/api/v1/admin/hotels/1/rooms/1

{
"type":"Economical Room",
"basePrice":80.00,
"capacity":2,
"totalCount":60,
"amenities":["WiFi", "Air Conditioning"],
"photos": ["http://via.placeholder.com/50","http://via.placeholder.com/150"]
}

5. Get Inventory By Room Id
GET http://localhost:8080/api/v1/admin/inventory/rooms/1

6. Update inventory
PATCH http://localhost:8080/api/v1/admin/inventory/rooms/1
   {
   "startDate":"2026-01-05",
   "endDate":"2026-01-07",
   "surgeFactor":1.5,
   "closed":true
   }

Profile APIs

1. Update My Profile
PATCH http://localhost:8080/api/v1/users/profile

{
"name":"Supreet Kumar",
"dateOfBirth":"2000-10-10",
"gender":"MALE"
}

2. Get My Profile
GET http://localhost:8080/api/v1/users/profile

3. Add a guest
POST http://localhost:8080/api/v1/users/guests

{
"name":"HarsH",
"gender":"MALE",
"age":23
}

4. Update guest By Id
PUT http://localhost:8080/api/v1/users/guests/27
   {

   "name":"Harshit",
   "gender":"MALE",
   "age":24
   }

5. Delete guest By Id
DELETE http://localhost:8080/api/v1/users/guests/27

swagger api
added the dependency and hit the url below:
http://localhost:8080/api/v1/swagger-ui/index.html#/
