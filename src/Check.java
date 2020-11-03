import java.util.concurrent.TimeUnit;

public class Check implements Runnable{



    Almacen almacen;
    private final int productId;

    public Check(Almacen almacen, int productId){
        this.almacen = almacen;
        this.productId = productId;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            try{
                TimeUnit.MILLISECONDS.sleep(500);
                almacen.checkStock(productId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
