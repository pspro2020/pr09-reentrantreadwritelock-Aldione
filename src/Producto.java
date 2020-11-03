import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Producto implements Runnable{


    Almacen almacen;

    public Producto(Almacen almacen){
        this.almacen = almacen;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            try{
                TimeUnit.SECONDS.sleep(2);
                almacen.addProduct(ThreadLocalRandom.current().nextInt(3)+1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
