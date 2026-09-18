pipeline {
    agent any
    
    environment {
        IMAGE_NAME = "student-app"
        CONTAINER_NAME = "student-container"
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
                echo 'Source code checked out successfully.'
            }
        }
        stage('Maven Build & Test') {
            steps {
                sh 'mvn clean package'
                echo 'Application compiled and unit tests passed.'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ${IMAGE_NAME} .'
                echo 'Docker image built successfully.'
            }
        }
        stage('Deploy Docker Container') {
            steps {
                // Stop and remove the container if it's already running from a previous build
                sh 'docker stop ${CONTAINER_NAME} || true'
                sh 'docker rm ${CONTAINER_NAME} || true'
                // Run the new container mapping host port 8080 to container port 8080
                sh 'docker run -d -p 8080:8080 --name ${CONTAINER_NAME} ${IMAGE_NAME}'
                echo 'Container deployed and running.'
            }
        }
    }
}