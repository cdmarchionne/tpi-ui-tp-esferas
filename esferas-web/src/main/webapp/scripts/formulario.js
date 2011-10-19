
function validar_campo(nombre){
    var campo=document.getElementById(nombre);
    
    return (validar_contiene_datos(campo) && validar_dato_entero(campo));
}

function validar_contiene_datos(texto){
    if (texto.value.trim().length==0){
       alert("El campo esta vacio");
       texto.focus();
       return false;
    }
    return true;
}

function validar_dato_entero(valor){
	numero = parseInt(valor.value);
	
	if ((isNaN(numero))) {
		alert("Ingrese un valor numerico entero");
		valor.focus();
       return false;
    }
    return true;
}
