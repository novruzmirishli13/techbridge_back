# Java 21 tabanlı image
FROM eclipse-temurin:21-jdk

# Çalışma dizini
WORKDIR /app

# Jar dosyasını kopyala
COPY build/libs/HZT-0.0.1-SNAPSHOT.jar app.jar

# Uygulama portu (Spring Boot için genelde 8080)
EXPOSE 8080

# Uygulamayı çalıştır
ENTRYPOINT ["java", "-jar", "app.jar"]

