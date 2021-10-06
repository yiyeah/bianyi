FROM openjdk:12
WORKDIR /app/
COPY Test.java ./
RUN javac Test.java
