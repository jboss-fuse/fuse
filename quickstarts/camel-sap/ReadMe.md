camel-sap: Demonstrates using the camel-sap component
======================================================
Author: William Collins - JBoss Fuse Team  
Level: Advanced  
Technologies: Camel, SAP  
Summary: This quickstart demonstrates how to use the JBoss Fuse SAP camel components in Camel in order to integrate with SAP  
Target Product: Fuse  
Source: <https://github.com/jboss-fuse/fuse/tree/master/quickstarts/camel-sap>  

This quick start shows how to use the SAP Camel Component and demonstrates:

* How to configure the `SAP Camel Component`.
* How to process an inbound request from SAP through an `SAP Server Endpoint`.
* How to build and return an outbound response to SAP through an `SAP Server Endpoint`.
* How to build and send an outbound request to SAP through an `SAP Destination Endpoint`.
* How to process an inbound response from SAP through an `SAP Destination Endpoint`.
* How to unmarshal and marshal SAP requests and responses into custom JAXB beans. 
* How to establish an `SAP Transaction Context` within a Camel route.

This quick start performs a Flight Booking in SAP from Camel using the SAP Camel Component and the Flight Data Application in SAP.

There is one top-level route containing four sub-routes in this example:

* The top-level route, `book-flight`, implements the Function Module `BOOK_FLIGHT` which performs a Flight Booking in SAP. This route receives, through an `SAP Server Endpoint` at the start of the route, a sRFC request from SAP and returns a sRFC response to SAP. It invokes the four sub-routes to perform a flight booking.
* The sub-route `find-connection` makes a series of calls into SAP, using `SAP Destination Endpoints`, to find a Flight Connection matching the criteria in the request for the Flight Booking
* The sub-route `find-customer-info` makes a call into SAP, using an `SAP Destination Endpoint`, to get detailed information about the Flight Customer (Travel Agent) requesting the Flight Booking
* The sub-route `find-passenger-info` gathers information about the Passenger for the Flight Booking.
* Finally the sub-route `create-flight-trip` makes a call into SAP, using an `SAP Destination Endpoint` in an `SAP Transaction Context`, to create the Flight Booking in SAP. 

### Building and Deploying this Quick Start

1. Change your working directory to `quickstarts/camel-sap` directory.
1. Run `mvn clean install` to build thie quick start.

After building from the source code, you can upload the changes to the fabric container: 

1. It is assumed that you have already create a fabric and logged into a container called `root`.
1. Change your working directory to `quickstarts/camel-sap` directory.
1. Run `mvn fabric8:deploy` to upload the quick start to the fabric container.

