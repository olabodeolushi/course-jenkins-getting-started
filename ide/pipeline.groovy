pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/olabodeolushi/jgsu-spring-petclinic.git', branch: 'main'
            }
        }
        stage('Build') {
            //test
            steps {
                sh './mvnw clean package'
            }
            
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
