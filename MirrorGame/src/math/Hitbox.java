package math;

import java.util.Vector;

public class Hitbox {
	
	private double l;
	private double r;
	private double t;
	private double b;
	
	public Hitbox(double x, double y, double width, double height){
		l = x;
		r = x + width;
		t = y;
		b = y + height;
	}
	
	/*public Vector<Double> getCollisionVector(Hitbox other){
		Vector<Double> vector = new Vector<Double>(0, 0);
		boolean tB = between(t, other.t, other.b);
		boolean bB = between(b, other.t, other.b);
		boolean lB = between(l, other.l, other.r);
		boolean rB = between(r, other.l, other.r);
		if((tB || bB) && lB)
			vector.set(0, element)
		return vector;
	}*/
	
	public double[] getMTV(Hitbox other){ // Minimum Translation Vector
		// This is a modification for rectangles of the Separating Axis Theorem Algorithm.
		
		double[] mtv = {0.0, 0.0};
		
		// Total width - projected length = projected overlap
		double xOverlap = (this.r - this.l + other.r - other.l) - Math.max(other.r - this.l, this.r - other.l);
		double yOverlap = (this.b - this.t + other.b - other.t) - Math.max(other.b - this.t, this.b - other.t);
		
		if(xOverlap > 0 && yOverlap > 0){
			if(xOverlap < yOverlap)
				mtv[0] = xOverlap;
			else
				mtv[1] = yOverlap;
		}
		
		return mtv;
		//return (between(t, other.t, other.b) || between(b, other.t, other.b)) && (between(l, other.l, other.r) || between(r, other.l, other.r));
	}

	private boolean between(double a, double one, double two){
		return Math.signum(one-a) != Math.signum(two-a);
	}
}
