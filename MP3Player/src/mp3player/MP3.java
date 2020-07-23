/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import javazoom.jl.player.Player;

public class MP3 {
    private String filename;
    private Player player;
    String title;   
    public long pauseLocation;
    public long songTotalLength;
    public String fileLocation;
    FileInputStream FIS;
    BufferedInputStream BIS;
    
    public MP3(String filename){
        this.filename=filename;
    }
    
    public void close(){   // closing song...
        if(player!=null)
            player.close();
          pauseLocation = 0;
          songTotalLength = 0;
    }
    
    public void pause(){  // pausing song...
      if(player != null){
          try {
              pauseLocation = FIS.available();
              player.close();
          } catch (IOException ex) {
          }        
      }
  }
    
    public void play(){   // play the song...
        try{
           FIS=new FileInputStream(filename);
           BIS=new BufferedInputStream(FIS);
           
           player = new Player(BIS);
           
          songTotalLength = FIS.available(); 
          
          fileLocation = filename+"";
        }catch(Exception e){
            System.out.println("Problem loading File: "+filename);
            System.out.println(e);
        }       
        new Thread(){ // for running the song.....
            public void run(){
            try{
            player.play();
        }catch(Exception e){
            System.out.println(e);
        }
        }
        }.start();        
    }
    
    
    public void resume(){  // resuming the song...
        try{
           FIS=new FileInputStream(filename);
           BIS=new BufferedInputStream(FIS);
           
           player = new Player(BIS);
           FIS.skip(songTotalLength-pauseLocation);
          
        }catch(Exception e){
            System.out.println("Problem loading File: "+filename);
            System.out.println(e);
        }        
        new Thread(){
            public void run(){
            try{
            player.play();
        }catch(Exception e){
            System.out.println(e);
        }
        }
        }.start();
        
    }
      
}
