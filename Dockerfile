FROM tomcat:10.1-jdk21

WORKDIR /usr/local/tomcat/webapps/

COPY build/libs/sanctions-api.war ./sanctions-api.war

EXPOSE 8080

CMD ["catalina.sh", "run"]