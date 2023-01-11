package com.god.moviesdownloder_god;

public class Hindi {

   String FullHD,HD,SD;


   public Hindi(){

   }


    public Hindi(String fullHD, String HD, String SD) {
        FullHD = fullHD;
        this.HD = HD;
        this.SD = SD;
    }

    public String getFullHD() {
        return FullHD;
    }

    public void setFullHD(String fullHD) {
        FullHD = fullHD;
    }

    public String getHD() {
        return HD;
    }

    public void setHD(String HD) {
        this.HD = HD;
    }

    public String getSD() {
        return SD;
    }

    public void setSD(String SD) {
        this.SD = SD;
    }
}
