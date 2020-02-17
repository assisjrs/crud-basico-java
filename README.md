

##Executar banco via docker
 docker run -d -p 9001:9001 --name hsqldb blacklabelops/hsqldb
 
##Executar interface gráfica do banco 
 docker cp hsqldb:/opt/hsqldb/hsqldb.jar .
 java -jar hsqldb.jar
 
##Executar a aplicação
###Build da aplicação
./gradlew clean build

./gradlew bootRun

###Build da imagem docker
 docker build -t crud-java:latest .

###Execução da imagem
 docker run -d -p 9090:9090 --name crud-java crud-java
 