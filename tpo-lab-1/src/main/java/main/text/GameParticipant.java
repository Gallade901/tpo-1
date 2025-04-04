package main.text;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GameParticipant {
    private Being player;
    private LocalDateTime joinTime;
    private Integer numberOfHits;
    private Integer numberOfEscapes;
    private Integer numberOfMisses;

    public GameParticipant(Being player) {
        this.player = player;
        this.joinTime = LocalDateTime.now();
        this.numberOfHits = 0;
        this.numberOfEscapes = 0;
        this.numberOfMisses = 0;
    }

    public void recordHit() {
        numberOfHits++;
    }

    public void recordEscape() {
        numberOfEscapes++;
    }
    public void recordMiss() {numberOfMisses++;}

}
