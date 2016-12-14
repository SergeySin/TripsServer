package hiber_part;

import org.json.simple.JSONObject;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by sergey on 08.12.16.
 */
@Entity
@Table(name = "Trips", schema = "mydb")
public class TripsEntity {
    private int tripsId;
    private String tripsUserId;
    private String tripsUserPost;
    private String tripsUserComment;
    private String tripsFrom;
    private String tripsTo;
    private String tripsGroupId;
    private Time tripsTime;
    private Date tripsDate;
    private Date tripsPostDate;
    private Time tripsPostTime;
    private Integer tripsAnalyseDirection;
    private Integer tripsAnalyseDate;
    private Integer tripsAnalyseTime;

    @Id
    @Column(name = "trips_id", nullable = false)
    public int getTripsId() {
        return tripsId;
    }

    public void setTripsId(int tripsId) {
        this.tripsId = tripsId;
    }

    @Basic
    @Column(name = "trips_user_id", nullable = true, length = 45)
    public String getTripsUserId() {
        return tripsUserId;
    }

    public void setTripsUserId(String tripsUserId) {
        this.tripsUserId = tripsUserId;
    }

    @Basic
    @Column(name = "trips_user_post", nullable = true, length = 255)
    public String getTripsUserPost() {
        return tripsUserPost;
    }

    public void setTripsUserPost(String tripsUserPost) {
        this.tripsUserPost = tripsUserPost;
    }

    @Basic
    @Column(name = "trips_user_comment", nullable = true, length = 90)
    public String getTripsUserComment() {
        return tripsUserComment;
    }

    public void setTripsUserComment(String tripsUserComment) {
        this.tripsUserComment = tripsUserComment;
    }

    @Basic
    @Column(name = "trips_from", nullable = true, length = 45)
    public String getTripsFrom() {
        return tripsFrom;
    }

    public void setTripsFrom(String tripsFrom) {
        this.tripsFrom = tripsFrom;
    }

    @Basic
    @Column(name = "trips_to", nullable = true, length = 45)
    public String getTripsTo() {
        return tripsTo;
    }

    public void setTripsTo(String tripsTo) {
        this.tripsTo = tripsTo;
    }

    @Basic
    @Column(name = "trips_group_id", nullable = true, length = 45)
    public String getTripsGroupId() {
        return tripsGroupId;
    }

    public void setTripsGroupId(String tripsGroupId) {
        this.tripsGroupId = tripsGroupId;
    }

    @Basic
    @Column(name = "trips_time", nullable = true)
    public Time getTripsTime() {
        return tripsTime;
    }

    public void setTripsTime(Time tripsTime) {
        this.tripsTime = tripsTime;
    }

    @Basic
    @Column(name = "trips_date", nullable = true)
    public Date getTripsDate() {
        return tripsDate;
    }

    public void setTripsDate(Date tripsDate) {
        this.tripsDate = tripsDate;
    }

    @Basic
    @Column(name = "trips_post_date", nullable = true)
    public Date getTripsPostDate() {
        return tripsPostDate;
    }

    public void setTripsPostDate(Date tripsPostDate) {
        this.tripsPostDate = tripsPostDate;
    }

    @Basic
    @Column(name = "trips_post_time", nullable = true)
    public Time getTripsPostTime() {
        return tripsPostTime;
    }

    public void setTripsPostTime(Time tripsPostTime) {
        this.tripsPostTime = tripsPostTime;
    }

    @Basic
    @Column(name = "trips_analyse_direction", nullable = true)
    public Integer getTripsAnalyseDirection() {
        return tripsAnalyseDirection;
    }

    public void setTripsAnalyseDirection(Integer tripsAnalyseDirection) {
        this.tripsAnalyseDirection = tripsAnalyseDirection;
    }

    @Basic
    @Column(name = "trips_analyse_date", nullable = true)
    public Integer getTripsAnalyseDate() {
        return tripsAnalyseDate;
    }

    public void setTripsAnalyseDate(Integer tripsAnalyseDate) {
        this.tripsAnalyseDate = tripsAnalyseDate;
    }

    @Basic
    @Column(name = "trips_analyse_time", nullable = true)
    public Integer getTripsAnalyseTime() {
        return tripsAnalyseTime;
    }

