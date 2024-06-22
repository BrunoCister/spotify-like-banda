FROM openjdk:21-oracle
COPY target/SpotifyLikeBanda-1.0.0.jar SpotifyLikeBanda-1.0.0.jar
ENTRYPOINT ["java", "-jar", "./SpotifyLikeBanda-1.0.0.jar"]