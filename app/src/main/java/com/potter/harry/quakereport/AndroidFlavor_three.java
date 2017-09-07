package com.potter.harry.quakereport;

public class AndroidFlavor_three {
    public Double currentdegree;
    public Double windspeed;
    public Double winddirection;
    public Double humidity;
    public Double pressure;
    public String ico;

    public AndroidFlavor_three(){}

    public AndroidFlavor_three(Double currentdegree, Double windspeed,Double winddirection,Double humidity,Double pressure,String ico)
    {
        this.currentdegree=currentdegree;
        this.windspeed=windspeed;
        this.winddirection=winddirection;
        this.humidity=humidity;
        this.pressure=pressure;
        this.ico=ico;


    }

    /**
     * Get the name of the Android version
     */
    public Double currentdegree() {
        return currentdegree;
    }
    public Double windspeed() {
        return windspeed;
    }
    public Double humidity() {
        return humidity;
    }
    public Double pressure() {
        return pressure;
    }
    public Double winddirection() {
        return winddirection;
    }
    public String ico() {
        return ico;
    }


}