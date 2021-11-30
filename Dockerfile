FROM openjdk:15
WORKDIR /app/
COPY test1.java ./
RUN javac test1.java
