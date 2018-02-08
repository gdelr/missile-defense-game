   import java.awt.Graphics;
   import java.awt.Rectangle;
public interface ObjectInterface{
	
	
	public abstract void tick();
    public abstract void render(Graphics g);
	//set hit box for eatch GameObject
    public abstract Rectangle getBounds();
}