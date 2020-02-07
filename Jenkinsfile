  pipeline { 
    agent any
    stages {
    
      stage ('build & SonarQube analysis'){    
		agent any
        steps {
		script{
			def scannerHome = tool 'sonarqube-scanner';
			withSonarQubeEnv(credentialsId: 'b7dabfdc-19d0-4e2a-ab23-4ed5e17dd9c1') {
			sh "${scannerHome}/bin/sonar-scanner"
			} 
		}
        }        
	  }
	 
	 stage ('Owasp ZAP Analysis'){
		agent any
		steps {
		//script{
			//def zapHome = tool 'ZAP_2.9.0';
			//startZap(host: "localhost", port: 5555, timeout:500, zapHome: "/opt/ZAP/", allowedHosts:['http://testphp.vulnweb.com/'])
       
			//sh '''
            //     export ARCHERY_HOST=http://testphp.vulnweb.com/
            //         bash ${zapHome}/zapscan.sh
            //      '''
			
			build job:  '/prueba-demo1', propagate: true, wait: true
        //}      
		}
	 
	 }
    }
  }
