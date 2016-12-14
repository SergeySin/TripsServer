package hiber_part;

import javax.persistence.*;

/**
 * Created by sergey on 29.11.16.
 */
@Entity
@Table(name = "Source_associations", schema = "mydb")
public class SourceAssociationsEntity {
    private int saId;
    private String saName;
    private int saSourceId;

    @Id
    @Column(name = "SA_id", nullable = false)
    public int getSaId() {
        return saId;
    }

    public void setSaId(int saId) {
        this.saId = saId;
    }

    @Basic
    @Column(name = "SA_name", nullable = true, length = 45)
    public String getSaName() {
        return saName;
    }

    public void setSaName(String saName) {
        this.saName = saName;
    }

    @Basic
    @Column(name = "SA_Source_id", nullable = false)
    public int getSaSourceId() {
        return saSourceId;
    }

    public void setSaSourceId(int saSourceId) {
        this.saSourceId = saSourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SourceAssociationsEntity that = (SourceAssociationsEntity) o;

        if (saId != that.saId) return false;
        if (saSourceId != that.saSourceId) return false;
        if (saName != null ? !saName.equals(that.saName) : that.saName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = saId;
        result = 31 * result + (saName != null ? saName.hashCode() : 0);
        result = 31 * result + saSourceId;
        return result;
    }
}
