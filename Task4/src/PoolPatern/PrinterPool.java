package PoolPatern;

public class PrinterPool {
    private static final int NUMBER_OF_PRINTERS = 100;

    private static PrinterPool instance;

    private Printer[] pool;

    private PrinterPool(){
        pool = new Printer[NUMBER_OF_PRINTERS];
        fillPool();
    }

    public synchronized Printer getAvailablePrinter(){
        while (true){
            for (Printer printer : pool){
                if (!printer.isAvailable()) continue;
                printer.setAvailable(false);
                return printer;
            }
        }
    }


    private void fillPool(){
        for (int i = 0; i < NUMBER_OF_PRINTERS; i++){
            pool[i] = new Printer();
        }
    }

    public static synchronized PrinterPool getInstance(){
        if (instance == null) instance = new PrinterPool();
        return instance;
    }

}
