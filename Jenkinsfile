pipeline {
    agent any
    
    // This tells Jenkins to inject Maven into the PATH for this pipeline
    tools {
        maven 'Maven3'
    }
    
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
                bat 'mvn clean package'
                echo 'Application compiled and unit tests passed.'
            }
        }
        stage('Build Docker Image') {
            steps {
                bat "docker build -t ${IMAGE_NAME} ."
                echo 'Docker image built successfully.'
            }
        }
        stage('Deploy Docker Container') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'SUCCESS') {
                    bat "docker stop ${CONTAINER_NAME}"
                    bat "docker rm ${CONTAINER_NAME}"
                }
                bat "docker run -d -p 9090:8080 --name ${CONTAINER_NAME} ${IMAGE_NAME}"
                echo 'Container deployed and running.'
            }
        }
    }
}