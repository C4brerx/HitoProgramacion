package hito_programacion2;

public class Main {
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        CalculadoraAvanzada calcAvanzada = new CalculadoraAvanzada();

        System.out.println("Suma de enteros: " + calc.sumar(3, 5)); // Salida: 8
        System.out.println("Suma de doubles: " + calc.sumar(3.5, 2.5)); // Salida: 6.0

        calc.saludar(); // Salida: ¡Hola desde la clase base!
        calcAvanzada.saludar(); // Salida: ¡Hola desde derivada!
    }
}
