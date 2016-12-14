package hiber_part;

import javax.persistence.*;

/**
 * Created by sergey on 08.12.16.
 */
@Entity
@Table(name = "Word", schema = "mydb")
public class WordEntity {
    private int wordId;
    private String wordName;
    private Integer wordPhraseId;

    @Id
    @Column(name = "Word_id", nullable = false)
    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    @Basic
    @Column(name = "Word_name", nullable = true, length = 180)
    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    @Basic
    @Column(name = "Word_phrase_id", nullable = true)
    public Integer getWordPhraseId() {
        return wordPhraseId;
    }

    public void setWordPhraseId(Integer wordPhraseId) {
        this.wordPhraseId = wordPhraseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordEntity that = (WordEntity) o;

        if (wordId != that.wordId) return false;
        if (wordName != null ? !wordName.equals(that.wordName) : that.wordName != null) return false;
        if (wordPhraseId != null ? !wordPhraseId.equals(that.wordPhraseId) : that.wordPhraseId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wordId;
        result = 31 * result + (wordName != null ? wordName.hashCode() : 0);
        result = 31 * result + (wordPhraseId != null ? wordPhraseId.hashCode() : 0);
        return result;
    }
}
