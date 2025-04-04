package main.text;

import org.junit.Test;
import static org.junit.Assert.*;

public class DomainModelTest {
    @Test
    public void testCreationOfUniverse() {
        UniversalBeing universe = new UniversalBeing("Вселенная-1");
        assertNotNull(universe);
        assertTrue(universe.getInhabitants().isEmpty());
    }

    @Test
    public void testAddingBeing() {
        UniversalBeing universe = new UniversalBeing("Вселенная-2");
        Being being = new Being("TestBeing");

        universe.addInhabitant(being);
        assertEquals(1, universe.getInhabitants().size());
        assertTrue(universe.getInhabitants().contains(being));
    }

    @Test
    public void testCreatingGame() {
        UniversalBeing universe = new UniversalBeing("Вселенная-3");
        Game game = new Game(universe);

        assertNotNull(game);
        assertFalse(game.getCompleted());
        game.complete();
        assertTrue(game.getCompleted());

    }

    @Test
    public void testGameParticipant() {
        Being being = new Being("TestBeing");
        GameParticipant participant = new GameParticipant(being);

        assertEquals(0, (int) participant.getNumberOfHits());
        assertEquals(0, (int) participant.getNumberOfEscapes());
        participant.recordEscape();
        participant.recordHit();
        assertEquals(1, (int) participant.getNumberOfHits());
        assertEquals(1, (int) participant.getNumberOfEscapes());

    }

    @Test
    public void testGameStartWith1IncidentSuccessEscape() {
        UniversalBeing universalBeing = new UniversalBeing("1");
        Game game = new Game(universalBeing);
        Being being = new Being("TestBeing");
        Being being2 = new Being("TestBeing2");
        GameParticipant gameParticipant = new GameParticipant(being);
        GameParticipant gameParticipant2 = new GameParticipant(being2);
        being.participateInGame(gameParticipant);
        being2.participateInGame(gameParticipant2);
        game.addParticipant(gameParticipant);
        game.addParticipant(gameParticipant2);
        GameIncident incident = new GameIncident(gameParticipant, gameParticipant2, true);
        game.recordIncident(incident);
        game.start();
        assertEquals(1, (int) gameParticipant.getNumberOfHits());
        assertEquals(1, (int) gameParticipant.getNumberOfEscapes());
        assertEquals(1, (int) gameParticipant2.getNumberOfMisses());
    }

    @Test
    public void testGameStartWith2IncidentSuccessEscape() {
        UniversalBeing universalBeing = new UniversalBeing("1");
        Game game = new Game(universalBeing);
        Being being = new Being("TestBeing");
        Being being2 = new Being("TestBeing2");
        GameParticipant gameParticipant = new GameParticipant(being);
        GameParticipant gameParticipant2 = new GameParticipant(being2);
        being.participateInGame(gameParticipant);
        being2.participateInGame(gameParticipant2);
        game.addParticipant(gameParticipant);
        game.addParticipant(gameParticipant2);
        GameIncident incident = new GameIncident(gameParticipant, gameParticipant2, true);
        GameIncident incident2 = new GameIncident(gameParticipant2, gameParticipant, false);
        game.recordIncident(incident);
        game.recordIncident(incident2);
        game.start();
        assertEquals(1, (int) gameParticipant.getNumberOfHits());
        assertEquals(1, (int) gameParticipant2.getNumberOfHits());
        assertEquals(1, (int) gameParticipant.getNumberOfEscapes());
        assertEquals(0, (int) gameParticipant2.getNumberOfEscapes());
        assertEquals(1, (int) gameParticipant2.getNumberOfMisses());
        assertEquals(1, (int) gameParticipant.getNumberOfMisses());
    }
}