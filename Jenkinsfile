  pipeline { 
    agent any
    stages {
    
      stage ('build & SonarQube analysis'){    
        steps {
          script{
            withSonarQubeEnv(credentialsId: 'b7dabfdc-19d0-4e2a-ab23-4ed5e17dd9c1') {
                sh 'mvn clean package sonar:sonar'
            } 
          }        
        }		
      }
	
	  stage("Quality Gate") {
            steps {
              timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
		  }
		}
	  }
	
	
    }
  }
