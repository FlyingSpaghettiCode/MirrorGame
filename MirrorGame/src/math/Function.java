package math;

import java.util.ArrayList;

public class Function {
	
	private Action action;
	private double c;
	
	public Function(Action action, double c){
		this.action = action;
		this.c = c;
	}
	
	public double execute(double x){
		if(action == Action.ADD)
			return x + c;
		else if(action == Action.MULTIPLY)
			return x * c;
		else if(action == Action.DIVIDE)
			return x / c;
		else if(action == Action.POWER)
			return Math.signum(x) * Math.pow(Math.abs(x), c);
		else if(action == Action.SINE)
			return Math.sin(x);
		else if(action == Action.COSINE)
			return Math.cos(x);
		else if(action == Action.TANGENT)
			return Math.tan(x);
		else
			return x;
	}

}
