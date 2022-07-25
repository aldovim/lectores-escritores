
package lectoresescritores;

import java.util.ArrayList;
import java.util.List;

public class LectoresEscritores {
    static int CANTIDAD_ESCRITORES = 2;
    static int CANTIDAD_LECTORES = 5;

    public static void main(String[] args) {
       GestorDBLock gestor = new GestorDBLock();
       List<Escritor> escritores = new ArrayList<Escritor>();
       List<Lector> lectores = new ArrayList<Lector>();
       
       for (int i = 0; i < CANTIDAD_ESCRITORES; i++) {
           escritores.add(new Escritor(gestor, i));
       }

       for (int i = 0; i < CANTIDAD_LECTORES; i++) {
           lectores.add(new Lector(gestor, i));
       }
       
       for (int i = 0; i < CANTIDAD_ESCRITORES; i++) {
           escritores.get(i).start();
       }
       
       for (int i = 0; i < CANTIDAD_LECTORES; i++) {
           lectores.get(i).start();
       }
    }
}
