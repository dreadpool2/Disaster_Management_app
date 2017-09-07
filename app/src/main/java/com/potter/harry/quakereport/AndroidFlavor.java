package com.potter.harry.quakereport;

public class AndroidFlavor {
    private Double mversionname;
    private String mversionnumber;
    private Long imageresourceid;
    private String iuri;
    public AndroidFlavor(){}

    public AndroidFlavor(Double vName, String vNumber, Long imageResourceId, String url)
    {
        mversionname = vName;
        mversionnumber = vNumber;
        imageresourceid = imageResourceId;
        iuri = url;

    }

    /**
     * Get the name of the Android version
     */
    public Double getVersionName() {
        return mversionname;
    }

    /**
     * Get the Android version number
     */
    public String getVersionNumber() {
        return mversionnumber;
    }

    /**
     * Get the image resource ID
     */
    public Long getImageResourceId() {
        return imageresourceid;
    }

    public String getImageResoaurceId() {
        return iuri;
    }




}