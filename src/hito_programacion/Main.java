package hito_programacion;

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Perro perro = new Perro();

        animal.sonido(); // Salida: El animal hace un sonido
        perro.sonido(); // Salida: El perro ladra
    }
}
