package sheridan.lagumbaj.assignment3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sheridan.lagumbaj.assignment3.model.Agent;

public interface AgentRepository extends JpaRepository<Agent, Integer> {
}
