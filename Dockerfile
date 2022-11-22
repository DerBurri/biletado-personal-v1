FROM maven:3-openjdk-18 as build-biletado-personal

COPY . /

# debug
#RUN ls -la

RUN mvn package

# debug
#RUN ls -la
#RUN ls -la target

FROM openjdk:18 as biletado-personal

COPY --from=build-biletado-personal /target/openapi-spring-1.0.0.jar /

ENTRYPOINT java -jar /openapi-spring-1.0.0.jar
