package com.vtb.gb.auction;

import com.vtb.gb.auction.config.PrepareDataApp;
import com.vtb.gb.auction.service.AuctionStart;


public class App {
    public static void main(String[] args)  {
        PrepareDataApp.forcePrepareData();
        AuctionStart auctionStart = new AuctionStart();

         /*long start = System.currentTimeMillis();
        //Time OPTIMISTIC 469 ms
        auctionStart.startOptimistic();
        long end = System.currentTimeMillis();
        System.out.println(end - start);*/

        long start = System.currentTimeMillis();
        //TIME PESSIMISTIC 479 ms
        auctionStart.startPessimistic();
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
