package main.text;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Being {
    private String name;
    private LocalDateTime appearanceTime;
    private List<GameParticipant> participants;
    private Boolean involvedInDebate;
    private UniversalBeing universe;

    public Being(String name) {
        this.name = name;
        this.appearanceTime = LocalDateTime.now();
        this.participants = new ArrayList<>();
        this.involvedInDebate = false;
        this.universe = null;
    }

    public void participateInGame(GameParticipant gameParticipant) {
        participants.add(gameParticipant);
    }

}