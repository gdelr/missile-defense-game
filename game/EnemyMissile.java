import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.lang.*;

public class EnemyMissile extends Missile{
   Handler handler;
   
   private boolean show=false;
   int time=0;
   public EnemyMissile(int x,int y,int id,Handler handler,int targetX,int targetY){
      super(x,y,id,handler,targetX,targetY);
	  velY=1;
   }
      
   public void tick(){
	   y+=velY;
   }
	
   public void render(Graphics g){
	   if(!show){
		g.setColor(Color.red);
		g.fillRect(x,y,5,15);
	   }
   }
   public Rectangle getBounds(){
	   if(!show)return new Rectangle(x,y,5,15);
	   else return new Rectangle(0,0,0,0);
   }
   
   
}   