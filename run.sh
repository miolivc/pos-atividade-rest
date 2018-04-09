
docker build -t miolivc/db-pos-atividade-rest . -f Database
docker run -p 5433:5432 -d --name database miolivc/db-pos-atividade-rest

mvn package

docker build -t miolivc/app-pos-atividade-rest . -f Application
docker run -p 8081:8080 -d --name app --link database:host-database miolivc/app-pos-atividade-rest