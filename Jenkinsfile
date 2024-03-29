
pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'JAVA_HOME'
    }
    stages {
        stage('Build Maven') {
            steps {
                script {
                    // Parcourir les répertoires des microservices et exécuter Maven pour construire
                    def microservices = ['media-service','discovery', 'auth-service', 'feeds-service', 'Friend-service', 'interaction-service', 'notification-service', 'service-post', 'User-service', 'geteway']

                    microservices.each { service ->
                        dir(service) {
                            checkout([$class: 'GitSCM', branches: [[name: '*/devsofia']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sofiawh/social-network']]])
                            bat "mvn clean install"
                        }
                    }
                }
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    // Parcourir les répertoires des microservices et exécuter les tests avec Maven
                    def microservices = ['discovery', 'auth-service', 'feeds-service', 'Friend-service', 'interaction-service', 'media-service', 'notification-service', 'service-post', 'User-service', 'geteway']

                    microservices.each { service ->
                        dir(service) {
                            bat  "mvn test"
                        }
                    }
                }
            }
        }
        stage('Build and Deploy with Docker Compose') {
            steps {
                // Construction et déploiement avec Docker Compose
                bat 'docker-compose -f docker-compose.yml up -d --build'
            }
        }
    }
}



