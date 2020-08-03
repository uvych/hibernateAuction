package com.vtb.gb.auction;

import com.vtb.gb.auction.config.PrepareDataApp;
import com.vtb.gb.auction.service.AuctionStart;


public class App {
    public static void main(String[] args)  {
        PrepareDataApp.forcePrepareData();
        AuctionStart auctionStart = new AuctionStart();
        

    }
}
