package hiber_part;

import javax.persistence.*;

/**
 * Created by sergey on 08.12.16.
 */
@Entity
@Table(name = "Source", schema = "mydb")
public class SourceEntity {
    private int sourceId;
    private String sourceName;
    private String sourceReplace;
    private String sourceFunc;

    @Id
    @Column(name = "Source_id", nullable = false)
    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    @Basic
    @Column(name = "Source_name", nullable = false, length = 180)
    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    @Basic
    @Column(name = "Source_replace", nullable = true, length = 180)
    public String getSourceReplace() {
        return sourceReplace;
    }

    public void setSourceReplace(String sourceReplace) {
        this.sourceReplace = sourceReplace;
    }

    @Basic
    @Column(name = "Source_func", nullable = true, length = 180)
    public String getSourceFunc() {
        return sourceFunc;
    }

    public void setSourceFunc(String sourceFunc) {
        this.sourceFunc = sourceFunc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SourceEntity that = (SourceEntity) o;

        if (sourceId != that.sourceId) return false;
        if (sourceName != null ? !sourceName.equals(that.sourceName) : that.sourceName != null) return false;
        if (sourceReplace != null ? !sourceReplace.equals(that.sourceReplace) : that.sourceReplace != null)
            return false;
        if (sourceFunc != null ? !sourceFunc.equals(that.sourceFunc) : that.sourceFunc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sourceId;
        result = 31 * result + (sourceName != null ? sourceName.hashCode() : 0);
        result = 31 * result + (sourceReplace != null ? sourceReplace.hashCode() : 0);
        result = 31 * result + (sourceFunc != null ? sourceFunc.hashCode() : 0);
        return result;
    }
}
