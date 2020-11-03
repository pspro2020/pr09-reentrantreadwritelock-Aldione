import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Almacen {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    ArrayList<Integer> stock = new ArrayList<>(Arrays.asList(1, 2, 1, 1, 2, 1, 3));

    public void addProduct(int productId){
        writeLock.lock();
        try{
            stock.add(productId);
            System.out.printf("%s The %s added the product %s on the stock\n", DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()), Thread.currentThread().getName(), productId);
        }finally {
            writeLock.unlock();
        }
    }

    public void checkStock(int productId){
        int stock;
        readLock.lock();
        try{
            stock = countStock(productId);
            System.out.printf("%s The %s counted the stock of %s and the total is %s\n", DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()), Thread.currentThread().getName(), productId, stock);
        }finally {
            readLock.unlock();
        }
    }

    private int countStock(int productId) {
        int totalStock = 0;

        for (Integer integer : stock) {
            if (integer == productId) {
                totalStock++;
            }
        }
        return totalStock;
    }

}
