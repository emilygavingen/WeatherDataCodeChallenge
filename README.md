# WeatherDataCodeChallenge
## Project Description
This project works with a MongoDB database to hold different instances of registered sensors and their unique metrics.
Each sensor is registered using either the clients inputted sensor ID, or if null, a generated sensor ID number. From here, the client is able to add metrics using a POST method endpoint containing the desired sensors ID number to write into and the new metrics, which will in-turn create new instances of the same sensor ID but with a new auto-generated unique Metric ID.
A series of endpoints are used to both receive and post to the database.

## Project Endpoints and Examples
http://localhost:8082/api/v1/sensors : Used with either GET or POST. GET will return a list of all sensors, and POST can be used to register a new sensor. E.g. of body for POST : 
{
"sensorId": "8h3g8hcv4095h9vbik30",
"countryName": "England",
"cityName": "London"
}

http://localhost:8082/api/v1/sensors/{{SENSOR_ID}} : This POST endpoint is used to add metrics to a specified sensor ID. E.g. of body :
{
"temperature": 15,
"humidity": 15,
"windSpeed": 15
}

http://localhost:8082/api/v1/sensors/query : GET method for querying data by city name and a specific time period. E.g of query :
?cityName=Dublin&start=2022-03-03T14:21:26.000

