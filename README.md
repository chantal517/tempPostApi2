#Sending JSON to the REST Endpoint

##HTTP Requests
The REST endpoint will be waiting for **HTTP POST or GET** requests from the client. The endpoint will only have methods mapped to the POST and GET method. PATCH, DELETE, and **other HTTP methods will not be functional** for this endpoint.

##Endpoint details
HTTP Method | Host | Path | Port # | Description
--- | --- | ---
POST | localhost | /temp_data | 8080 | **Post a JSON array of temperature readings**
GET | localhost | /temp_data | 8080 | **Get a JSON array of temperature readings**

##POST
###The Header
``` http
POST /send_data/v1 HTTP 1.0
Content-Type: application/json
Accept: application/json
```

##The Body
Below is an example of the JSON that will be in the HTTP request or response that will be coming from clients or going to the clients respectively. The same payload (body) can be used in the POST and GET because we are simply displaying the same data that is sent from the sensors to the viewing application.

``` json
{
	"device_id" : "123456789",
	"location" : "example_room",
    "start_datetime" : "Sat Jul 9 19:58:59 EDT 2016",
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

###Example POST Request
```http
POST /send_data/v1 HTTP 1.0
Content-Type: application/json
Accept: application/json

{
	"device_id" : "123456789",
	"location" : "example_room",
    "start_datetime" : "Sat Jul 9 19:58:59 EDT 2016",
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

###The Response

##GET
###The Header

###Example GET Request

###The Response

####Successful

####Unsuccessful


