package excercising.exercising.controller;

import excercising.exercising.domain.Route;
import excercising.exercising.domain.RunningRecord;
import excercising.exercising.domain.RunningRoute;
import excercising.exercising.domain.SegmentTime;
import excercising.exercising.dto.*;
import excercising.exercising.service.RunningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RunningController {

    private final RunningService runningService;

    @PostMapping("/running")
    public String runningCon(@RequestBody RunningRecordDTO runningRecordDTO) {


        List<SegmentTime> segmentTimeList = runningRecordDTO.getSegmentTimeDTO().stream().map(m -> new SegmentTime(m.getKm(), m.getAveragePace(), m.getDifference(), m.getElevation())).collect(Collectors.toList());

        List<RunningRoute> runningRouteList = runningRecordDTO.getRunningRouteDTO().stream().map(m -> new RunningRoute(m.getKm(),
                        m.getRouteDTOList().stream().map(mm -> new Route(mm.getLatitude(), mm.getLongitude())).collect(Collectors.toList()))).
                collect(Collectors.toList());


        RunningRecord runningRecord = RunningRecord.runningDTOToEntity(runningRecordDTO);
//        runningRecord.setSegmentTimeList(segmentTimeList);
//        runningRecord.setRunningRouteList(runningRouteList);

        for (SegmentTime segmentTime : segmentTimeList) {
            runningRecord.addSegmentTimeList(segmentTime);
        }
        for (RunningRoute runningRoute : runningRouteList) {
//            for (Route route : runningRoute.getRouteList()) {
//                runningRoute.addRouteList(route);
//            }
            runningRecord.addRunningRouteList(runningRoute);
        }


        runningService.save(runningRecord);

        return "good";

    }

    @PostMapping("/runningResult")
    public ResponseEntity runningResult(@RequestBody RunningResultDTO runningResultDTO) {
        Optional<RunningRecord> findingRunningRecord = runningService.findById(runningResultDTO.getId());
        RunningRecord runningRecord = findingRunningRecord.get();

        RunningRecordDTO runningRecordDTO = new RunningRecordDTO();
        runningRecordDTO.setTotalKm(runningRecord.getTotalKm());
        runningRecordDTO.setAveragePace(runningRecord.getAveragePace());
        runningRecordDTO.setBestPace(runningRecord.getBestPace());
        runningRecordDTO.setRunningTime(runningRecord.getRunningTime());
        runningRecordDTO.setKcal(runningRecord.getKcal());
        runningRecordDTO.setAverageCadence(runningRecord.getAverageCadence());
        runningRecordDTO.setElevationGain(runningRecord.getElevationGain());
        runningRecordDTO.setElevationLoss(runningRecord.getElevationLoss());
        runningRecordDTO.setAverageHeartRate(runningRecord.getAverageHeartRate());
        runningRecordDTO.setMaximumHeartRate(runningRecord.getMaximumHeartRate());

        List<SegmentTimeDTO> segmentTimeDTOList = runningRecord.getSegmentTimeList().stream().map(m -> new SegmentTimeDTO(m.getKm(), m.getAveragePace(), m.getDifference(), m.getElevation()))
                .collect(Collectors.toList());

        List<RunningRouteDTO> runningRouteDTOList = runningRecord.getRunningRouteList().stream().map(m -> new RunningRouteDTO(m))
                .collect(Collectors.toList());



        runningRecordDTO.setSegmentTimeDTO(segmentTimeDTOList);
        runningRecordDTO.setRunningRouteDTO(runningRouteDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(runningRecordDTO);
    }
}
