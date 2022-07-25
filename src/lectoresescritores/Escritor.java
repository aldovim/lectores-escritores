package lectoresescritores;

import java.util.Random;

public class Escritor extends Thread {
    private static Random R = new Random();
    private GestorDBLock gestor;
    private int id;
    
    public Escritor(GestorDBLock gestor, int id) {
        this.gestor = gestor;
        this.id = id;
    }

    @Override
    public void run(){
        while(true) {
            try {
                this.gestor.escritorEntrar(this.id);
                sleep(R.nextInt(200));
                this.gestor.escritorSalir(this.id);
                sleep(R.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
