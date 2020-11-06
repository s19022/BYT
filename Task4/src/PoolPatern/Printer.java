package PoolPatern;

public class Printer {
    private static int counter = 1;

    private final int id;

    private boolean available;

    public Printer(){
        id = counter++;
        available = true;
    }

    public void setAvailable(boolean newValue){
        if (!available) return;
        available = newValue;
    }

    public boolean isAvailable() {
        return available;
    }

    public void print(String message){
        System.out.println("Message: " + message + "; printed by printer with id " + id);
        try{
            Thread.sleep((int)(Math.random() * 1400 + 100));      // to imitate working process
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }

        available = true;

    }
}
