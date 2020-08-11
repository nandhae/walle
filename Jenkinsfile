#!/usr/bin/env groovy

pipeline {
    agent any
    environment {
        registry = "nandhanido/walle"
        registryCredential = 'dockerhub'
        dockerImage = ""
    }

    stages {
        stage('gradle build') {
            steps {
              container('docker') {
                script {
                    sh './gradlew clean build'
                }
              }
            }
        }

        stage('docker build') {
            steps {
              container('docker') {
                script {
                    def imageTag = "$registry:latest"
                    dockerImage = docker.build imageTag
                }
              }
            }
        }

        stage('docker publish') {
            steps {
              container('docker') {
                script {
                    docker.withRegistry("", registryCredential) {
                        dockerImage.push()
                    }
                }
              }
            }
        }

        stage('Deploy App') {
             steps {
                script {
                    kubernetesDeploy(configs: "deployment.yaml", kubeconfigId: "mykubeconfig")
                }
            }
        }
    }
}
