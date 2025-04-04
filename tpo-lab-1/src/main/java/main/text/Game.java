package main.text;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Game {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer numberOfParticipants;
    private Boolean completed;
    private UniversalBeing universe;
    private Set<GameParticipant> participants;
    private List<GameIncident> incidents;

    public Game(UniversalBeing universe) {
        this.universe = universe;
        this.startTime = LocalDateTime.now();
        this.endTime = null;
        this.numberOfParticipants = 0;
        this.completed = false;
        this.participants = new HashSet<>();
        this.incidents = new ArrayList<>();
    }

    public void addParticipant(GameParticipant being) {
        participants.add(being);
        numberOfParticipants++;
    }

    public void recordIncident(GameIncident incident) {
        incidents.add(incident);
    }

    public void complete() {
        this.completed = true;
        this.endTime = LocalDateTime.now();
    }

    public void start() {
        GameParticipant participant1 = participants.iterator().next();
        GameParticipant participant2 = participants.iterator().next();
        for (GameIncident gameIncident : incidents) {
            gameIncident.getAttacker().recordHit();
            if (gameIncident.getSuccessfulEscape()) {
                gameIncident.getAttacker().recordEscape();
            }
            gameIncident.getVictim().recordMiss();
        }
        universe.addInhabitant(participant2.getPlayer());
        universe.addInhabitant(participant1.getPlayer());
        complete();
        universe.addGame(this);
    }
}