  pipeline { 
    agent any
    stages {    
      stage ('Analisis de Codigo'){   
        steps {
		script{
			def scannerHome = tool 'sonarqube-scanner';
			withSonarQubeEnv(credentialsId: 'b7dabfdc-19d0-4e2a-ab23-4ed5e17dd9c1') {
			sh "${scannerHome}/bin/sonar-scanner"
			} 
		}
        }        
      }
      
      stage ('Quality Gate'){
	steps {			
		script{
			def qg = waitForQualityGate()
			if (qg.status != 'OK') {
			  error "Pipeline abortado debido a la falla del QG: ${qg.status}"
			}
		}
						
	}
	 
      }
	 
      stage ('Owasp ZAP Analysis'){
		steps {			
			//sleep time: 3, unit: 'MINUTES'
			//build job:  '/prueba-demo1', parameters: [string(name: 'param1', value:'val1')], wait: true   
						
		}
	 
      }
    }
  }
