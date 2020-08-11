#!/usr/bin/env groovy

pipeline {
    agent {
    kubernetes {
      yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    walle: walle
spec:
  containers:
  - name: docker
    image: docker:18.05-dind
    securityContext:
        privileged: true
  - name: java
    image: java
    """
        }
    }

    environment {
        registry = "nandhanido/walle"
        registryCredential = 'dockerhub'
        dockerImage = ""
    }

    stages {
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
