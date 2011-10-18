
function valida_crear_mapa(){
    return valida_contiene_datos(document.mapa.dimX.length);
//    return valida_dimx();
}

function valida_dimx(){
    //valido el nombre
    if (!valida_contiene_datos(document.mapa.dimX.length)){
       alert("Ingrese una dimension Horizontal");
       document.mapa.dimX.focus();
       return false;
    }
    return true;
}

function valida_contiene_datos(texto){
    if (texto.length==0){
       alert("El campo esta vacio");
       texto.focus();
       return false;
    }
    return true;
}

var letras="abcdefghyjklmnñopqrstuvwxyz";

function tiene_letras(texto){	
	texto = texto.toLowerCase();
	   for(i=0; i<texto.length; i++){
	      if (letras.indexOf(texto.charAt(i),0)!=-1){
	         return true;
	      }
   }
   return false;
}