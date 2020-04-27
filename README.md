# Share A Ride

A Java REST API to find others to ride with

## Installation

You must have Apache Maven and Java installed on your computer to run this project

To install maven on ubuntu:
https://linuxize.com/post/how-to-install-apache-maven-on-ubuntu-18-04/

## Usage
Inside shareAride directory run:
```
mvn package
java -jar target/shareAride-1.0-SNAPSHOT.jar server config.yml
```

For Coverage, inside shareAride directory run:
```
mvn clean jacoco:prepare-agent install jacoco:report
```
Then go into /target/site/index.html

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)