package es.deusto.prog3.cap00;

import java.util.ArrayList;

import javax.swing.JPanel;

public class EjemploInterfaz {
	public static void main(String[] args) {
		A miA;
		miA = new A();
		miA = new B();
		// Object o = new ... ();
		SupCurvaCerrada s = new Circunferencia();
		ArrayList<SupCurvaCerrada> l = new ArrayList<>();
	}
}

class A {
	int i;
	void m() {
		// c1
	}
}

class B extends A {
	long nuevo;
	// int i
	// m()  ...  c1
	void m() { // c2
	}
	void m2() { }
}

interface Dibujable {
	void dibujar( JPanel p );
}

interface SupCurvaCerrada {
	double area();
	double perimetro();
	void escalar( double escala );
}

interface SCCEscalableXY extends SupCurvaCerrada {
	void escalar( double x, double y );
}

class Circunferencia implements SupCurvaCerrada {
	double r;
	// ...

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double perimetro() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void escalar(double escala) {
		// TODO Auto-generated method stub
		
	}
	
}
