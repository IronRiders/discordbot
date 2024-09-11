FROM maven:latest as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN mvn package

FROM openjdk:17-oracle
RUN mkdir /opt/app

COPY --from=build /usr/app/target/discordbot-v1.1.8-jar-with-dependencies.jar /opt/app/app.jar

ENTRYPOINT java -jar /opt/app/app.jar
