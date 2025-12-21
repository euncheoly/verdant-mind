pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Gradle Tests') {
            steps {
                echo 'Running Unit Tests...'
                // Run gradle test inside a container. 
                // We mount the current directory so the container can see the code.
                sh 'docker run --rm -v "$(pwd)":/home/gradle/project -w /home/gradle/project gradle:8.7-jdk17 gradle test'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Spring Boot Docker Image...'
                sh 'docker build -t my-gradle-app:${BUILD_NUMBER} .'
                sh 'docker tag my-gradle-app:${BUILD_NUMBER} my-gradle-app:latest'
            }
        }
    }

    post {
        success {
            echo 'Build and Test successful!'
        }
        failure {
            echo 'Build or Test failed. Check the logs.'
        }
    }
}