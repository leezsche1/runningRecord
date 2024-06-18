package excercising.exercising.service;

import excercising.exercising.domain.RunningRecord;
import excercising.exercising.repository.RunningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RunningService {

    private final RunningRepository runningRepository;

    @Transactional
    public void save(RunningRecord runningRecord) {
        runningRepository.save(runningRecord);
    }

    public Optional<RunningRecord> findById(Long id) {

        return runningRepository.findById(id);

    }


}
