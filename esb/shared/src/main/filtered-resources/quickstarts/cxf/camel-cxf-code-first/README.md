camel-cxf-code-first: Demonstrates how to create a SOAP Web service
======================================================
Author: Fuse Team  
Level: Beginner  
Technologies: Camel, CXF, SOAP  
Summary: This quickstart demonstrates creating a SOAP Web service, using code first style, with Apache CXF and Camel and expose it through the OSGi HTTP Service.  
Target Product: Fuse  
Source: <https://github.com/jboss-fuse/quickstarts>  

We are using code first style, which means the web service is defined using Java code, and the JAX-WS annotations. The web service is then exposed as an endpoint in a Camel route.


What is it?
-----------

This quickstart demonstrates creating a SOAP Web service, using code first style, with Apache CXF and Camel and expose it through the OSGi HTTP Service.  
We are using code first style, which means the web service is defined using Java code, and the JAX-WS annotations. The web service is then exposed as an
endpoint in a Camel route.

System requirements
-------------------

Before building and running this quick start you need:

* Maven 3.1.1 or higher
* JDK 1.7 or 1.8
* JBoss Fuse 6


Build and Deploy the Quickstart
-------------------------

1. Change your working directory to `camel-cxf-code-first` directory.
* Run `mvn clean install` to build the quickstart.
* Start JBoss Fuse 6 by running bin/fuse (on Linux) or bin\fuse.bat (on Windows).
* In the JBoss Fuse console, enter the following command:

        osgi:install -s mvn:org.jboss.quickstarts.fuse/cxf-camel-cxf-code-first/${project.version}

* Fuse should give you an id when the bundle is deployed

* You can check that everything is ok by issuing  the command:

        osgi:list
   your bundle should be present at the end of the list


Use the bundle
---------------------

To use the application be sure to have deployed the quickstart in Fuse as described above. 

1. cd to the 'camel-cxf-code-first' directory
* Run 'mvn -Ptest test

Or open 'http://localhost:8181/cxf/' in a browser to see 'OrderEndpoint' listed as a SOAP service.

Undeploy the Archive
--------------------

To stop and undeploy the bundle in Fuse:

1. Enter `osgi:list` command to retrieve your bundle id
2. To stop and uninstall the bundle enter

        osgi:uninstall <id>
