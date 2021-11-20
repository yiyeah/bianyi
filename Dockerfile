FROM openjdk:15
WORKDIR /app/
COPY ./* /folder/
WORKDIR /folder/
ENV CLASSPATH=".:./antlr-4.9.2-complete.jar:$CLASSPATH"
RUN javac Main.java
