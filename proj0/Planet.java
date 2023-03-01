public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public double G = 6.67e-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
                    xxPos = xP;
                    yyPos = yP;
                    xxVel = xV;
                    yyVel = yV;
                    mass = m;
                    imgFileName = img;
                  }

    public Planet(Planet p) {
      xxPos = p.xxPos;
      yyPos = p.yyPos;
      xxVel = p.xxVel;
      yyVel = p.yyVel;
      mass = p.mass;
      imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
      double dx = Math.abs(this.xxPos - p.xxPos);
      double dy = Math.abs(this.yyPos - p.yyPos);
      double r = Math.sqrt(dx * dx + dy * dy);
      return r;
    }

    public double calcForceExertedBy(Planet p){
      return G * this.mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
    }
    public double calcForceExertedByX(Planet p){
      double costheta = (p.xxPos - this.xxPos) / this.calcDistance(p);
      return costheta * this.calcForceExertedBy(p);
    }

    public double calcForceExertedByY(Planet p){
      double sintheta = (p.yyPos - this.yyPos) / this .calcDistance(p);
      return sintheta * this.calcForceExertedBy(p);
    }

    public double calcNetForceExertedByX(Planet[] ps){
      double sumx = 0;
      for(int i = 0; i < ps.length; i++){
        if (!(ps[i].equals(this))){
          sumx += this.calcForceExertedByX(ps[i]);
        }
      }
      return sumx;
    }

    public double calcNetForceExertedByY(Planet[] ps){
      double sumy = 0;
      for (int i = 0; i < ps.length; i++){
        if (!(ps[i].equals(this))){
          sumy += this.calcForceExertedByY(ps[i]);
        }
      }
      return sumy;
    }

    public void update(double t, double fx, double fy){
      double ax = fx / this.mass;
      double ay = fy / this.mass;
      this.xxVel = this.xxVel + t * ax;
      this.yyVel = this.yyVel + t * ay;
      this.xxPos = this.xxPos + t * xxVel;
      this.yyPos = this.yyPos + t * yyVel;
    }

    public void draw(){
      StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
} 