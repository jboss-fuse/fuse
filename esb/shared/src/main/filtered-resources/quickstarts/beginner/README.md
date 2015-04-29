beginner: Fuse Quickstarts for new Fuse users.
======================================================
Author: Fuse Team  
Level: Beginner  
Technologies: Fuse  
Summary: This directory contains the beginner quickstarts which demonstrate how to use fuse with various technologies.  
Target Product: Fuse  
Source: <https://github.com/jboss-fuse/quickstarts>  

The following quickstarts are beginner examples that use Apache Camel and which we recommend for first time users

* [camel.cbr](camel-cbr) - a small Camel application using Content Based Router (one of the most common EIP pattern)
* [camel.eips](camel-eips) - demonstrates a number of other commonly used EIP patterns with Apache Camel.
* [camel.errorhandler](camel-errorhandler) - introduction to error handling with Camel, including using redelivery and a Dead Letter Channel.
* [camel.log](camel-log) - a very simple Camel application using a timer to trigger a message every 5th second which is then written to the server log.
* [camel.log.wiki](camel-log-wiki) - a wiki based example of [camel.log](camel-log) where the Camel routes are stored in an <a fabric-version-link="/camel/canvas/fabric/profiles/quickstarts/beginner/camel.log.wiki.profile/camel-log.xml">XML file inside the wiki</a> so you can edit it via the browser and use <a href="/fabric/profiles/docs/fabric/rollingUpgrade.md">rolling upgrades</a> to update it and roll forward/backward changes to containers without having to release any Java artifacts.


 
