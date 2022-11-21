before start service you must init a local memcached process run on port 11211
buld: mvn package
run: java -jar target/joke_api-1.0-SNAPSHOT.jar server
local api url: http://localhost:8080/jokes?keyword={value}
