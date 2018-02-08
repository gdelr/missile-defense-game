import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.lang.*;

public class Player extends GameObject{
   Handler handler;
   
   private boolean show=false;
   public Player(int x,int y,int id,Handler handler,int xx,int yy){
      super(x,y,id);
      this.handler=handler;
      velX=3;
	  setTargetX(xx);
	  setTargetY(yy);
   }
   public void tick(){
	   collison();
   }
	
   public void render(Graphics g){
	   if(!show){
		g.setColor(Color.red);
		g.fillOval(x,y,40,40);
		g.setColor(new Color(185,172,3));
		g.fillRect(0,550,600,60);
		g.setColor(Color.white);
		g.fillRect(getTargetX(),getTargetY(),5,5);
	   }
   }
   
   
   public Rectangle getBounds(){
	   if(!show) return new Rectangle(getTargetX(),getTargetY(),5,5);
	   else return new Rectangle(0,0,0,0);
   }
   
   public void collison(){
	   for(int i=0;i<handler.object.size();i++){
		   GameObject tempObject=handler.object.get(i);
		    if(tempObject.getID()==new String ("playermissile").hashCode()){
			   if(getBounds().intersects(tempObject.getBounds())){
				   show=true;
				   handler.removeObject(handler.object.get(i));
				   handler.addObject(new Explosion(getTargetX(),getTargetY(),new String ("explosion").hashCode(),handler));
			   }
		   } 
	   }  
   }
   
}