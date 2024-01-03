pipeline {
    agent any
    
    stages {
        stage("Starting"){
            steps {
                echo "Starting with Jenkins Job"
            }
        }
        
        stage('Build') {
            steps {
                sh './gradlew assembleDebug'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew testDebug'
            }
        }

        stage('Publish to AppCenter') {
            steps {
                script {
                    // Add commands to publish to AppCenter
                    // For example, use the AppCenter CLI
                    sh 'appcenter distribute release --app "your-app-id" --token "your-access-token" --file app/build/outputs/apk/debug/app-debug.apk'
                }
            }
        }
    }
}
