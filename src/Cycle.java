public class Cycle{
    private boolean isRunning;
    private Updatable receiver;

    public Cycle(Updatable receiver) {
        isRunning=true;
        this.receiver = receiver;
    }


    public void run() {
        //initialization
        long lastMilis=System.currentTimeMillis();
        int updatesCounter=0;
        //Main loop
        while(isRunning){
           //Drawing
            receiver.draw(null);
            //Second processing
            if(System.currentTimeMillis()-lastMilis>1000){
                System.out.println("Updates counter: "+updatesCounter+" Ping: ");
                lastMilis+=1000;
                receiver.update(updatesCounter);
                updatesCounter++;
            }
        }

    }
    public void start(){
        isRunning=true;
        run();

    }

    public void stop(){
        isRunning=false;
    }
}
