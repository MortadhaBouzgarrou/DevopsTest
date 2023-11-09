# Utilisation d'une image de base OpenJDK

FROM openjdk:11
# Expose the port your Spring Boot application runs on (change to the actual port)
EXPOSE 8082
# Définition du répertoire de travail dans le conteneur (chaque commande bch tsir f wost rep heka)
WORKDIR /app
# Télécharger le fichier JAR depuis Nexus (a travers l'outil curl) et le copier dans le conteneur
RUN apt-get update && apt-get install -y curl
RUN curl -o DevOps_Project-2.1.jar -L "http://192.168.33.10:8081/repository/maven-releases/tn/esprit/DevOps_Project/2.1/DevOps_Project-2.1.jar"
#Utilisez un argument de build pour définir le mot de passe pendant la build
# Définir le point d'entrée de votre application : texecuti jar eli jebtu b java -jar
ENTRYPOINT ["java","-jar","DevOps_Project-2.1.jar"]