import java.util.concurrent.BrokenBarrierException;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    // ТУТ ДОБАВЛЯЛ ДЛЯ Д.З
    @Override
    public void go(Car c) {
        try {
            System.out.println (c.getName () + " начал этап: " + description);
            Thread.sleep (length / c.getSpeed () * 1000);
            MainClass.roadStage.await ();
            System.out.println (c.getName () + " закончил этап: " + description);
            if (this.length == 40) {
                MainClass.finishLine.countDown ();}
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
            }
        }
}
