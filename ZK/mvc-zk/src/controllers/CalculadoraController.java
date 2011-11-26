package controllers;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Progressmeter;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;

import domain.Calculadora;

public class CalculadoraController extends GenericForwardComposer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Calculadora calc;
	
	Textbox op1;
	Textbox op2;
	
	Label resultado;
	
	
	public CalculadoraController(){		
	
		this.calc = new Calculadora();
			
	}
	
	private void obtenerOperandos() {
		this.calc.setOp1(Integer.parseInt(op1.getValue()));
		this.calc.setOp2(Integer.parseInt(op2.getValue()));
	}
	
	
	
	public  void onClick$sumar(Event event){
		obtenerOperandos();
		resultado.setValue(this.calc.sumar().toString());
	}

	public void onClick$restar(Event event){
		obtenerOperandos();
		resultado.setValue(this.calc.restar().toString());
	}		
	
	public void onClick$multiplicar(Event event){
		obtenerOperandos();
		resultado.setValue(this.calc.multiplicar().toString());
	}
	
	public void onClick$dividir(Event event){
		obtenerOperandos();
		resultado.setValue(this.calc.dividir().toString());
	
	}

	
}
