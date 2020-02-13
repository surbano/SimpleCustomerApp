// Se define la variable que almacerá la carga del Jenkinsfile que ejecutará el Owaspzap
def pipOwaspzap
// Se define la variable donde se almacenará el resultado del Quality Gates
def varOwaspzap
// Se define la variable donde se almacenará el resultado de la ejecución del OWASPZAP
def resOwaszap
pipeline { 
    agent any

    stages {    
      stage ('Analisis de Codigo'){   
        steps {
		script{
			echo 'INICIO_ANALISIS_DE_CODIGO'
			// Se obtiene el nombre del sonarscanner declarado de las variables globales del Jenkins
			def scannerHome = tool 'sonarqube-scanner';
			// Se invoca la ejecución del Sonnar Scanner con el token y sus propiedades
			withSonarQubeEnv(credentialsId: 'b7dabfdc-19d0-4e2a-ab23-4ed5e17dd9c1') {
			sh "${scannerHome}/bin/sonar-scanner " +				
			    "-Dsonar.projectKey=Valaxy " +
			    "-Dsonar.projectName=ValaxyDemo " +
			    "-Dsonar.projectVersion=1.0 " +
		 	    "-Dsonar.java.binaries=. " +
			    "-Dsonar.sources=/var/lib/jenkins/workspace/pipeline-demo/SimpleCustomerApp/src " +
			    "-Dsonar.sourceEncoding=UTF-8"
				
			}
		}
		echo 'ANALISIS_DE_CODIGO_HECHO'
        }        
      }
	  
	  // Para que este Stage funcione se tiene que configurar previamente un Webhook en el SonnarQube con la instancia del Jenkins: http://18.191.19.80:8080/sonarqube-webhook/
	  stage ('Quality Gates'){
		steps {			
			script {
				// Se define la escucha del Quality Gates ejecutado en el Sonar Scanner
				def qg = waitForQualityGate()
				// Se almacena el estado del Quality Gates en la variable global que se enviará al JenkinsFile del OZAP
				varOwaspzap = qg.status
				// Se compara el Status, si no es "OK" muestra un mensaje
				if (qg.status != 'OK') {
					error "Pipeline abortado debido a la falla del QG: ${qg.status}"
					mail to: 'sergioub@gmail.com',
					subject: "Fallo Quality Gate Pipeline: ${currentBuild.fullDisplayName}",
					body: "Revisar: ${env.BUILD_URL}"
				}
				
			}
			echo 'QUALITY_GATE_COMPROBADO'
		}
	 
      }
      
      stage ('Ejecutando Jenkinsfile OWASPZAP'){
	      
		steps {
			script {
				// Se instancia la carga del jenkinsfile que ejecutará el Owaspzap
				pipOwaspzap = load "jfile-owaspzap"	
				// Se invoca al método que se encuentra en el jenkisfile que ejecutará el Owaspzap enviándole la variable del resultado
				if(varOwaspzap != null) {
					resOwaszap =pipOwaspzap.someMethod(varOwaspzap)
				}
			}
			echo resOwaszap
		}
	 
      }
      
      stage ('Ejecutando JMETER'){
		    
	      steps {
		      script {
			      // Se valida que el resultado de la ejecución del OWASPZAP se haya ejecutado
			      if(resOwaszap != null){					
				      	// Se invoca la tarea donde se encuentra la ejecución del JMETER
					build job:  '/prueba-jmeter', parameters: [string(name: 'param1', value:'val1')], wait: true
				      	echo 'JMETER_EJECUTADO'
			      }
		      
		      }	
	      }
      }
	    
      stage ('Reporte Físico JMETER'){
		    
	      steps {
		      sh '
			    cd /opt/JMETER
			    cd bin
			    sh jmeter.sh -Jjmeter.save.saveservice.output_format=csv -n -t /opt/grabaciones/testphp.vulnweb.com.jmx -l /opt/grabaciones/$(date +%y%m%d%H%M%S%N).csv -e -o /opt/grabaciones/$(date +%y%m%d%H%M%S%N)
			'	
	      }
      }
	    
	      	      
      }
  
}



