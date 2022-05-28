package service;

import model.Reteta;
import model.RetetaId;
import persistenta.RetetaRepo;

import java.util.List;

public class RetetaService {
    private RetetaRepo retetaRepo;

    public RetetaService(RetetaRepo retetaRepo) {
        this.retetaRepo = retetaRepo;
    }

    public void finishCommand(RetetaId retetaId) {
        retetaRepo.finishCommand(retetaId);
    }

    public List<Reteta> getAll() {
        return retetaRepo.getAll();
    }

    public List<Reteta> getAllNeonorate() {
        return retetaRepo.getAllNeonorate();
    }

    public List<Reteta> getAllNeonorateOf(String username) {
        return retetaRepo.getAllNeonorateOf(username);
    }

    public List<Reteta> getAllOnorateOf(String username) {
        return retetaRepo.getAllOnorateOf(username);
    }

    public void addRetetaWithLastTimestamp(Reteta reteta) {
        retetaRepo.addRetetaWithLastTimestamp(reteta);
    }

    public void eraseTimestamp() {
        retetaRepo.eraseTimestamp();
    }

    public void cancelCommand(RetetaId retetaId) {
        retetaRepo.cancelCommand(retetaId);
    }
}
