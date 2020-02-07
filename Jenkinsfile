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
	  
	  
	  stage ('Quality Gates'){
		steps {			
			script {
				def qg = waitForQualityGate()
				if (qg.status != 'OK') {
					error "Pipeline abortado debido a la falla del QG: ${qg.status}"
					mail to: 'sergio.urbano@iteraprocess.com',
					subject: "Fallo Quality Gate Pipeline: ${currentBuild.fullDisplayName}",
					body: "Revisar: ${env.BUILD_URL}"
				}
				
			}
			
		}
	 
      }
	  
	 
      stage ('Owasp ZAP Analysis'){
		steps {			
			timeout(3) {
				//build job:  '/prueba-demo1', parameters: [string(name: 'param1', value:'val1')], wait: true   
				
			}
			
		}
	 
      }
    }
  }
