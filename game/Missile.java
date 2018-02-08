import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public abstract class Missile extends GameObject{
   Handler handler;
	int targetX;
	int targetY;
   public Missile(int x,int y,int id,Handler handler,int targetX,int targetY){
      super(x,y,id);
	  setTargetX(targetX);
	  setTargetY(targetY);
      this.handler=handler;
      velX=3;
	  velY=3;
   }
   public abstract void tick();
   public abstract void render(Graphics g);
   
}