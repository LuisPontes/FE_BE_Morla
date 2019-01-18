#!/bin/bash

 function compile() { 
    echo "Compile ...[start]" 
    #mvn clean compile assembly:single
	mvn package
    echo "Compile ...[OK]" 
  }  
 function startProject() { 
    echo "Run Project..." 
	java -jar target/MORLA.app-0.0.1-SNAPSHOT.jar
  }  
  function upload(){
  	echo "UpLoad to server... " 
	scp -r target/MORLA.app-0.0.1-SNAPSHOT.jar pi@192.168.1.100:/tmp
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
	
	"up")
		upload
		;;
  *)	
  		echo "Comandos:{ compile[c] | start project[run] | corre tudo (compile e upload)[all] }" 
  		exit 1
		;;
esac
