before start service you must init a local memcached process run on port 11211__
buld: mvn package__
run: java -jar target/joke_api-1.0-SNAPSHOT.jar server__
local api url: http://localhost:8080/jokes?keyword={value}
