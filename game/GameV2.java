import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.Font;

public class GameV2 extends Canvas implements Runnable,MouseMotionListener, MouseListener {
	
   static final Integer  XSIZE=new Integer (600);
   static final Integer  YSIZE=new Integer (600);
   private Thread thread;
   public static Random rng=new Random();
   private boolean running=false;
   private Handler handler;
   
   public static int level=1;
   private boolean inLevel=false;
   public static int numMissileLeft;
   public static int score=0;
   
   public static int numCity=4;

   private Random rand = new Random();

   public GameV2(){
      handler=new Handler();
      addMouseMotionListener(this);
      addMouseListener(this);
      Window(XSIZE,YSIZE,"Missile Command",this);
      //add things to handler here
      handler.addObject(new Plane(0,50,new String ("plane").hashCode(),handler));
      handler.addObject(new City(XSIZE/5,540,new String ("city").hashCode(),handler));
      handler.addObject(new City(XSIZE/5*2,540,new String ("city").hashCode(),handler));
      handler.addObject(new City(XSIZE/5*3,540,new String ("city").hashCode(),handler));
      handler.addObject(new City(XSIZE/5*4,540,new String ("city").hashCode(),handler));
   }
   public void Window(int width, int height, String title, GameV2 game){//sets up the window
      JFrame frame=new JFrame(title);
   	
      frame.setPreferredSize(new Dimension(width, height));
      frame.setMaximumSize(new Dimension(width, height));
      frame.setMinimumSize(new Dimension(width, height));
   	
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);
      frame.setLocationRelativeTo(null);
      frame.add(game);
      frame.setVisible(true);
      game.start();
   }

   public void mouseClicked(MouseEvent e){
      //adds a player missile to handler and sets where the missile will be going based on the mouse x and y
      handler.addObject(new PlayerMissile(20,545,new String ("playermissile").hashCode(),handler,e.getX(),e.getY()));
      handler.addObject(new Player(0,525,new String ("player").hashCode(),handler,e.getX(),e.getY()));
	  
      
      //sets up where the missile will be going
      /* for(int i=0;i<handler.object.size();i++){
         GameObject tempObject= handler.object.get(i);
         if(tempObject.getID()==new String("player").hashCode()){
            tempObject.setTargetX(e.getX());
            tempObject.setTargetY(e.getY());
         }
      } */
   }
   
   // classes that had to be added for the MouseMotionListener and MouseListener interface
   public void mousePressed(MouseEvent e){}
   public void mouseExited(MouseEvent e){}
   public void mouseEntered(MouseEvent e){}
   public void mouseReleased(MouseEvent e){}
   public void mouseDragged(MouseEvent e){}
   public void mouseMoved(MouseEvent e){}

   public synchronized void start(){ 
      thread=new Thread(this);
      thread.start();
      running=true;
   
   }
	
   public synchronized void stop(){ 
      try{
         thread.join();
         running=false;
      	
      }
      catch(Exception e){
         e.printStackTrace();
      }
   
   }
	
   public void run(){ 
      long lastTime=System.nanoTime();
      double amountOfTicks=64;
      double ns=1000000000/amountOfTicks;
      double delta=0;
      long timer=System.currentTimeMillis();
      int frames=0;
      while(running){
         long now=System.nanoTime();
         delta+=(now-lastTime)/ns;
         lastTime=now;
         while(delta>=1){
            tick();
            delta--;
         }
         if(running)
            render();
         frames++;
      	
         if(System.currentTimeMillis()-timer>1000){
            timer+=1000;
            frames=0;
         }
      }
      stop();
   }
	
   private void tick(){
      //aslong as you have cities the game will continue to play
      if(numCity!=0){
      //sets up the level
         if(!inLevel){
            for(int i=0;i<(level*4)-3;i++){
            //makes the enemy missiles fall from random places from the top of the screan
               int rngValue=rng.nextInt(100);
               int rngY=-1*rng.nextInt(200);
               handler.addObject(new EnemyMissile(rng.nextInt(550)+50,rngY,new String ("enemymissile").hashCode(),handler,rng.nextInt(600),400));
               numMissileLeft++;
            }
            inLevel=true;
         }
         handler.tick();
      
      //sets up the next level
         if(numMissileLeft<=0){
            level++;
            score+=numCity*50;
            inLevel=false;
         }
      //checks if the enemymissile is at the ground and if so blows up
         for(int i=0;i<handler.object.size();i++){
            GameObject tempObject= handler.object.get(i);
            if(tempObject.getID()==new String ("enemymissile").hashCode()){
               if(tempObject.getY()>530){
                  handler.addObject(new Explosion(tempObject.getX(),530,new String ("explosion").hashCode(),handler));
               //when the enemymissile blows up it adds 500 points to score we need to take that points out because the player did not blow up the missile
                  score-=500;
               }
            }
         }
      }
      else{}
   }
	
   private void render(){ 
      BufferStrategy bs= this.getBufferStrategy();
      if(bs==null){
         this.createBufferStrategy(3);
         return;
      }
   
      Graphics g=bs.getDrawGraphics();
      if(numCity!=0){
         g.setColor(Color.black);
         g.fillRect(0,0,600,550);
      
      
         handler.render(g);
         g.setColor(Color.green);
      
         Font font = new Font("Arial",Font.BOLD,18);
         g.setFont(font);
         g.drawString("Score:"+score,200,30);
      
         g.drawString("level:"+level,0,30);
      
         g.dispose();
         bs.show();
      }
   //display the game over screan
      if(numCity==0){
         g.setColor(Color.black);
         g.fillRect(0,0,600,600);
         g.setColor(Color.white);
         g.drawString("Game Over",280,300);
         g.drawString("Score:"+score,280,320);
         g.dispose();
         bs.show();
      }
   }
   public static void main(String args[]){
      new GameV2();
   }
}