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
                // Using bat instead of sh for Windows
                bat 'mvn clean package'
                echo 'Application compiled and unit tests passed.'
            }
        }
        stage('Build Docker Image') {
            steps {
                // Double quotes allow Groovy to inject the environment variables
                bat "docker build -t ${IMAGE_NAME} ."
                echo 'Docker image built successfully.'
            }
        }
        stage('Deploy Docker Container') {
            steps {
                // catchError prevents the pipeline from failing on first run when the container doesn't exist yet
                catchError(buildResult: 'SUCCESS', stageResult: 'SUCCESS') {
                    bat "docker stop ${CONTAINER_NAME}"
                    bat "docker rm ${CONTAINER_NAME}"
                }
                bat "docker run -d -p 8080:8080 --name ${CONTAINER_NAME} ${IMAGE_NAME}"
                echo 'Container deployed and running.'
            }
        }
    }
}