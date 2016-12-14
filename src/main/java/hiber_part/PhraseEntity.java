package hiber_part;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sergey on 08.12.16.
 */
@Entity
@Table(name = "Phrase", schema = "mydb", catalog = "")
public class PhraseEntity {
    private int phraseId;

    @Id
    @Column(name = "Phrase_id", nullable = false)
    public int getPhraseId() {
        return phraseId;
    }

    public void setPhraseId(int phraseId) {
        this.phraseId = phraseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhraseEntity that = (PhraseEntity) o;

        if (phraseId != that.phraseId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return phraseId;
    }
}
