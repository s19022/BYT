package PoolPatern;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 2000; i++){
            final int id = i;
            new Thread(()-> {
                PrinterPool.getInstance().getAvailablePrinter().print("thread with id " + id);
            }).start();
        }
    }
}
