# Register page #

##Description##

Dummy registration page

### Prerequisites for building ###
1. Java SDK 7 should be installed
2. Maven 3.2.5 or newer should be installed (https://maven.apache.org/download.cgi) and configured.

### Build it all ###
1. Open a terminal window and navigate to the root of the project
2. Run ```mvn clean package```

### Run Jetty embedded server ###
1. Run the runner class Main.java in nl.rabobank.register package


     java -Dorg.apache.jasper.compiler.disablejsr199=true -jar target\sign-up-project-1.0-SNAPSHOT.jar

2. URL to the page: [http://localhost:8000/register](http://localhost:8000/register)
