   import java.awt.Graphics;
   import java.awt.Rectangle;

public abstract class GameObject implements ObjectInterface {
	
   protected int x,y;
   protected int id;
   protected int velX,velY;
   protected int targetX,targetY;
	
   public GameObject (int x, int y,int id){
      this.x=x;
      this.y=y;
      this.id=id;
   
   }

   public void setX(int x){ // Sets the x value
      this.x=x;
   }
	
   public void setY(int y){ // Sets the y value
      this.y=y;
   }

   public int getX(){ // Returns x
      return x;
   }
	
   public int getY(){ // Returns y
      return y;
   }
	
   public void setID(int id){ // Sets ID
      this.id=id;
   }
	
   public int getID(){ // Returns ID
      return id;
   }
	
   public void setVelX(int velX){ // Sets x velocity
      this.velX=velX;
   }
	
   public void setVelY(int velY){ // Sets y velocity
      this.velY=velY;
   }
	
   public int getvelX(){ // Returns x velocity
      return velX;
   }
	
   public int getvelY(){ // Returns y velocity
      return velY;
   }
 
public void setTargetX(int targetX){
	this.targetX=targetX;
}
public void setTargetY(int targetY){
	this.targetY=targetY;

}
public int getTargetX(){
	return targetX;
}
public int getTargetY(){
	return targetY;
}
}
