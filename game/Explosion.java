import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Explosion extends GameObject{
   Handler handler;
   private int time=0;
   private boolean show=false;
   public Explosion(int x,int y,int id,Handler handler){
      super(x,y,id);
      this.handler=handler;
   }
   public void tick(){
	   if(time<=100){
		time++;
	   }
	   collison();
   }
	
   public void render(Graphics g){
	   if(time<=100){
			g.setColor(Color.white);
			g.fillOval(x-20,y-20,40,40);
	   }
   }
   
   public Rectangle getBounds(){
	   if(!show)return new Rectangle(x-20,y-20,40,40);
	   else{
		   return new Rectangle(0,0,0,0);
	   }
   }
   
   public void collison(){
	   for(int i=0;i<handler.object.size();i++){
		   GameObject tempObject=handler.object.get(i);
		    if(tempObject.getID()==new String ("enemymissile").hashCode()){
			   if(getBounds().intersects(tempObject.getBounds())){
				   show=true;
				   handler.removeObject(handler.object.get(i));
				   handler.addObject(new Explosion(x,y,new String ("explosion").hashCode(),handler));
				   GameV2.numMissileLeft--;
				   GameV2.score+=500;
			   }
		   } 
		   if(tempObject.getID()==new String ("explosion").hashCode()&&time>100){
			   handler.removeObject(handler.object.get(i));
		   }
		   if(tempObject.getID()==new String ("city").hashCode()){
			   if(getBounds().intersects(tempObject.getBounds())){
				   handler.removeObject(handler.object.get(i));
				   handler.addObject(new Explosion(x,y,new String ("explosion").hashCode(),handler));
				   GameV2.numMissileLeft--;
				   GameV2.numCity--;
			   }
		   }
	   }  
   }
}