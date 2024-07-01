FROM openjdk:21-oracle
EXPOSE 80
COPY target/SpotifyLikeBanda-1.0.0.jar SpotifyLikeBanda-1.0.0.jar
ENTRYPOINT ["java", "-jar", "./SpotifyLikeBanda-1.0.0.jar", "--server.port=80"]