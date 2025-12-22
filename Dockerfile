FROM maven:latest AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME

RUN mvn package

FROM eclipse-temurin:25-jdk AS runtime
RUN mkdir /opt/app
RUN mkdir /assets
COPY --from=build /usr/app/target/discordbot-v1.1.8-jar-with-dependencies.jar /opt/app/app.jar
COPY --chmod=444 src/main/java/org/ironriders/discordbot/assets /assets/
ENTRYPOINT java -jar /opt/app/app.jar
