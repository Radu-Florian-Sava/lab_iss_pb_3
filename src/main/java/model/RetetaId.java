package model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class RetetaId implements Serializable {
    private static final long serialVersionUID = 2630509155780170282L;
    @Column(name = "data", nullable = false)
    private LocalDateTime data;
    @Column(name = "numeMedicament", nullable = false, length = 30)
    private String numeMedicament;
    @Column(name = "numeSectie", nullable = false, length = 20)
    private String numeSectie;

    public RetetaId() {
        this.data = LocalDateTime.now();
    }

    public String getNumeSectie() {
        return numeSectie;
    }

    public void setNumeSectie(String numeSectie) {
        this.numeSectie = numeSectie;
    }

    public String getNumeMedicament() {
        return numeMedicament;
    }

    public void setNumeMedicament(String numeMedicament) {
        this.numeMedicament = numeMedicament;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, numeSectie, numeMedicament);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RetetaId entity = (RetetaId) o;
        return Objects.equals(this.data, entity.data) &&
                Objects.equals(this.numeSectie, entity.numeSectie) &&
                Objects.equals(this.numeMedicament, entity.numeMedicament);
    }

    @Override
    public String toString() {
        return numeSectie + " : " + data;
    }
}