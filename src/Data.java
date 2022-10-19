import java.time.LocalDate;
import java.time.LocalDateTime;

public class Data implements Comparable<Data>{
    private String id;
    private LocalDateTime startdate;
    private LocalDateTime enddate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDateTime startdate) {
        this.startdate = startdate;
    }

    public LocalDateTime getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDateTime enddate) {
        this.enddate = enddate;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                '}';
    }


    @Override
    public int compareTo(Data o) {
        if(this.startdate.compareTo(o.startdate) == 0){
            return this.enddate.compareTo(o.enddate);
        }
        return this.startdate.compareTo(o.startdate);
    }
}
