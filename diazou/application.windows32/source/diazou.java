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

public class diazou extends PApplet {

int base = 10;
int number = 0;
int[] positions = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
String symbols = "0123456789ABCDEFGHI";

int SQ_SIZE = 30;
int PADDING = 16;

boolean ten = false;



public void setup() {
  
  textSize(50);
  stroke(0);
}

public void draw() {
  background(255);
  for (int i=0; i<positions.length; i++) {
    for (int h=0; h<base-1; h++) {
      if (positions[i] <= h)
        fill(255);
      else
        fill(0);
      strokeWeight(4);
      stroke(180);
      rect(width-SQ_SIZE-50-i*(SQ_SIZE+PADDING), height-90-h*SQ_SIZE, SQ_SIZE, SQ_SIZE);
      fill(0);
      text(symbols.charAt(positions[i]),
           width-SQ_SIZE-51-i*(SQ_SIZE+PADDING),
           height-PADDING);
    }
  }
  fill(0);
  text(number, 10, 50);
  fill(16, 180, 60);
  text("Diaz", width-PADDING-200, 50);
  text(base, width-PADDING-70, 50);
}

public void updatePositions() {
  int remainder = number;
  for (int i=positions.length-1; i>=0; i--) {
    int n = remainder / PApplet.parseInt(pow(base, i));
    positions[i] = n;
    if (n >= 1) {
      remainder -= n*pow(base, i);
    }
  }
}

public void mouseClicked(MouseEvent e) {
  if (e.getButton() == LEFT) {
    number++;
  } else {
    number = max(0, number-1);
  }
  updatePositions();
}

public void mouseDragged() {
  number += (pmouseX-mouseX)/2;
  number = constrain(number, 0, (int) pow(2, 16)-1);
  updatePositions();
}

public void keyPressed() {
  // Between 2 and 9
  if (PApplet.parseInt(key) >= 50 && PApplet.parseInt(key) <= 57) {
    base = PApplet.parseInt(key)-48;
    if (ten) {
      base += 10;
      ten = false;
    }
    updatePositions();
  }
  // Key number 0
  if (PApplet.parseInt(key) == 48) {
    if (ten) {
      base = 10;
      ten = false;
    } else {
      number = 0;
    }
    updatePositions();
  }
  // Key number 1 (dizaines)
  if (PApplet.parseInt(key) == 49) {
    if (ten) {
      base = 11;
      ten = false;
    } else {
      ten = true;
    }
  }
}
  public void settings() {  size(800, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "diazou" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
