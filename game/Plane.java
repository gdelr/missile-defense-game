import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Plane extends GameObject{
   Handler handler;
   
   private boolean show=false;
   public Plane(int x,int y,int id,Handler handler){
      super(x,y,id);
      this.handler=handler;
      velX=3;
   }
   public void tick(){
      x+=velX;
      y+=velY;
	  collison();
	  if(GameV2.rng.nextInt(100)==1){
		  if(GameV2.rng.nextInt(100)<=20){
			handler.addObject(new EnemyMissile(x,y,new String ("enemymissile").hashCode(),handler,x,400));
		  }
	  }
   }
	
   public void render(Graphics g){
	   if(!show){
			g.setColor(new Color(232,26,3));
			//boddy
			g.fillRect(x,y,20,10);
			//fin
			g.fillRect(x-4,y,4,8);
			g.fillRect(x-10,y-2,8,8);
			g.fillRect(x-10,y-3,6,1);
			g.fillRect(x-10,y-5,1,2);
			//bot wing
			g.fillRect(x+4,y,12,12);
			g.fillRect(x+4,y,10,14);
			g.fillRect(x+4,y,8,16);
			g.fillRect(x+4,y,6,18);
			//top wing
			g.fillRect(x+8,y-6,2,2);
			g.fillRect(x+8,y-4,4,2);
			g.fillRect(x+8,y-2,6,2);
			g.fillRect(x+8,y,8,2);
			//nose cone
			g.fillRect(x,y+2,22,2);
			g.fillRect(x,y+4,24,4);
			g.fillRect(x,y+8,28,2);
      
			if(x>600)x=0;
	   }
   }
   
   public Rectangle getBounds(){
	   if(!show)return new Rectangle(x-10,y-6,30,15);
	   else return new Rectangle(0,0,0,0);
   }
   
   public void collison(){
	   for(int i=0;i<handler.object.size();i++){
		   GameObject tempObject=handler.object.get(i);
		    if(tempObject.getID()==new String ("explosion").hashCode()){
			   if(getBounds().intersects(tempObject.getBounds())){
				   show=true;
				   handler.removeObject(handler.object.get(i));
				   handler.addObject(new Explosion(x,y,new String ("explosion").hashCode(),handler));
				   GameV2.score+=1000;
			   }
		   } 
	   }  
   }
}