package sheridan.lagumbaj.assignment3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "missions")
public class Mission {

    @Id
    @Column(name = "mission_code")
    private String missionCode = "";

    private String title = "";

    private String location = "";

    @Column(name = "date_started")
    private LocalDate dateStarted;

    private String status = "";

    @Column(name = "risk_level")
    private String riskLevel = "";

    private String description = "";

    @ManyToMany(mappedBy = "missions")
    private List<Agent> agents;

    public Mission() {
    }

    public Mission(String missionCode, String title, String location,
                   LocalDate dateStarted, String status, String riskLevel, String description) {
        this.missionCode = missionCode;
        this.title = title;
        this.location = location;
        this.dateStarted = dateStarted;
        this.status = status;
        this.riskLevel = riskLevel;
        this.description = description;
    }

    public String getMissionCode() {
        return missionCode;
    }

    public void setMissionCode(String missionCode) {
        this.missionCode = missionCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(LocalDate dateStarted) {
        this.dateStarted = dateStarted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }
}
