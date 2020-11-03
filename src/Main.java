import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        Almacen almacen = new Almacen();
        Thread[] check = new Thread[3];

        for (int i = 0; i < 3; i++) {
            check[i] = new Thread(new Check(almacen, i+1),"cheked"+(i+1));
        }

        Thread producto = new Thread(new Producto(almacen),"refiller");
        producto.start();

        try{
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Thread thread : check) {
            thread.start();
        }


    }

}
