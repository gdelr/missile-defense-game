import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
   ArrayList<GameObject> object=new ArrayList<GameObject>();
	
   public void tick(){ // Moves all the gameObjects
      for(int i=0;i<object.size();i++){
         GameObject tempObject=object.get(i);
         tempObject.tick();
      }
   	
   }
	
   public void render(Graphics g){ // draws everything
      for (int i=0;i<object.size();i++){
         GameObject tempObject=object.get(i);
         tempObject.render(g);
      }
   	
   }
	
   public void addObject(GameObject object){ // Adds object
      this.object.add(object);
   }
	
   public void removeObject(GameObject object){ // Removes object
      this.object.remove(object);
   }
   
   public GameObject get(int index){
      return object.get(index);
   }
	

}
