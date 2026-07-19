package sheridan.lagumbaj.assignment3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "agents")
public class Agent {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "codename")
    private String codename = "";

    @Column(name = "full_name")
    private String fullName = "";

    private String nationality = "";

    private String rank = "";

    @Column(name = "date_recruited")
    private LocalDate dateRecruited;

    @Column(name = "image_url")
    private String imageUrl = "";

    @ManyToMany
    @JoinTable(
            name = "agent_mission",
            joinColumns = @JoinColumn(name = "agent_id"),
            inverseJoinColumns = @JoinColumn(name = "mission_code")
    )
    private List<Mission> missions = new ArrayList<>();

    public Agent() {
    }

    public Agent(String codename, String fullName, String nationality,
                 String rank, LocalDate dateRecruited, String imageUrl) {
        this.codename = codename;
        this.fullName = fullName;
        this.nationality = nationality;
        this.rank = rank;
        this.dateRecruited = dateRecruited;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public LocalDate getDateRecruited() {
        return dateRecruited;
    }

    public void setDateRecruited(LocalDate dateRecruited) {
        this.dateRecruited = dateRecruited;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public void assignToMission(Mission mission) {
        this.missions.add(mission);
    }

    public void unassignFromMission(Mission mission) {
        this.missions.removeIf(m -> m.getMissionCode().equals(mission.getMissionCode()));
    }
}
