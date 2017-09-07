package com.potter.harry.quakereport;

public class AndroidFlavor_two {
    public Double temp;
    public Double deg;
    public Double temp_kf;
    public String descrip;
    public Double speed;
    public Long time;
    public String icona;
    public String main;
    public Double lattu;
    public Double lonnu;


    public  AndroidFlavor_two(){}

    public AndroidFlavor_two(Double temp, Double deg,Double temp_kf,String descrip,Double speed,Long time,String icona,String main,Double lattu , Double lonnu)
    {
        this.temp=temp;
        this.deg=deg;
        this.temp_kf=temp_kf;
        this.descrip=descrip;
        this.speed=speed;
        this.time=time;
        this.icona=icona;
        this.main=main;
        this.lattu=lattu;
        this.lonnu=lonnu;


    }

    /**
     * Get the name of the Android version
     */
    public Double temp()
    {
        return temp;
    }
    public Double deg() {
        return deg;
    }
    public Double temp_kf() {
        return temp_kf;
    }
    public String descrip() {
        return descrip;
    }
    public Double speed() {
        return speed;
    }
    public Long timed() {
        return time;
    }
    public String icona() {
        return icona;
    }
    public String main() {
        return main;
    }
    public Double lattu()
    {
        return lattu;
    }
    public Double lonnu() {
        return lonnu;
    }




}