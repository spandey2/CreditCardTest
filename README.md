
Getting Started. 

Prerequisites:

1: java 1.8 install in machine<br />
2: docker install in machine<br />
3: git install in machine<br />


To install this example application, run the following commands:

git clone https://github.com/spandey2/CreditCardTest/tree/master<br />
cd CreditCardTest<br />


Create Applications:

Application Name: CreditCards<br />
Base URI http://localhost:8080

Can change the port in application.properties

Maven:<br />
mvn clean package<br />

Docker:<br />
docker build -t account-java . <br />
docker run -p 8080:8080 -it --rm account-java<br />

Api for creating credit card for a given name, card number, and limit<br />
/api/account/add

Api for returning all cards in the system<br />
/api/account/getall

I have used H2 in-memory DB to store the information while the API is running.

Basic authentication is added in the service -check the username password from application.properties


