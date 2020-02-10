node { 
	agent none
	
	//echo 'mariko'
    stages {    	  
	 
      stage ('Owasp ZAP Analysis'){
	      //steps {		
		echo 'mariko'
          // Se ejecuta la tarea "prueba-demo1" del Jenkins de manera externa la cual contiene el owaszap configurado 
          //build job:  '/prueba-demo1', parameters: [string(name: 'param1', value:'val1')], wait: true   

        //}
	 
      }
    }
  }
