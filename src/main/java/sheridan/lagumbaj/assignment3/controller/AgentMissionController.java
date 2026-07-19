package sheridan.lagumbaj.assignment3.controller;

import sheridan.lagumbaj.assignment3.model.Agent;
import sheridan.lagumbaj.assignment3.model.Mission;
import sheridan.lagumbaj.assignment3.repository.AgentRepository;
import sheridan.lagumbaj.assignment3.repository.MissionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AgentMissionController {

    private final AgentRepository agentRepository;
    private final MissionRepository missionRepository;

    public AgentMissionController(AgentRepository agentRepository, MissionRepository missionRepository) {
        this.agentRepository = agentRepository;
        this.missionRepository = missionRepository;
    }

    @GetMapping({"/", "/index"})
    public String showIndex() {
        return "index";
    }

    //Agents

    @GetMapping({"/agents"})
    public String listAgents(Model model) {
        model.addAttribute("agents", this.agentRepository.findAll());
        return "agent-list";
    }

    @GetMapping({"/agents/new"})
    public String showAddAgentForm(Model model) {
        model.addAttribute("agent", new Agent());
        return "agent-form";
    }

    @PostMapping({"/agents"})
    public String saveAgent(@ModelAttribute Agent agent) {
        this.agentRepository.save(agent);
        return "redirect:/agents";
    }

    @GetMapping({"/agents/{id}"})
    public String showAgentDetails(@PathVariable Integer id, Model model) {
        Agent agent = this.agentRepository.findById(id).orElseThrow();
        model.addAttribute("agent", agent);
        model.addAttribute("availableMissions", missionsNotYetAssignedTo(agent));
        return "agent-details";
    }

    @PostMapping({"/agents/{id}/assign-mission"})
    public String assignMissionToAgent(@PathVariable Integer id, @RequestParam String missionCode) {
        Agent agent = this.agentRepository.findById(id).orElseThrow();
        Mission mission = this.missionRepository.findById(missionCode).orElseThrow();
        boolean alreadyAssigned = agent.getMissions().stream()
                .anyMatch(m -> m.getMissionCode().equals(missionCode));
        if (!alreadyAssigned) {
            agent.assignToMission(mission);
            this.agentRepository.save(agent);
        }
        return "redirect:/agents/" + id;
    }

    @PostMapping({"/agents/{id}/unassign-mission"})
    public String unassignMissionFromAgent(@PathVariable Integer id, @RequestParam String missionCode) {
        Agent agent = this.agentRepository.findById(id).orElseThrow();
        Mission mission = this.missionRepository.findById(missionCode).orElseThrow();
        agent.unassignFromMission(mission);
        this.agentRepository.save(agent);
        return "redirect:/agents/" + id;
    }

    //MIssions

    @GetMapping({"/missions"})
    public String listMissions(Model model) {
        model.addAttribute("missions", this.missionRepository.findAll());
        return "mission-list";
    }

    @GetMapping({"/missions/new"})
    public String showAddMissionForm(Model model) {
        model.addAttribute("mission", new Mission());
        return "mission-form";
    }

    @PostMapping({"/missions"})
    public String saveMission(@ModelAttribute Mission mission) {
        this.missionRepository.save(mission);
        return "redirect:/missions";
    }

    @GetMapping({"/missions/{code}"})
    public String showMissionDetails(@PathVariable String code, Model model) {
        Mission mission = this.missionRepository.findById(code).orElseThrow();
        model.addAttribute("mission", mission);
        model.addAttribute("availableAgents", agentsNotYetAssignedTo(mission));
        return "mission-details";
    }

    @PostMapping({"/missions/{code}/assign-agent"})
    public String assignAgentToMission(@PathVariable String code, @RequestParam Integer agentId) {
        Mission mission = this.missionRepository.findById(code).orElseThrow();
        Agent agent = this.agentRepository.findById(agentId).orElseThrow();
        boolean alreadyAssigned = agent.getMissions().stream()
                .anyMatch(m -> m.getMissionCode().equals(code));
        if (!alreadyAssigned) {
            agent.assignToMission(mission);
            this.agentRepository.save(agent);
        }
        return "redirect:/missions/" + code;
    }

    @PostMapping({"/missions/{code}/unassign-agent"})
    public String unassignAgentFromMission(@PathVariable String code, @RequestParam Integer agentId) {
        Mission mission = this.missionRepository.findById(code).orElseThrow();
        Agent agent = this.agentRepository.findById(agentId).orElseThrow();
        agent.unassignFromMission(mission);
        this.agentRepository.save(agent);
        return "redirect:/missions/" + code;
    }

    //Helpers

    private List<Mission> missionsNotYetAssignedTo(Agent agent) {
        List<Mission> assigned = agent.getMissions();
        return this.missionRepository.findAll().stream()
                .filter(m -> assigned.stream().noneMatch(a -> a.getMissionCode().equals(m.getMissionCode())))
                .collect(Collectors.toList());
    }

    private List<Agent> agentsNotYetAssignedTo(Mission mission) {
        return this.agentRepository.findAll().stream()
                .filter(agent -> agent.getMissions().stream()
                        .noneMatch(m -> m.getMissionCode().equals(mission.getMissionCode())))
                .collect(Collectors.toList());
    }

    @ModelAttribute("localDate")
    LocalDate getLocalDate() {
        return LocalDate.now();
    }

    @ExceptionHandler({NoSuchElementException.class})
    ModelAndView dataNotFound(NoSuchElementException e) {
        ModelAndView mv = new ModelAndView("data-not-found", "message", e.getMessage());
        mv.addObject("localDate", this.getLocalDate());
        return mv;
    }
}
