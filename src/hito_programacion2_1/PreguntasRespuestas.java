package hito_programacion2_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PreguntasRespuestas {
    public static void main(String[] args) {
        String preguntasFile = "preguntas.txt";
        String respuestasFile = "respuestas.txt";

        try (BufferedReader preguntasReader = new BufferedReader(new FileReader(preguntasFile));
             BufferedReader respuestasReader = new BufferedReader(new FileReader(respuestasFile))) {

            String pregunta;
            String respuesta;
            int puntuacion = 0;

            while ((pregunta = preguntasReader.readLine()) != null && (respuesta = respuestasReader.readLine()) != null) {
                System.out.println(pregunta);
                String respuestaUsuario = obtenerRespuestaUsuario();
                if (respuestaUsuario.equalsIgnoreCase(respuesta)) {
                    System.out.println("¡Respuesta correcta!");
                    puntuacion++;
                } else {
                    System.out.println("Respuesta incorrecta.");
                }
            }

            System.out.println("Puntuación obtenida: " + puntuacion);
        } catch (IOException e) {
            System.err.println("Error al leer los archivos.");
            e.printStackTrace();
        }
    }

    private static String obtenerRespuestaUsuario() throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.print("Tu respuesta: ");
        return reader.readLine();
    }
}
