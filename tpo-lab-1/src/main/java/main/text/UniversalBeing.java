package main.text;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UniversalBeing {
    private String universeName;
    private LocalDateTime creationTime;
    private Set<Being> inhabitants;
    private Set<Game> activeGames;

    public UniversalBeing(String universeName) {
        this.universeName = universeName;
        this.creationTime = LocalDateTime.now();
        this.inhabitants = new HashSet<>();
        this.activeGames = new HashSet<>();
    }

    public void addInhabitant(Being being) {
        inhabitants.add(being);
        being.setUniverse(this);
    }

    public void addGame(Game game) {
        activeGames.add(game);
    }
}