package domain;

public class Calculadora {
	private Integer op1;
	private Integer op2;
	
	
	
	
	public Calculadora(){
		
	}

	
	public Integer sumar(){
		return op1 + op2;
	}


	public Integer restar(){
		return op1 - op2;
	}


	public Integer dividir(){
		if(op2==0){
			throw new RuntimeException("No se puede Dividir por cero");
		}
		else{return (Integer)op1 / op2;}
		
		
	}

	public Integer multiplicar(){
		return (Integer)op1 * op2;
	}


	public Integer raizCuadrada(){
		return op1*op1;
	}
	public void setOp1(int valor) {
		this.op1 = valor;
		
	}

	public void setOp2(int valor) {
		this.op2 = valor;
		
	}
	
	
}
