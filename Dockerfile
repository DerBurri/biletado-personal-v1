FROM maven:3-openjdk-18 as build-biletado-personal
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME

#COPY . /build
# debug
# RUN ls -la # ls /build

# build jar
RUN --mount=type=cache,target=/root/.m2 mvn -f $HOME/pom.xml clean package

# debug
#RUN ls -la
#RUN ls -la target


FROM openjdk:19 as biletado-personal
ENV HOME=/usr/app

# copy jar from build container
COPY --from=build-biletado-personal $HOME/target/openapi-spring-*.jar $HOME/

ENTRYPOINT java -jar $HOME/openapi-spring-*.jar
