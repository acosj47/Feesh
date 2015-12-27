// class of feesh!!!!!!!!!!
// by Justin

// objects
Fish feesh, cheep, mike, jill;  // fish objects
Fish [] school;
String [] fishNames = {"Feesh", "Cheep", "Mike", "Jill"};


void setup() {
  size(800, 600);
  reset();
}
// reset, object instances
void reset() {
  school = new Fish[4];
  school[0] = new Fish(50, 50, color(255, 50, 0), fishNames[0]);
  school[1] = new Fish(100, 100, color(0, 200, 20), fishNames[1]);
  school[2] = new Fish(125, 125, color(0, 20, 200), fishNames[2]);
  school[3] = new Fish(60, 60, color(255, 150, 150), fishNames[3]);
}

void draw() {
  background(0, 100, 200);
  for (int i =0; i < school.length; i++) {
    school[i].show();
    school[i].move();
    school[i].bounce();
  }
  collisions();
}

// handles collision checks with the Fish class hit Method
void collisions() {
  float tmp;
  for (int i = 0; i < school.length; i++) {
    for (int j = 1; j < school.length; j++) {
      if (school[i].hit(school[j].x, school[j].y)) {
        tmp = school[i].dx;
        school[i].dx = school[j].dx;
        school[j].dx = tmp;

        tmp = school[i].dy;
        school[i].dy = school[j].dy;
        school[j].dy = tmp;
        // push off each other harder
        {school[i].move();}
      }
    }
  }
}


void keyPressed() {
  if (key == 'r') {reset();}
}

class Fish {
  // locals
  float x, y, dx, dy;  // position and speed
  color c;              // color
  float w, h;           // size
  int count = 0;         // count for animation
  String name = "";
  // fish constructor
  Fish( float w, float h, color c, String name) {
    this.w = w;
    this.h = h;
    this.c = c;
    this.name = name;
    x = random( w, width - w);
    y = random( height/2, height - h);
    dx = random(-1.5, 1.5);
    dy = random(-1.5, 1.5);
  }

  // Methods

  // show the fish
  void show() {
    count++;
    // dorsal fin
    noStroke();
    fill(255, 255, 0);
    ellipse(x, y - h/2.5, w/1.5, h/2); 

    //tail
    if (dx < 0) {
      fill(255);
      ellipse(x + w/1.6, y - h/5, w/2.5, h/2.5);
      ellipse(x + w/1.7, y, w/2.5, h/2.5);
      ellipse(x + w/1.8, y + h/5, w/2.5, h/2.5);
    } else {
      fill(255);
      ellipse(x - w/1.6, y - h/5, w/2.5, h/2.5);
      ellipse(x - w/1.7, y, w/2.5, h/2.5);
      ellipse(x - w/1.8, y + h/5, w/2.5, h/2.5);
    }


    //body
    if (dx < 0) {
      noStroke();
      fill(c);
      ellipse(x, y, w, h);
      ellipse(x + w/3.2, y + h/20, w/1.5, h/1.25);
    } else {
      noStroke();
      fill(c);
      ellipse(x, y, w, h);
      ellipse(x - w/3.2, y + h/20, w/1.5, h/1.25);
    }

    // mouth
    if (dx < 0) {
      fill(255, 200, 200);
      ellipse(x - w/2.6, y + h/6.6, w/3, h/3.5);

      if (count / 30 % 2 == 0) {   // mouth animation
        strokeWeight(3);
      } else {
        strokeWeight(5);
      }
      stroke(0);   
      line(x - w/1.9, y + h/6.6, x - w/2.5, y + h/6.6);
      noStroke();
      strokeWeight(1);
    }

    if (dx > 0) {
      fill(255, 200, 200);
      ellipse(x + w/2.6, y + h/6.6, w/3, h/3.5);

      if (count / 30 % 2 == 0) {   // mouth animation
        strokeWeight(5);
      } else {
        strokeWeight(8);
      }
      stroke(0);   
      line(x + w/1.9, y + h/6.6, x + w/2.5, y + h/6.6);
      noStroke();
      strokeWeight(1);
    } 

    //eyes
    if (dx < 0) {
      fill(255);
      ellipse(x - w/2, y - h/6.6, w/5, h/2.5);
      ellipse(x - w/2.8, y - h/6.6, w/5, h/2.5);
      //pupils
      fill(0);
      ellipse(x - w/1.8, y - h/6.6, w/20, h/10);
      ellipse(x - w/2.5, y - h/6.6, w/20, h/10);
    } else {
      fill(255);
      ellipse(x + w/2.5, y - h/6.6, w/5, h/2.5);
      ellipse(x + w/2, y - h/6.6, w/5, h/2.5);
      //pupils
      fill(0);
      ellipse(x + w/2.2, y - h/6.6, w/20, h/10);
      ellipse(x + w/1.8, y - h/6.6, w/20, h/10);
    }

    // fins
    if (dx < 0) {
      if (count/30 % 2 ==0) {         // fin animation
        fill(255);
        arc(x, y, w/4, h/2.5, PI+HALF_PI, TWO_PI+HALF_PI);
        arc(x, y - h/6.6, w/2, h/2, PI+HALF_PI, TWO_PI+HALF_PI);
      } else {
        fill(255);
        arc(x, y, w/2, h/2.5, PI+HALF_PI, TWO_PI+HALF_PI);
        arc(x, y - h/6.6, w/1.5, h/2, PI+HALF_PI, TWO_PI+HALF_PI);
      }
    }
    // fins
    if (dx > 0) {
      if (count/30 % 2 == 0) {   // fin animation
        fill(255);
        arc(x, y, w/4, h/2.5, PI-HALF_PI, TWO_PI-HALF_PI);
        arc(x, y - h/6.6, w/2, h/2, PI-HALF_PI, TWO_PI-HALF_PI);
      } else {
        fill(255);
        arc(x, y, w/2, h/2.5, PI-HALF_PI, TWO_PI-HALF_PI);
        arc(x, y - h/6.6, w/1.5, h/2, PI-HALF_PI, TWO_PI-HALF_PI);
      }
    }

    // belly
    if (dx < 0) {
      fill(255);
      ellipse(x, y + h/2.8, w/1.7, h/3.5);
      ellipse(x + w/6.6, y + h/2.8, w/1.7, h/5);
    } else {
      fill(255);
      ellipse(x, y + h/2.8, w/1.7, h/3.5);
      ellipse(x - w/6.6, y + h/2.8, w/1.7, h/5);
    }
  }
  // move the fish
  void move() {
    x += dx;
    y += dy;
  }

  // bounce off walls
  void bounce() {
    if (x > width - w || x < w) {dx *= -1;}
    if (y > height - h || y < h) {dy *= -1;}
  }

  // check if you hit something
  boolean hit(float a, float b) {
    return (dist(x, y, a, b) < w);
  }
}
