package lectoresescritores;

import java.util.Random;

public class Lector extends Thread {
    private static Random R = new Random();
    private GestorDBLock gestor;
    private int id;
    
    public Lector(GestorDBLock gestor, int id) {
        this.gestor = gestor;
        this.id = id;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                this.gestor.lectorEntrar(this.id);
                sleep(R.nextInt(200));
                this.gestor.lectorSalir(this.id);
                sleep(R.nextInt(500));
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
