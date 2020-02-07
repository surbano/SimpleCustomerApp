  pipeline { 
    agent any
    stages {    
      stage ('Analisis de Codigo'){   
        steps {
		script{
			// Se obtiene el nombre del sonarscanner declarado de las variables globales del Jenkins
			def scannerHome = tool 'sonarqube-scanner';
			// Se invoca la ejecución del Sonnar Scanner con el token
			withSonarQubeEnv(credentialsId: 'b7dabfdc-19d0-4e2a-ab23-4ed5e17dd9c1') {
			sh "${scannerHome}/bin/sonar-scanner " +				
			    "-Dsonar.projectKey=${GIT_BRANCH} " +
			    "-Dsonar.projectName=${GIT_BRANCH} " +
			    "-Dsonar.projectVersion=0.0.0 " +
			    "-Dsonar.sources=/var/lib/jenkins/workspace/pipeline-demo/SimpleCustomerApp/src " +
			    "-Dsonar.sourceEncoding=UTF-8"
				
			} 
		}
        }        
      }
	  
	  // Para que este Stage funcione se tiene que configurar previamente un Webhook en el SonnarQube con la instancia del Jenkins: http://18.191.19.80:8080/sonarqube-webhook/
	  stage ('Quality Gates'){
		steps {			
			script {
				// Se define la escucha del Quality Gates ejecutado en el Sonar Scanner
				def qg = waitForQualityGate()
				// Se compara el Status, si no es "OK" muestra un mensaje
				if (qg.status != 'OK') {
					error "Pipeline abortado debido a la falla del QG: ${qg.status}"
					mail to: 'sergioub@gmail.com',
					subject: "Fallo Quality Gate Pipeline: ${currentBuild.fullDisplayName}",
					body: "Revisar: ${env.BUILD_URL}"
				}
				
			}
			
		}
	 
      }
	  
	 
      stage ('Owasp ZAP Analysis'){
		steps {			
			// Se ejecuta la tarea "prueba-demo1" del Jenkins de manera externa la cual contiene el owaszap configurado 
			build job:  '/prueba-demo1', parameters: [string(name: 'param1', value:'val1')], wait: true   
			
		}
	 
      }
    }
  }
