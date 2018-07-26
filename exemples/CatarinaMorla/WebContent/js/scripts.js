
/*function para rodar o slide com os botoes do menu*/

var n;
function bodySlide(n) {
	var indic = document.getElementById('indx').childElementCount;
	for (var i = 0; i < indic; i++) {
		document.getElementById('role' + i).setAttribute('class', 'hidden');
	}
	if(n<5){
		document.getElementById('role'+n).setAttribute('class','row');
	}
	if(n != 10){	
		for(var i = 0; i < indic; i++){
			document.getElementById('ind'+i).setAttribute('class','');
			document.getElementById(i).setAttribute('class','item');				
		}
		document.getElementById('ind'+n).setAttribute('class','active');
		document.getElementById(n).setAttribute('class','item active');			
	}	
	
}

/*function para mostrar o conteudo dos projectos*/

var name;
function show(name){			
	
			var stat = $(name).attr("class");			
			if(stat == "hide"){
				$(name).attr("class","");				
			}else{
				$(name).attr("class","hide");
			}
	
}
