package lectoresescritores;

import java.util.concurrent.locks.*;

public class GestorDBLock {
    private int cantidadDeLectores = 0;
    private int cantidadDeEscritores = 0;

    private boolean hayEscritor = false;
   
    private Lock lock = new ReentrantLock(true);
    private Condition okLeer = lock.newCondition();
    private Condition okEscribir = lock.newCondition();

    public void lectorEntrar(int id) throws InterruptedException {
        lock.lock();

        try {
            while(hayEscritor || cantidadDeEscritores > 0) {
                okLeer.await(); 
            }

            cantidadDeLectores++;
            System.out.println("Lector " + id + " entra en la DB");
        } finally {
          lock.unlock();
        }
    }

    public void lectorSalir(int id) {
        lock.lock();
        
        try {
            System.out.println("Lector " + id + " sale de la DB");
            cantidadDeLectores--;
            
            if (cantidadDeLectores == 0) {
                okEscribir.signal();
            }
        } finally {
            lock.unlock();
        }
    }
    
    public void escritorEntrar(int id) throws InterruptedException {
        lock.lock();

        try {
            cantidadDeEscritores++;

            while(hayEscritor || cantidadDeLectores > 0) {
                okEscribir.await();
            }

            hayEscritor = true;
            
            System.out.println("Escritor " + id + " entra en la DB");
        } finally {
            lock.unlock();
        }
    }

    public void escritorSalir(int id) {
        lock.lock();

        try {
            cantidadDeEscritores--;
            System.out.println("Escritor " + id + " sale de la DB");

            hayEscritor = false;

            if (cantidadDeEscritores > 0) {
                okEscribir.signal();
            } else {
                okLeer.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}
