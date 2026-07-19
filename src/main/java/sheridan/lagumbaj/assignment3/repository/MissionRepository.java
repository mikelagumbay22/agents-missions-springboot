package sheridan.lagumbaj.assignment3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sheridan.lagumbaj.assignment3.model.Mission;

public interface MissionRepository extends JpaRepository<Mission, String> {
}
