# 换成你自己的版本
FROM openjdk:16-alpine3.13
WORKDIR /app/
COPY src ./src/
RUN javac -cp src -d out src/*.java