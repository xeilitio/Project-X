package packageGame;

public class TimerRun implements Runnable{
   
    int counter = 0;
    Perso perso;
    int time;
    int timeR;
    int timeF;
    boolean running = true;
    
    public void run() {
            while(running){
                try {
                    Thread.sleep(1);
                } catch (Exception e) {}
                counter++;
                if (counter >= 1) {
                    time++;
                    timeR++;
                    timeF++;
                    counter=0;
                }
            }
            System.out.println("thread shutdown");
        }
    
    public int GetTime(){
        return time;
    }
    public int GetTimeR(){
        return timeR;
    }
    public int GetTimeF(){
        return timeF;
    }

    public void shutdown() {
        running = false;
    }
    public void restart(){
        running = true;
    }
    
    public void reset(){
        time = 0;
    }
}

