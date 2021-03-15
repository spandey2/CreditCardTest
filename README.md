
Getting Started
To install this example application, run the following commands:

git clone https://github.com/spandey2/CreditCardTest/tree/master
cd spring-boot-microservices-example


Create Applications

Application Name: CreditCards
Base URIs: http://localhost:8080

Can change the port in application.properties

Docker

docker run -p 8080 -it --rm account-java:latest
docker run -p 8080:8080 -it --rm account-java

Api for creating credit card for a given name, card number, and limit
/api/account/add

Api for returning all cards in the system

/api/account/getall

Used H2 in-memory DB to store the information while the API is running

Basic authentication is added in the service -check the username password from application.properties


