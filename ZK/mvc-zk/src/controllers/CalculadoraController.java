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
	
	Button sumar ;
	Button resar ;
	Button Dividir ;
	Button Multiplicar ;
	
	
	
	public CalculadoraController(){		
	
		this.calc = new Calculadora();
			
	}
	private void getOperandos() {
		this.calc.setOp1(Integer.parseInt(op1.getText()));
		this.calc.setOp2(Integer.parseInt(op2.getText()));
	}
	
	
	
	public  void onClick$sumar(Event event){
		getOperandos();
		resultado.setValue(this.calc.sumar().toString());
	}

	public void onClick$restar(Event event){
		getOperandos();
		resultado.setValue(this.calc.restar().toString());
	}		
	
	public void onClick$multiplicar(Event event){
		getOperandos();
		resultado.setValue(this.calc.multiplicar().toString());
	}
	
	public void onClick$dividir(Event event){
		getOperandos();
		resultado.setValue(this.calc.dividir().toString());
	
	}

}
