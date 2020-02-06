  pipeline { 
    agent any
    stages {
    
      stage ('build & SonarQube analysis'){    
        steps {
          	withSonarQubeEnv(credentialsId: 'b7dabfdc-19d0-4e2a-ab23-4ed5e17dd9c1') {
          	sh 'sonar-scanner'
        	} 
        }        
	  }		
    }
  }
