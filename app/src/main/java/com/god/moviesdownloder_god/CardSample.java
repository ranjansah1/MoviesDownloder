package com.god.moviesdownloder_god;

import android.widget.ImageView;
import android.widget.TextView;

public class CardSample {
    private String CardImg,CardCoutTb,CardHndiDubbed,CardID;




    public CardSample(String cardImg, String cardCoutTb, String cardHndiDubbed,String cardID) {
        CardImg = cardImg;
        CardCoutTb = cardCoutTb;
        CardHndiDubbed = cardHndiDubbed;
        CardID=cardID;

    }
    public CardSample(){}


    public String getCardImg() {
        return CardImg;
    }

    public void setCardImg(String cardImg) {
        CardImg = cardImg;
    }

    public String getCardCoutTb() {
        return CardCoutTb;
    }

    public void setCardCoutTb(String cardCoutTb) {
        CardCoutTb = cardCoutTb;
    }

    public String getCardHndiDubbed() {
        return CardHndiDubbed;
    }

    public void setCardHndiDubbed(String cardHndiDubbed) {
        CardHndiDubbed = cardHndiDubbed;
    }

    public String getCardID() {
        return CardID;
    }

    public void setCardID(String cardID) {
        CardID = cardID;
    }
}
