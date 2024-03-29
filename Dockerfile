FROM maven:3-openjdk-18 as build-biletado-personal
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME

#COPY . /build
# debug
# RUN ls -la # ls /build

# build jar
RUN mvn -Dmaven.test.skip -f $HOME/pom.xml clean package

# debug
#RUN ls -la
#RUN ls -la target


FROM openjdk:19 as biletado-personal
ENV HOME=/usr/app

# copy jar from build container
COPY --from=build-biletado-personal $HOME/target/openapi-spring-*.jar $HOME/

ENTRYPOINT SPRING_PROFILES_ACTIVE=prod java -jar $HOME/openapi-spring-*.jar --spring.config.location=optional:$HOME/config/
