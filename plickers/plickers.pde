int sq_width;
int sq_height;
String bin;

int niverenn;

int lastClick = 0;


void setup() {
  size(500, 500);
  frameRate(120);
  sq_width = width/5;
  sq_height = height/5;
  niverenn = 0;
  noStroke();
  textSize(100);
  textAlign(CENTER, CENTER);
  noLoop();
}

void draw() {
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

void mouseClicked() {
  if (millis() - lastClick < 200) {
    loop();
  } else {
    noLoop();
    redraw();
  }
  lastClick = millis();
}

void doubleClicked(){
  loop();
}
