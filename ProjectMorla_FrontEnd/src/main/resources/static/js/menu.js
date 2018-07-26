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