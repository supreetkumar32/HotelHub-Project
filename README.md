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

2. Get all rooms in hotel GET http://localhost:8080/api/v1/admin/hotels/2/rooms
3. Get room by id GET http://localhost:8080/api/v1/admin/hotels/2/rooms/1

