package service;

import model.Medicament;
import persistenta.MedicamentRepo;

import java.util.List;

public class MedicamentService {
    private MedicamentRepo medicamentRepo;

    public MedicamentService(MedicamentRepo medicamentRepo) {
        this.medicamentRepo = medicamentRepo;
    }

    public Medicament getOne(String denumire){
        return medicamentRepo.getOne(denumire);
    }

    public void addMedicament(String denumire, String descriere, int doza) {
        medicamentRepo.addMedicament(new Medicament(denumire, descriere, doza));
    }

    public void toggleMedicament(String denumire){
        medicamentRepo.toggleMedicament(denumire);
    }

    public List<Medicament> getAll(){
        return medicamentRepo.getAll();
    }
}
