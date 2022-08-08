#1. setup application.properties
#2. add database
#3. add smtp server
#4. setup storage.path


#add env variable to ipv4 IP displaying
export _JAVA_OPTIONS=-Djava.net.preferIPv4Stack=true

#run project
mvn clean install spring-boot:start

#stop project
#mvn spring-boot:stop