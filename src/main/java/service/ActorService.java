package service;

import model.GenericActor;
import persistenta.ActorRepo;

import java.util.List;

public class ActorService {
    private ActorRepo actorRepo;


    public ActorService(ActorRepo actorRepo) {
        this.actorRepo = actorRepo;
    }

    public boolean addMedic(String username, String password, String checkPassword) {
        return actorRepo.addMedic(username, password, checkPassword);
    }

    public boolean addFarmacist(String username, String password, String checkPassword) {
        return actorRepo.addFarmacist(username, password, checkPassword);
    }

    public boolean loginAdmin(String username, String password) {
        return actorRepo.loginAdmin(username, password);
    }

    public boolean loginMedic(String username, String password) {
        return actorRepo.loginMedic(username, password);
    }

    public boolean loginFarmacist(String username, String password) {
        return actorRepo.loginFarmacist(username, password);
    }

    public List<GenericActor> getAllNonAdmin(){
        return actorRepo.getAllNonAdmin();
    }

    public GenericActor getOne(String username){
        return actorRepo.getOne(username);
    }
}
