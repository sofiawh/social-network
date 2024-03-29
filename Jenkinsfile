// pipeline {
//     agent any
//     tools{
//         maven 'maven_3_5_0'
//     }
//     stages{
//         stage('Build Maven'){
//             steps{
//                 checkout([$class: 'GitSCM', branches: [[name: '*/devsofia']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sofiawh/social-network']]])
//                 sh 'mvn clean install'
//             }
//         }
//         stage('Build docker image'){
//             steps{
//                 script{
//                     sh 'docker build -t sofiawh/social-network .'
//                 }
//             }
//         }
//         stage('Push image to Hub'){
//             steps{
//                 script{
//                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
//                    sh 'docker login -u sofiah -p ${dockerhubpwd}'
//
// }
//                    sh 'docker push sofiawh/social-network'
//                 }
//             }
//         }
//         stage('Deploy to k8s'){
//             steps{
//                 script{
//                     kubernetesDeploy (configs: 'deploymentservice.yaml',kubeconfigId: 'k8sconfigpwd')
//                 }
//             }
//         }
//     }
// }

pipeline {
    agent any
    tools {
        maven 'maven_3_5_0'
    }
    stages {
        stage('Build Maven') {
            steps {
                script {
                    // Parcourir les répertoires des microservices et exécuter Maven pour construire
                    def microservices = ['discovery', 'auth-service', 'feeds-service', 'Friend-service', 'interaction-service', 'media-service', 'notification-service', 'service-post', 'User-service', 'geteway']

                    microservices.each { service ->
                        dir(service) {
                            checkout([$class: 'GitSCM', branches: [[name: '*/devsofia']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sofiawh/social-network']]])
                            sh "mvn clean install"
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
                            sh "mvn test"
                        }
                    }
                }
            }
        }
        stage('Build and Deploy with Docker Compose') {
            steps {
                // Construction et déploiement avec Docker Compose
                sh 'docker-compose -f docker-compose.yml up -d --build'
            }
        }
    }
}





//
//
// pipeline {
//     agent any
//     tools {
//         maven 'maven_3_5_0'
//     }
//     stages {
//         stage('Build Maven') {
//             steps {
//                 script {
//                     // Parcourir les répertoires des microservices et exécuter Maven
//                     def microservices = ['discovery', 'auth-service', 'feeds-service', 'Friend-service', 'interaction-service', 'media-service', 'notification-service', 'service-post', 'User-service', 'geteway']
//
//                     microservices.each { service ->
//                         dir(service) {
//                             checkout([$class: 'GitSCM', branches: [[name: '*/devsofia']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sofiawh/social-network']]])
//                             sh "mvn clean install"
//                         }
//                     }
//                 }
//             }
//         }
//         stage('Build and Deploy with Docker Compose') {
//             steps {
//                 // Construction et déploiement avec Docker Compose
//                 sh 'docker-compose -f docker-compose.yml up -d --build'
//             }
//         }
//     }
// }
//
//
//
//
// pipeline {
//     agent any
//     tools{
//         maven 'maven_3_5_0'
//     }
//     stages{
//         stage('Build Maven'){
//             steps{
//                 script {
//                     // Parcourir les répertoires des microservices et exécuter Maven
//                     def microservices = ['discovery', 'auth-service', 'feeds-service', 'Friend-service', 'interaction-service', 'media-service', 'notification-service', 'service-post', 'User-service', 'geteway']
//
//                     microservices.each { service ->
//                         dir(service) {
//                             checkout([$class: 'GitSCM', branches: [[name: '*/devsofia']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sofiawh/social-network']]])
//                             sh "mvn clean install"
//                         }
//                     }
//                 }
//             }
//         }
//         stage('Build docker image'){
//             steps{
//                 script{
//                     // Parcourir les répertoires des microservices et construire les images Docker
//                     def microservices = ['discovery', 'auth-service', 'feeds-service', 'Friend-service', 'interaction-service', 'media-service', 'notification-service', 'service-post', 'User-service', 'geteway']
//
//                     microservices.each { service ->
//                         dir(service) {
//                             sh "docker build -t sofiawh/${service} ."
//                         }
//                     }
//                 }
//             }
//         }
//         stage('Push image to Hub'){
//             steps{
//                 script{
//                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
//                         // Parcourir les répertoires des microservices et pousser les images Docker vers Docker Hub
//                         def microservices = ['discovery', 'auth-service', 'feeds-service', 'Friend-service', 'interaction-service', 'media-service', 'notification-service', 'service-post', 'User-service', 'geteway']
//
//                         microservices.each { service ->
//                             sh "docker login -u sofiah -p ${dockerhubpwd}"
//                             sh "docker push sofiawh/${service}"
//                         }
//                     }
//                 }
//             }
//         }
//     }
// }