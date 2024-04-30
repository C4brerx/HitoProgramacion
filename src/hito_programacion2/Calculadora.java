package hito_programacion2;

public class Calculadora {

    // Método de sobrecarga para sumar enteros
    public int sumar(int a, int b) {
        return a + b;
    }

    // Método de sobrecarga para sumar doubles
    public double sumar(double a, double b) {
        return a + b;
    }

    // Método de sobrecarga para sumar tres números enteros
    public int sumar(int a, int b, int c) {
        return a + b + c;
    }

    // Método que vamos a sobreescribir en la clase derivada
    public void saludar() {
        System.out.println("¡Hola Calculadora!");
    }
}
