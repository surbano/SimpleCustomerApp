  pipeline { 
    agent any
    stages {
    
      stage ('build & SonarQube analysis'){    
        steps {
          	withSonarQubeEnv('SonarQube Scanner') {
          	sh 'sonar-scanner'
        	} 
        }        
	  }		
    }
  }
