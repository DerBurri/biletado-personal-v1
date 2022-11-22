FROM maven:3-openjdk-18 as build-biletado-personal

WORKDIR /build

#COPY . /build
COPY src/ /build/src
COPY pom.xml /build

# debug
# RUN ls -la # ls /build

# build jar
RUN mvn package

# debug
#RUN ls -la
#RUN ls -la target


FROM openjdk:18 as biletado-personal

# copy jar from build container
COPY --from=build-biletado-personal /build/target/openapi-spring-1.0.0.jar /

ENTRYPOINT java -jar /openapi-spring-1.0.0.jar
