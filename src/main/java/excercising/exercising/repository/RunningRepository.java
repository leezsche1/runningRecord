package excercising.exercising.repository;


import excercising.exercising.domain.RunningRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunningRepository extends JpaRepository<RunningRecord, Long> {



}
