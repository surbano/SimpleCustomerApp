
The accepted answer wouldn't fail the stage or even mark it as unstable. It is now possible to fail a stage, continue the execution of the pipeline and choose the result of the build:

pipeline {
    agent any
    stages {
        stage('1') {
            steps {
                sh 'exit 0'
            }
        }
        stage('2') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh "exit 1"
                }
            }
        }
        stage('3') {
            steps {
                sh 'exit 0'
            }
        }
    }
}
