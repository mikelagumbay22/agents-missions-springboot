package sheridan.lagumbaj.assignment3.data;

import sheridan.lagumbaj.assignment3.model.Agent;
import sheridan.lagumbaj.assignment3.model.Mission;
import sheridan.lagumbaj.assignment3.repository.AgentRepository;
import sheridan.lagumbaj.assignment3.repository.MissionRepository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer {

    private final AgentRepository agentRepository;
    private final MissionRepository missionRepository;

    public DataInitializer(AgentRepository agentRepository, MissionRepository missionRepository) {
        this.agentRepository = agentRepository;
        this.missionRepository = missionRepository;
    }

    @PostConstruct
    public void init() {

        Agent bond = new Agent("007", "James Bond", "British", "MI6 Field Agent",
                LocalDate.of(1962, 10, 5),
                "https://ui-avatars.com/api/?name=James+Bond&background=1a1a2e&color=fff&size=256");

        Agent romanoff = new Agent("Black Widow", "Natasha Romanoff", "Russian-American", "SHIELD Operative",
                LocalDate.of(2010, 5, 7),
                "https://ui-avatars.com/api/?name=Natasha+Romanoff&background=0f3460&color=fff&size=256");

        Agent salt = new Agent("Salt", "Evelyn Salt", "American", "CIA Officer",
                LocalDate.of(2010, 7, 23),
                "https://ui-avatars.com/api/?name=Evelyn+Salt&background=533483&color=fff&size=256");

        Agent hunt = new Agent("IMF-1", "Ethan Hunt", "American", "IMF Field Agent",
                LocalDate.of(1996, 5, 22),
                "https://ui-avatars.com/api/?name=Ethan+Hunt&background=e94560&color=fff&size=256");

        Agent powers = new Agent("International Man of Mystery", "Austin Powers", "British", "MI6 Agent",
                LocalDate.of(1967, 6, 1),
                "https://ui-avatars.com/api/?name=Austin+Powers&background=16213e&color=fff&size=256");

        Agent english = new Agent("Agent One", "Johnny English", "British", "MI7 Agent",
                LocalDate.of(2003, 4, 11),
                "https://ui-avatars.com/api/?name=Johnny+English&background=222831&color=fff&size=256");

        bond = agentRepository.save(bond);
        romanoff = agentRepository.save(romanoff);
        salt = agentRepository.save(salt);
        hunt = agentRepository.save(hunt);
        powers = agentRepository.save(powers);
        english = agentRepository.save(english);

        Mission nightfall = new Mission("M-001", "Operation Nightfall", "Berlin, Germany",
                LocalDate.of(2024, 2, 10), "Completed", "High",
                "Extraction of a defecting scientist from a heavily guarded research facility.");

        Mission redTide = new Mission("M-002", "Operation Red Tide", "Lagos, Nigeria",
                LocalDate.of(2024, 5, 18), "Completed", "Medium",
                "Disruption of an illegal arms shipment moving through the port district.");

        Mission glassHouse = new Mission("M-003", "Operation Glass House", "Tokyo, Japan",
                LocalDate.of(2025, 1, 22), "Active", "High",
                "Surveillance and sabotage of a rogue corporation's data servers.");

        Mission silentEcho = new Mission("M-004", "Operation Silent Echo", "Rome, Italy",
                LocalDate.of(2025, 6, 3), "Active", "Low",
                "Intelligence gathering on a suspected black-market art trafficking ring.");

        Mission ironVeil = new Mission("M-005", "Operation Iron Veil", "Toronto, Canada",
                LocalDate.of(2026, 3, 14), "Planning", "Medium",
                "Decryption of intercepted communications tied to a cyber-espionage network.");

        Mission crimsonDawn = new Mission("M-006", "Operation Crimson Dawn", "Cairo, Egypt",
                LocalDate.of(2026, 6, 1), "Planning", "High",
                "Joint task force operation to secure a stolen prototype device before auction.");

        nightfall = missionRepository.save(nightfall);
        redTide = missionRepository.save(redTide);
        glassHouse = missionRepository.save(glassHouse);
        silentEcho = missionRepository.save(silentEcho);
        ironVeil = missionRepository.save(ironVeil);
        crimsonDawn = missionRepository.save(crimsonDawn);


        bond.assignToMission(nightfall);
        bond.assignToMission(glassHouse);
        agentRepository.save(bond);

        romanoff.assignToMission(nightfall);
        romanoff.assignToMission(redTide);
        romanoff.assignToMission(crimsonDawn);
        agentRepository.save(romanoff);

        salt.assignToMission(redTide);
        salt.assignToMission(silentEcho);
        agentRepository.save(salt);

        hunt.assignToMission(glassHouse);
        hunt.assignToMission(crimsonDawn);
        agentRepository.save(hunt);

        powers.assignToMission(silentEcho);
        powers.assignToMission(ironVeil);
        agentRepository.save(powers);

        english.assignToMission(ironVeil);
        english.assignToMission(nightfall);
        agentRepository.save(english);
    }
}