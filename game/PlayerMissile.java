import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.lang.*;

public class PlayerMissile extends Missile{
   Handler handler;
   double leftLine,rightLine,midLine;
   private boolean show=false;
   public boolean block=false;
   
   private int startX;
   
   private double deltaX=getTargetX()-getX();
   private double deltaY=getTargetY()-getY();
   private double direction=Math.atan(deltaY/deltaX);
   
   public PlayerMissile(int x,int y,int id,Handler handler,int targetX,int targetY){
      super(x,y,id,handler,targetX,targetY);
	  show=true;
	  startX=x;
	  setVelX(6);
	  setVelY(6);
   }
      
   public void tick(){
		if(show){
				
			setX((int)((double)getX()+((double)getvelX()*Math.cos(direction))));
			setY((int)((double)getY()+((double)getvelY()*Math.sin(direction))));
			deltaX=getTargetX()-getX();
			deltaY=getTargetY()-getY();
			direction=Math.atan(deltaY/deltaX);
			
		}
   }
	
   public void render(Graphics g){
	   if(show){
		g.setColor(Color.blue);
		g.fillRect(x,y,5,15);
	   }
   }
   
   public Rectangle getBounds(){
	   if(!block)return new Rectangle(x,y,5,15);
	   else return new Rectangle(0,0,0,0);
   }
}   