#!/bin/bash

 function compile() { 
    echo "Compile ...[start]" 
    #mvn clean compile assembly:single
	mvn package
    echo "Compile ...[OK]" 
  }  
 function startProject() { 
    echo "Run Project..." 
    java -jar ProjectMorla_FrontEnd-0.0.1-SNAPSHOT.jar
  }  



case "$1" in  
  "c")
		compile
		;; 
  "run")
  		startProject
		;; 

  "all") 
    	compile 
    	startProject 
  		;; 
  *)	
  		echo "Comandos:{ compile[c] | start project[run] | corre tudo (compile e upload)[all] }" 
  		exit 1
		;;
esac