package main.text;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Locale;

@Getter
@Setter
public class GameIncident {
    private LocalDateTime incidentTime;
    private GameParticipant attacker;
    private GameParticipant victim;
    private Boolean successfulEscape;

    public GameIncident(GameParticipant attacker, GameParticipant victim, boolean escape) {
        this.incidentTime = LocalDateTime.now();
        this.attacker = attacker;
        this.victim = victim;
        this.successfulEscape = escape;
    }

}
