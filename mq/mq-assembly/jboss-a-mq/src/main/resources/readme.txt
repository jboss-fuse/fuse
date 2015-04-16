JBoss A-MQ
----------

Configuration
-------------
The default broker is defined in: 
 ./etc/io.fabric8.mq.fabric.server-broker.cfg
The xml configuration is:
 ./etc/activemq.xml

Security Prerequisites
----------------------
By default, no users are defined for the container. You can run the container in the
foreground in this case, but you will not be able to access the container remotely and you will
not be able to run it in the background.

To enable remote access to the container, you must create at least one user in
the ./etc/users.properties file.
It is recommended that you create at least one user with the admin role by adding
a line with the following syntax:

<Username>=<Password>,admin

The admin role grants full administration privileges to the user.

Quick Start
-----------
To start JBoss A-MQ in the background, type 'bin/start' on Linux/Unix or 'bin\start.bat' on Windows.

Note: Be sure to use the appropriate username and password in the following examples.
To display the log using the remote console, type:

[Linux/Unix]
    ./bin/client log:display
[Windows]
    .\bin\client.bat log:display

To display the current broker statistics using the remote console, type:
   
[Linux/Unix]
    ./bin/client activemq:bstat
[Windows]
    .\bin\client.bat activemq:bstat

To validate the installation with a simple JMS producer and consumer, type:

[Linux/Unix]
    ./bin/client "activemq:producer --user <Username> --password <Password>"
[Windows]
    .\bin\client.bat "activemq:producer --user <Username> --password <Password>"

This will produce messages to the TEST queue. Check that messages have been enqueued with:

[Linux/Unix]
    ./bin/client activemq:dstat
[Windows]
    .\bin\client.bat activemq:dstat

To consume messages type:

[Linux/Unix]
    ./bin/client "activemq:consumer --user <Username> --password <Password>"
[Windows]
    .\bin\client.bat "activemq:consumer --user <Username> --password <Password>"

Check that messages have been dequeued with:

[Linux/Unix]
    ./bin/client activemq:dstat
[Windows]
    .\bin\client.bat activemq:dstat

View the webconsole at http://localhost:8181/hawtio

Documentation
-------------
You can find documentation online at:
https://access.redhat.com/site/documentation/JBoss_A-MQ/

