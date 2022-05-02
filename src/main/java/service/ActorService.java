package service;

import persistenta.ActorRepo;

public class ActorService {
    private ActorRepo actorRepo;
    public ActorService(ActorRepo actorRepo){
        this.actorRepo = actorRepo;
    }

    public boolean loginAdmin(String username, String password){
        return actorRepo.loginAdmin(username, password);
    }
    public boolean loginMedic(String username, String password){
        return actorRepo.loginMedic(username, password);
    }
    public boolean loginFarmacist(String username, String password){
        return actorRepo.loginFarmacist(username, password);
    }


}
