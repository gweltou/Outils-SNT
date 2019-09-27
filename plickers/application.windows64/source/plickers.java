import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class plickers extends PApplet {

int sq_width;
int sq_height;
PFont font;
String bin;

int niverenn;

int lastClick = 0;


public void setup() {
  
  frameRate(120);
  sq_width = width/5;
  sq_height = height/5;
  niverenn = 0;
  noStroke();
  textSize(100);
  textAlign(CENTER, CENTER);
  noLoop();
}

public void draw() {
  background(255);
  bin = binary(niverenn, 25);
  fill(0);
  for (int i=0; i<25; i++) {
    if (bin.charAt(i) == '1') {
      rect((i%5)*sq_width, (i/5)*sq_height, sq_width, sq_height);
      //println((i%5)*sq_width, (i/5)*sq_height);
    }
  }
  fill(127);
  text(niverenn, width/2, (height/2)-12);
  niverenn += 1;
}

public void mouseClicked() {
  if (millis() - lastClick < 200) {
    loop();
  } else {
    noLoop();
    redraw();
  }
  lastClick = millis();
}

public void doubleClicked(){
  loop();
}
  public void settings() {  size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "plickers" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
