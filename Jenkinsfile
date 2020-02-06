  pipeline { 
    agent any
    stages {
    
      stage ('build & SonarQube analysis'){    
	tools {
        	sonarQube 'SonarQube Scanner 2.11'
	}
        steps {
          	withSonarQubeEnv('SonarQube Scanner') {
          	sh 'sonar-scanner'
        	} 
        }        
	  }		
    }
  }
