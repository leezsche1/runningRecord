package excercising.exercising.domain;

import excercising.exercising.dto.RunningRecordDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class RunningRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Member member;

    private Integer totalKm;
    private Integer averagePace;
    private Integer bestPace;
    private Integer runningTime; // 칼로리
    private Integer kcal; // 평균 케이던스
    private Integer averageCadence; // 고도 상승
    private Integer elevationGain; // 고도 하강
    private Integer elevationLoss; // 평균 심박수
    private Integer averageHeartRate; // 최대 심박수
    private Integer maximumHeartRate; // 구간

    @OneToMany(mappedBy = "runningRecord", cascade = CascadeType.ALL)
    private List<SegmentTime> segmentTimeList = new ArrayList<>();

    @OneToMany(mappedBy = "runningRecord", cascade = CascadeType.ALL)
    private List<RunningRoute> runningRouteList = new ArrayList<>();

    public void addSegmentTimeList(SegmentTime segmentTime) {
        segmentTime.setRunningRecord(this);
        this.segmentTimeList.add(segmentTime);
    }

    public void addRunningRouteList(RunningRoute runningRoute) {
        runningRoute.setRunningRecord(this);
        this.runningRouteList.add(runningRoute);
    }


    public static RunningRecord runningDTOToEntity(RunningRecordDTO runningRecordDTO) {

        RunningRecord runningRecord = new RunningRecord();
        runningRecord.setTotalKm(runningRecordDTO.getTotalKm());
        runningRecord.setAveragePace(runningRecordDTO.getAveragePace());
        runningRecord.setBestPace(runningRecordDTO.getBestPace());
        runningRecord.setRunningTime(runningRecordDTO.getRunningTime());
        runningRecord.setKcal(runningRecordDTO.getKcal());
        runningRecord.setAverageCadence(runningRecordDTO.getAverageCadence());
        runningRecord.setElevationGain(runningRecordDTO.getElevationGain());
        runningRecord.setElevationLoss(runningRecordDTO.getElevationLoss());
        runningRecord.setAverageHeartRate(runningRecordDTO.getAverageHeartRate());
        runningRecord.setMaximumHeartRate(runningRecordDTO.getMaximumHeartRate());

        return runningRecord;
    }


}
