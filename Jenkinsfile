
pipeline {
    agent any

    triggers {
        pollSCM('H/2 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Source code checked out from Git'
            }
        }

        stage('Run Unit Tests') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/findit-1.0.0.jar',
                                 fingerprint: true
            }
        }
    }
}


