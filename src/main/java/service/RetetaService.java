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
}
