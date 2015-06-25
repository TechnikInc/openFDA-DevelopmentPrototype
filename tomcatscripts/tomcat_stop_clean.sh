#!/bin/bash

# Stop Tomcat first
sudo service tomcat7  stop

# Clean up the old version of app otherwise, there might be an issue
rm -rf /usr/share/tomcat7/webapps/technikfda
rm -rf /usr/share/tomcat7/webapps/technikfda.war