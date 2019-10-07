package Amazon.full_time2020.final_round_加油少年_OOD;

import java.time.LocalDateTime;
import java.util.List;

public class TruckSystem {

    enum Transportation {
        truck, rail, plane
    }

    public class Event {
        private String name;
        private String startPosition, endPosition;
        private LocalDateTime startTime, endTime;
        private Transportation transportation;

        public Event(String name, String startPosition, String endPosition, LocalDateTime startTime, LocalDateTime endTime, Transportation transportation) {
            this.name = name;
            this.startPosition = startPosition;
            this.endPosition = endPosition;
            this.startTime = startTime;
            this.endTime = endTime;
            this.transportation = transportation;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setStartPosition(String startPosition) {
            this.startPosition = startPosition;
        }

        public void setEndPosition(String endPosition) {
            this.endPosition = endPosition;
        }

        public void setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }

        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }

        public void setTransportation(Transportation transportation) {
            this.transportation = transportation;
        }

        public String getName() {
            return name;
        }

        public String getStartPosition() {
            return startPosition;
        }

        public String getEndPosition() {
            return endPosition;
        }

        public LocalDateTime getStartTime() {
            return startTime;
        }

        public LocalDateTime getEndTime() {
            return endTime;
        }

        public Transportation getTransportation() {
            return transportation;
        }
    }

    public boolean checkValid(List<String> workLoad) {
        return true;
    }

}
