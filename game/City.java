import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.lang.*;

public class City extends GameObject{
   
   Handler handler;
   boolean show=false;
   public City(int x, int y, int id,Handler handler){
      super(x,y,id);
      this.handler=handler;
   }
   
   public void render(Graphics g){
      if(!show){
         g.setColor(new Color(0,225,225)); // cyan
         g.fillRect(x,y,50,10); //1
         g.fillRect(x+5,y-10,40,10); //2
         g.fillRect(x+10,y-20,30,10); //3
         g.fillRect(x+15,y-30,20,10); //4
      	//cherry on top
         g.setColor(new Color(255,69,0)); //red
         g.fillOval(x+20,y-40,10,10);
      	
      }
   
   }
   public void tick(){
      
   }
   
   public Rectangle getBounds(){
      if(!show)
         return new Rectangle(x,y-30,50,50);
      else 
         return new Rectangle(0,0,0,0);
   }
}