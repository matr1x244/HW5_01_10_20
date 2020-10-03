import java.util.concurrent.BrokenBarrierException;

public class Car implements Runnable {

    private static int CARS_COUNT;

    private static boolean winner; // true или false


    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    // ТУТ ДОБАВЛЯЛ ДЛЯ Д.З ПРОВЕРКА ПОБЕДЫ СИНХРОНИЗАЦИЯ
    public static synchronized void Winner(Car c) {
        if (!winner) {
            System.out.println (c.name + " - Победил");
            winner = true;
        }
    }

    // ТУТ ДОБАВЛЯЛ ДЛЯ Д.З
    @Override
    public void run() {
        try {
            System.out.println (this.name + " готовится");
            Thread.sleep (500 + (int) (Math.random () * 800));
            MainClass.startLine.countDown ();
            System.out.println (this.name + " готов");
            MainClass.startLine.await ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        for (int i = 0; i < race.getStages ().size (); i++) {
            race.getStages ().get (i).go (this);
        }
        Winner (this);
        try {
            MainClass.roadStage.await ();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace ();
        } catch (BrokenBarrierException brokenBarrierException) {
            brokenBarrierException.printStackTrace ();
        }
    }
}
