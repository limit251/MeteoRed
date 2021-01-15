# Meteo*Red*

MeteoRed is an JavaFX based app, which provides weather information for the selected city. 
The program uses  OpenWeatherMapApi to gather weather current data of the city.

## GUI
The program offers a simple and basic interface, easy to use.\
The user selects a country and after that the city he wants the forecast for. 

![Application GUI](/images/gui.png)

## How to run

In order for the application to run properly, you have to provide it with an input file. 
The input file must contain information about each city you want to have it loaded in the app.

These information are:
***cityId cityName cityLat cityLon countryCode*** where:
- cityId : represents the unique identificator used by OpenWeatherMap to refer a city in the database
- cityName : the name of the city (! It should be in English)
- cityLan : latitude of the city
- cityLon : longitude of the city
- countryCode : each country has an unique 2 characters code

Example of input file:
*2973393 Tarascon 43.805828 4.660280 FR  
2986678 Ploufragan 48.491409 -2.794580 FR  
683506 Bucharest 12.312 123.41 RO*


## Classes

The program was developed using MVC arhitecture.
Representative classes are: WeatherController, LocationManager and ApiRequest.



## What I've learned

After finishing this project I've improved my knowledge in working with Java, JavaFx, parsing JSONs and OpenWeatherMapsApi. 

## Exceptions
When an exception occurs, user will receive a visual alert.
The alert contains the error and a "how to fix it" message.

![Exception example](/images/error.png)



## UML diagrams

![Flow Chart](/images/flowchart.png)

![Classes Diagram](/images/classes_diagram.png)

