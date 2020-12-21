import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Main extends JFrame implements Updatable{
    public static void main(String[] args) {
        Main instance = new Main();
    }
    private Canvas canvas;
    private Graph graph;

    public Main(){
        super("Ping Checker");
        Cycle cycle = new Cycle(this);
        canvas = new Canvas();
        graph = new Graph(1500, 800,10,20,"Ping in time",Color.cyan);
        this.getContentPane().setPreferredSize(new Dimension(1900,1000));
        this.pack();
        this.add(canvas);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cycle.start();
    }

    @Override
    public void update(int updateCount) {
        Random random = new Random();
        graph.update(random.nextInt(200)+1);
    }

    @Override
    public void draw(Graphics gr) {
        BufferStrategy strategy = canvas.getBufferStrategy();
        if(strategy==null){
            canvas.createBufferStrategy(2);
            return;
        }
        Graphics g = strategy.getDrawGraphics();
        canvas.paint(g);
        graph.draw(g);
        g.dispose();
        strategy.show();
    }
}