    public void setTripsAnalyseTime(Integer tripsAnalyseTime) {
        this.tripsAnalyseTime = tripsAnalyseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TripsEntity that = (TripsEntity) o;

        if (tripsId != that.tripsId) return false;
        if (tripsUserId != null ? !tripsUserId.equals(that.tripsUserId) : that.tripsUserId != null) return false;
        if (tripsUserPost != null ? !tripsUserPost.equals(that.tripsUserPost) : that.tripsUserPost != null)
            return false;
        if (tripsUserComment != null ? !tripsUserComment.equals(that.tripsUserComment) : that.tripsUserComment != null)
            return false;
        if (tripsFrom != null ? !tripsFrom.equals(that.tripsFrom) : that.tripsFrom != null) return false;
        if (tripsTo != null ? !tripsTo.equals(that.tripsTo) : that.tripsTo != null) return false;
        if (tripsGroupId != null ? !tripsGroupId.equals(that.tripsGroupId) : that.tripsGroupId != null) return false;
        if (tripsTime != null ? !tripsTime.equals(that.tripsTime) : that.tripsTime != null) return false;
        if (tripsDate != null ? !tripsDate.equals(that.tripsDate) : that.tripsDate != null) return false;
        if (tripsPostDate != null ? !tripsPostDate.equals(that.tripsPostDate) : that.tripsPostDate != null)
            return false;
        if (tripsPostTime != null ? !tripsPostTime.equals(that.tripsPostTime) : that.tripsPostTime != null)
            return false;
        if (tripsAnalyseDirection != null ? !tripsAnalyseDirection.equals(that.tripsAnalyseDirection) : that.tripsAnalyseDirection != null)
            return false;
        if (tripsAnalyseDate != null ? !tripsAnalyseDate.equals(that.tripsAnalyseDate) : that.tripsAnalyseDate != null)
            return false;
        if (tripsAnalyseTime != null ? !tripsAnalyseTime.equals(that.tripsAnalyseTime) : that.tripsAnalyseTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tripsId;
        result = 31 * result + (tripsUserId != null ? tripsUserId.hashCode() : 0);
        result = 31 * result + (tripsUserPost != null ? tripsUserPost.hashCode() : 0);
        result = 31 * result + (tripsUserComment != null ? tripsUserComment.hashCode() : 0);
        result = 31 * result + (tripsFrom != null ? tripsFrom.hashCode() : 0);
        result = 31 * result + (tripsTo != null ? tripsTo.hashCode() : 0);
        result = 31 * result + (tripsGroupId != null ? tripsGroupId.hashCode() : 0);
        result = 31 * result + (tripsTime != null ? tripsTime.hashCode() : 0);
        result = 31 * result + (tripsDate != null ? tripsDate.hashCode() : 0);
        result = 31 * result + (tripsPostDate != null ? tripsPostDate.hashCode() : 0);
        result = 31 * result + (tripsPostTime != null ? tripsPostTime.hashCode() : 0);
        result = 31 * result + (tripsAnalyseDirection != null ? tripsAnalyseDirection.hashCode() : 0);
        result = 31 * result + (tripsAnalyseDate != null ? tripsAnalyseDate.hashCode() : 0);
        result = 31 * result + (tripsAnalyseTime != null ? tripsAnalyseTime.hashCode() : 0);
        return result;
    }

    public JSONObject toJson(){

        JSONObject JsonPost = new JSONObject();

        JsonPost.put("tripsId", tripsId);
        JsonPost.put("tripsUserId", tripsUserId);
        JsonPost.put("tripsUserPost", tripsUserPost);
        JsonPost.put("tripsUserComment", tripsUserComment);
        JsonPost.put("tripsFrom", tripsFrom);
        JsonPost.put("tripsTo", tripsTo);
        JsonPost.put("tripsGroupId", tripsGroupId);
        JsonPost.put("tripsTime", tripsTime);
        JsonPost.put("tripsDate", tripsDate);
        JsonPost.put("tripsPostDate", tripsPostDate);
        JsonPost.put("tripsPostTime", tripsPostTime);
        JsonPost.put("tripsAnalyseStatus", tripsAnalyseDirection);
        JsonPost.put("tripsAnalyseStatus", tripsAnalyseDate);
        JsonPost.put("tripsAnalyseStatus", tripsAnalyseTime);

        return JsonPost;

    }

}
