FROM maven:3-openjdk-18 as build-niletado-personal

RUN echo "hello world"

COPY . /

RUN ls -la

RUN mvn package

# debug
#RUN ls -la
#RUN ls -la target

FROM openjdk:18 as niletado-personal

COPY --from=build-niletado-personal /target/openapi-spring-1.0.0.jar /

ENTRYPOINT java -jar /openapi-spring-1.0.0.jar
