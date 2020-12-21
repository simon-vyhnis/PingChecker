import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Graph implements Updatable{
    private int width;
    private int height;
    private int x;
    private int y;

    private List<Integer> history = new ArrayList<>();
    private int max=0;
    private double pixelPerPoint;
    private double pixelsPerRecord;
    private int purgeIndex=1;

    private String title;
    private Color color;

    public Graph(int width, int height, int x, int y, String title, Color color) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y+10;
        this.title = title;
        this.color = color;
    }
    @Override
    public void update(int points){
        history.add(points);

        pixelsPerRecord = (double)width / history.size();
        if(pixelsPerRecord < 1){
            while (pixelsPerRecord * purgeIndex < 1){
                purgeIndex++;
                System.out.println(purgeIndex+" , "+history.size()+" , "+pixelsPerRecord);
            }
            pixelsPerRecord = 1;
        }

        if(points>max){
            while(max<points || max%50!= 0){
                max++;
            }
            pixelPerPoint = (height -1.0)/(double)max;
        }
    }
    @Override
    public void draw(Graphics g){
        g.setColor(color);
        int posX=1;
        for (int i = 0; i< history.size(); i+=purgeIndex){
            int rectHeight = (int)(history.get(i)  * pixelPerPoint);
            int rectWidth = (int) pixelsPerRecord;
            g.fillRect(x+posX,y+height-rectHeight, rectWidth, rectHeight);
            posX += pixelsPerRecord;
        }

        g.setColor(Color.BLACK);
        g.drawString(title,x,y-10);
        g.drawRect(x,y, width, height);

        g.setColor(Color.GRAY);
        g.drawLine(x,y+(height/4),width,y+(height/4));
        g.drawLine(x,y+2*(height/4),width,y+2*(height/4));
        g.drawLine(x,y+3*(height/4),width,y+3*(height/4));

        g.drawString(max+"ms", x+width+1,y+10);
        g.drawString(max/4*3+"ms", x+width+1,y+(height/4)+5);
        g.drawString(max/2+"ms", x+width+1,y+2*(height/4)+5);
        g.drawString(max/4+"ms", x+width+1,y+3*(height/4)+5);
        g.drawString(0+"ms", x+width+1,y+height);
    }
}
