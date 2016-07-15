#Sending JSON to the REST Endpoint

##HTTP Requests
The REST endpoint will be waiting for an **HTTP POST** request from the clients (Raspberry Pi and Arduino). The endpoint will only have methods mapped to the POST method. GET, PATCH, DELETE, and **other HTTP methods will not be functional** for this endpoint.

##Endpoint details
**hostname: localhost**  
**uri: /send_data/v1**

##The Header
POST /send_data/v1 HTTP 1.0  
Content-Type: application/json  
Accept: application/json

##The Body
Below is an example of the JSON that will be in the HTTP request that will be coming from clients (Raspberry Pi and Arduino) and what the server at the REST endpoint will be expecting.

``` json
{
	"device_id" : "123456789",
	"location" : "example_room",
    "start_datetime" : "Sat Jul 9 19:59:59 EDT 2016",
    "stop_datetime" : "Sat Jul 9 19:59:59 EDT 2016",
	"temperature_data" : {
		"temperature_1" : {
			"temperature" : 78,
			"datetime" : "Sat Jul 9 19:59:59 EDT 2016"
		},
		"temperature_2" : {
			"temperature" : 79,
			"datetime" : "Sat Jul 9 19:58:59 EDT 2016"
		},
		"temperature_3" : {
			"temperature" : 80,
			"datetime" : "Sat Jul 9 19:57:59 EDT 2016"
		}
	}
}
```

##Example POST Request
```http
POST /send_data/v1 HTTP 1.0
Content-Type: application/json
Accept: application/json

{
	"device_id" : "123456789",
	"location" : "example_room",
    "start_datetime" : "Sat Jul 9 19:59:59 EDT 2016",
    "stop_datetime" : "Sat Jul 9 19:59:59 EDT 2016",
	"temperature_data" : {
		"temperature_1" : {
			"temperature" : 78,
			"datetime" : "Sat Jul 9 19:59:59 EDT 2016"
		},
		"temperature_2" : {
			"temperature" : 79,
			"datetime" : "Sat Jul 9 19:58:59 EDT 2016"
		},
		"temperature_3" : {
			"temperature" : 80,
			"datetime" : "Sat Jul 9 19:57:59 EDT 2016"
		}
	}
}

```