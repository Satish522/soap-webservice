pipeline {
    agent any
    tools{
        maven 'Maven'
    }
    stages {
        stage('Maven Build') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Satish522/soap-webservice.git']])
                sh 'mvn clean install'
            }
        }
        stage('Docker Build') {
            steps{
                script {
                    sh  'docker build -t satish2121/soap-webservice .'
                }
            }
        }
        stage('Publish Image to Hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhubcredential', variable: 'dockerhubcredential')]) {
                    sh 'docker login -u satish2121 -p ${dockerhubcredential}'
                }
                sh 'docker push satish2121/soap-webservice'    
                }
            }
        }
    }
}