package model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class RetetaId implements Serializable {
    private static final long serialVersionUID = -1665935185551500170L;
    @Column(name = "data", nullable = false)
    private LocalDateTime data;
    @Column(name = "numeMedicament", nullable = false, length = 30)
    private String numeMedicament;

    public RetetaId(LocalDateTime data, String numeMedicament) {
        this.data = data;
        this.numeMedicament = numeMedicament;
    }

    public RetetaId(String numeMedicament) {
        this.data = LocalDateTime.now();
        this.numeMedicament = numeMedicament;
    }

    public RetetaId() {
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
        return Objects.hash(data, numeMedicament);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RetetaId entity = (RetetaId) o;
        return Objects.equals(this.data, entity.data) &&
                Objects.equals(this.numeMedicament, entity.numeMedicament);
    }
}