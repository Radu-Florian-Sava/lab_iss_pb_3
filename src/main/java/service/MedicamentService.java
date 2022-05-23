package service;

import model.Medicament;
import persistenta.MedicamentRepo;

public class MedicamentService {
    private MedicamentRepo medicamentRepo;

    public MedicamentService(MedicamentRepo medicamentRepo) {
        this.medicamentRepo = medicamentRepo;
    }

    public void addMedicament(String denumire, String descriere, int doza) {
        medicamentRepo.addMedicament(new Medicament(denumire, descriere, doza));
    }
}
