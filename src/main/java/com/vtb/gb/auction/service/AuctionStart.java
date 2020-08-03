package com.vtb.gb.auction.service;

import com.vtb.gb.auction.domain.User;
import com.vtb.gb.auction.entity_manager.ManagerFactory;
import com.vtb.gb.auction.entity_manager.SessionFactoryManager;
import com.vtb.gb.auction.repo.ItemRepo;
import com.vtb.gb.auction.repo.UserRepo;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AuctionStart {
    private UserRepo userRepo;
    private ItemRepo itemRepo;

    public AuctionStart() {
        userRepo = new UserRepo();
        itemRepo = new ItemRepo();
    }

    public void startOptimistic() {
        List<User> users = userRepo.getUser();
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (User user : users){
            executorService.execute(() -> {
                for (int i = 0; i < 1000; i++){
                    itemRepo.setNewBindOptimistic(user);
                }
            });
        }
        executorService.shutdown();
        if (Thread.activeCount() == 0)  SessionFactoryManager.close();
    }

    public void startPessimistic()  {
        List<User> users = userRepo.getUser();
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (User user : users){
            executorService.execute(() -> {
                for (int i = 0; i < 1000; i++){
                    itemRepo.setNewBindPessimistic(user);
                }
            });
        }
        executorService.shutdown();
        if (Thread.activeCount() == 0)  ManagerFactory.close();
    }
}
