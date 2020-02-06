  pipeline { 
    stages {
    
      stage ('Sonar Scanner'){
      
          withSonarQubeEnv(credentialsId: 'b7dabfdc-19d0-4e2a-ab23-4ed5e17dd9c1') {
              // some block
          }
          
        echo 'Sonar Scanner'
      }
      
      stage ('Esperando a CD'){
        echo 'Esperando a CD'
      }
    
    }
		
	}


