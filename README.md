HotelHub Project

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