If you run the `fabric:deploy` command for the first then, it will ask you for the username and password to login the fabric container.
And then store this information in the local Maven settings file. You can find more details about this on the fabric8 website about the [Maven Plugin](http://fabric8.io/gitbook/mavenPlugin.html).

### [Prerequisites to run this example](id:requirements)

To run this quick start you will need an SAP instance available with the following installed:

1. Ensure that the [Flight Data Application](http://help.sap.com/saphelp_erp60_sp/helpdata/en/db/7c623cf568896be10000000a11405a/content.htm) of the `ABAP Workbench` has been setup. 
1. Ensure that an RFC Destination has been [setup for registration of the SAP Camel Component](http://help.sap.com/saphelp_nw73ehp1/helpdata/en/48/c7b790da5e31ebe10000000a42189b/content.htm?frameset=/en/48/a98f837e28674be10000000a421937/frameset.htm) with the instance's [SAP Gateway](http://help.sap.com/saphelp_nw70ehp3/helpdata/en/31/42f34a7cab4cb586177f85a0cf6780/frameset.htm). The destination should have a Connection Type `T` and the following `Technical Settings`:
	1. **Activation Type** : `Registered Server Program`
	1. **Program ID** : `JCO_SERVER`
1. Ensure the following [`ZBOOK_FLIGHT`](id:zbook_flight) ABAP program is installed and activated: 

		*&---------------------------------------------------------------------*
		*& Report  ZBOOK_FLIGHT
		*&
		*&---------------------------------------------------------------------*
		*&
		*&
		*&---------------------------------------------------------------------*

		REPORT  ZBOOK_FLIGHT.

		TYPES: BEGIN OF FLTINFO_STRUCTURE,
        		FLIGHTTIME(10) TYPE N,
        		CITYFROM(20) TYPE C,
        		DEPDATE TYPE D,
        		DEPTIME TYPE T,
        		CITYTO(20) TYPE C,
        		ARRDATE TYPE D,
        		ARRTIME TYPE T,
        END OF FLTINFO_STRUCTURE.
		
		TYPES: BEGIN OF CONNECTION_INFO_STRUCTURE,
        		CONNID(1) TYPE N,
        		AIRLINE(20) TYPE C,
        		PLANETYPE(10) TYPE C,
        		CITYFROM(20) TYPE C,
        		DEPDATE TYPE D,
        		DEPTIME TYPE T,
        		CITYTO(20) TYPE C,
        		ARRDATE TYPE D,
        		ARRTIME TYPE T,
        END OF CONNECTION_INFO_STRUCTURE.

		DATA: RFCDEST LIKE RFCDES-RFCDEST VALUE 'NONE'.

		DATA: RFC_MESS(128).

		DATA:  TRIPNUMBER(8) TYPE N VALUE '01234567',
       		TICKET_PRICE(12) TYPE P DECIMALS 4,
       		TICKET_TAX(12) TYPE P DECIMALS 4,
       		CURRENCY(5) TYPE C,
       		FLTINFO TYPE FLTINFO_STRUCTURE.

		DATA:  CONNECTION_INFO TYPE ZCONNECTION_INFO_STRUCTURE.

		DATA: INT_CONNECTION_INFO TYPE ZCONNECTION_INFO_TABLE.
		
		PARAMETERS: AGENCYNM(8) TYPE N DEFAULT 00000110,
            		CUSTNAME(25) TYPE C DEFAULT 'James Legrand' LOWER CASE,
            		FLTDATE TYPE D DEFAULT '20140319',
            		DESTFROM(3)   TYPE C DEFAULT 'SFO',
           			DESTTO(3)  TYPE C DEFAULT 'FRA',
            		PASSFORM(15) TYPE C LOWER CASE,
            		PASSNAME(25) TYPE C LOWER CASE,
            		PASSBIRT TYPE D.

		RFCDEST = 'JCOSERVER01'.

		CALL FUNCTION 'BOOK_FLIGHT'
		DESTINATION RFCDEST
		EXPORTING
    		CUSTNAME = CUSTNAME
    		PASSFORM = PASSFORM
    		PASSNAME = PASSNAME
    		PASSBIRTH = PASSBIRT
    		FLIGHTDATE = FLTDATE
    		TRAVELAGENCYNUMBER = AGENCYNM
    		DESTINATION_FROM = DESTFROM
    		DESTINATION_TO = DESTTO
    	IMPORTING
    		TRIPNUMBER = TRIPNUMBER
    		TICKET_PRICE = TICKET_PRICE
    		TICKET_TAX = TICKET_TAX
    		CURRENCY = CURRENCY
    		PASSFORM = PASSFORM
    		PASSNAME = PASSNAME
    		PASSBIRTH = PASSBIRT
    		FLTINFO = FLTINFO
    		CONNINFO = INT_CONNECTION_INFO.

		IF SY-SUBRC NE 0.

		WRITE: / 'Call ZBOOK_FLIGHT SY-SUBRC = ', SY-SUBRC.
		WRITE: / RFC_MESS.

		ELSE.

		WRITE: / 'Passenger: ', PASSFORM RIGHT-JUSTIFIED, ' ', PASSNAME LEFT-JUSTIFIED.

		ULINE.

		WRITE:
       		/ 'Trip Number:  ', TRIPNUMBER LEFT-JUSTIFIED,
       		/ 'Ticket Price: ', TICKET_PRICE DECIMALS 2 LEFT-JUSTIFIED,
       		/ 'Ticket Tax: ', TICKET_TAX DECIMALS 2 LEFT-JUSTIFIED,
       		/ 'Currency: ', CURRENCY LEFT-JUSTIFIED.

		ULINE.

		WRITE: / 'Flight Information',
       		/5 'Flight Time', 25 'Departure City', 55 'Departure Date', 75 'Departure Time', 95 			'Arrival City', 125 'Arrival Date', 145 'Arrival Time',
       		/5(10) FLTINFO-FLIGHTTIME, 25(20) FLTINFO-CITYFROM, 55(10) FLTINFO-DEPDATE, 75(10) 		FLTINFO-DEPTIME, 95(20) FLTINFO-CITYTO, 125(10) FLTINFO-ARRDATE, 145(10) FLTINFO-ARRTIME.

		ULINE.
		WRITE: / 'Flight Connections'.

		WRITE: /5 'Connection ID', 20 'Airline', 40 'Plane Type', 60 'Departure City', 90 'Departure 			Date', 110 'Departure Time', 130 'Arrival City', 160 'Arrival Date', 180 'Arrival Time'.

		LOOP AT INT_CONNECTION_INFO INTO CONNECTION_INFO.

		WRITE: /5 CONNECTION_INFO-CONNID, 20 CONNECTION_INFO-AIRLINE, 40 CONNECTION_INFO-PLANETYPE, 			60 CONNECTION_INFO-CITYFROM, 90 CONNECTION_INFO-DEPDATE, 110 CONNECTION_INFO-DEPTIME, 130 		CONNECTION_INFO-CITYTO,
          160 CONNECTION_INFO-ARRDATE, 180 CONNECTION_INFO-ARRTIME.

		ENDLOOP.

		ENDIF.


## How to run this example

The following information is divided into two sections, whether you are using the command line shell in fabric, or using the web console.

### Using the command line shell

You can run this example at the console command line, as follows:

1. It is assumed that you have already created a fabric and are logged into a container called `root`.
1. It is also assumed that you have an SAP instance available with the previously mentioned [requirements](#requirements) satisfied. 
1. Edit the `org.jboss.quickstarts.fuse-camel-sap` profile 

		fabric:profile-edit org.jboss.quickstarts.fuse-camel-sap		
And add the network locations of your SAP Java Connector libraries to the profile.

		lib.sapjco3.jar=http://host/path/to/library/sapjco3.jar
		lib.sapidoc3.jar=http://host/path/to/library/sapidoc3.jar
		lib.sapjco3.nativelib=http://host/path/to/library/<native-lib>

1. Edit the camel context file:

		fabric:profile-edit --resource camel.xml org.jboss.quickstarts.fuse-camel-sap		
And ensure that the connection properties of the `quickstartDestinationData` and `quickstartServerData` beans in the file matches those of your SAP instance:

		<bean id="quickstartDestinationData"
			class="org.fusesource.camel.component.sap.model.rfc.impl.DestinationDataImpl">
			<property name="ashost" value="example.com" />
			<property name="sysnr" value="00" />
			<property name="client" value="000" />
			<property name="user" value="username" />
			<property name="passwd" value="password" />
			<property name="lang" value="en" />
		</bean>

		<bean id="quickstartServerData"
			class="org.fusesource.camel.component.sap.model.rfc.impl.ServerDataImpl">
			<property name="gwhost" value="example.com" />
			<property name="gwserv" value="3300" />
			<property name="progid" value="JCO_SERVER" />
			<!-- This property value should not be changed -->
			<property name="repositoryDestination" value="quickstartDest" />
			<property name="connectionCount" value="2" />
			<property name="trace" value="1"/>
		</bean>

1. Create a new child container and deploy the `quickstart-camel-sap` profile in a single step, by entering the
 following command at the console:

        fabric:container-create-child --profile org.jboss.quickstarts.fuse-camel-sap root mychild

1. Wait for the new child container, `mychild`, to start up. Use the `fabric:container-list` command to check the status of the `mychild` container and wait until the `[provision status]` is shown as `success`.

### Using the web console

You can run this example from the web console as follows:

1. It is assumed that you have already created a fabric and are logged into a container called `root`.
1. It is also assumed that you have an SAP instance available with the previously mentioned [requirements](#requirements) satisfied.
1. Login to the web console.
1. Click the *Wiki* button in the navigation bar.
1. Select `org.jboss.quickstarts.fuse` --> `camel` --> `sap` .
1. Edit the `io.fabric8.agent.properties` file and add the network location on your SAP Java Connector libraries to this file.

		lib.sapjco3.jar=http://host/path/to/libraries/sapjco3.jar
		lib.sapidoc3.jar=http://host/path/to/libraries/sapidoc3.jar
		lib.sapjco3.nativelib=http://host/path/to/libraries/<native-lib>

1. Edit the `camel.xml` file and ensure that the connection properties of the `quickstartDestinationData` and `quickstartServerData` beans in the file matches those of your SAP instance:

		<bean id="quickstartDestinationData"
			class="org.fusesource.camel.component.sap.model.rfc.impl.DestinationDataImpl">
			<property name="ashost" value="example.com" />
			<property name="sysnr" value="00" />
			<property name="client" value="000" />
			<property name="user" value="username" />
			<property name="passwd" value="password" />
			<property name="lang" value="en" />
		</bean>

		<bean id="quickstartServerData"
			class="org.fusesource.camel.component.sap.model.rfc.impl.ServerDataImpl">
			<property name="gwhost" value="example.com" />
			<property name="gwserv" value="3300" />
			<property name="progid" value="JCO_SERVER" />
			<!-- This property value should not be changed -->
			<property name="repositoryDestination" value="quickstartDest" />
			<property name="connectionCount" value="2" />
			<property name="trace" value="1"/>
		</bean>

1. Click the `New` button in the top right corner.
1. In the `Create New Container` page, in the **Container Name** field enter `mychild` and click the *Create and start container* button


## How to try this example

The following information is divded into two sections, whether you are using the command line shell in fabric, or using the web console.

### Using the command line shell

To use the application be sure to have deployed the quickstart in fabric8 as described above. Successful deployment will create and start a Camel route in fabric8.

1. Log into the `mychild` container using the `fabric:container-connect` command, as follows:

		fabric:container-connect mychild
				
1. Invoke the camel route from SAP by running the [ZBOOK_FILE](#zbook_flight) program.
1. Enter into the input screen of the program, the flight booking parameters. **Note** that valid agency codes, travel agent names, and flight dates will depend on the data installed in your Flight Data Application. 
1. Notice the flight booking information returned on the programs output screen.


### Using the web console

1. Login the web console
1. Click the Runtime button in the navigation bar
1. Select the `mychild` container in the containers list, and click the *open* button right next to the container name.
1. A new window opens and connects to the container. Click the *Camel* button in the navigation bar.
1. Invoke the camel route from SAP by running the [ZBOOK_FILE](#zbook_flight) program.
1. Enter into the input screen of the program, the flight booking parameters. **Note** that valid agency codes, travel agent names, and flight dates will depend on the data installed in your Flight Data Application. 
1. In the Camel tree, expand the `Routes` node, and select the `book-flight` node. And click the *Diagram* button to see a visual representation of the route.
1. Notice the numbers in the diagram, which illustrate that a message has been processed. 
1. Notice the flight booking information returned on the programs output screen.


## Undeploy this example

The following information is divded into two sections, whether you are using the command line shell in fabric, or using the web console.

### Using the command line shell

To stop and undeploy the example in fabric8:
	
1. Disconnect from the child container by typing Ctrl-D at the console prompt.
1. Stop and delete the child container by entering the following command at the console:

		fabric:container-stop mychild
		fabric:container-delete mychild

### Using the web console

To stop and undeploy the example in fabric8:

1. In the web console, click the *Containers* button in the navigation bar.
1. Select the `mychild`containers in the *Containers* list and click the *Stop* button in the top right corner.
