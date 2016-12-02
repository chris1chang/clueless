package app.game.action;

import app.exception.GameModelException;
import app.game.model.*;

/**
 * Created by james on 11/26/16.
 */
public class EndTurn implements Action {

    Player player;
    String character;
    String weapon;
    String room;

    public EndTurn(Player player, String character, String weapon, String room) {
        this.player = player;
        this.character = character;
        this.weapon = weapon;
        this.room = room;
    }

    /*
    IsLegal(gameState: ClueLessModel): boolean
    (1) The game status must be “started and in progress.” (2) It
    must be the Player’s token’s turn. (3) The token must have
    made a suggestion OR have moved and is in a hallway OR has
    no legal move and is unable to make a suggestion. If all of
    those conditions hold true, then the operation returns True.
    Otherwise, it returns false.
    */

    public boolean isLegal(GameModel model) {
        boolean legal = false;
        try {
            boolean activeGame = model.getStatus() == GameStatus.ACTIVE;
            boolean yourTurn = model.getTurn().getWho() == player;
            boolean madeSuggestion = model.getTurn().getHasSuggested();
            boolean hasMoved = model.getTurn().getHasMoved();
            boolean inHallway = player.getCharacter().getSpace().name.equals(GameProperty.HALLWAY.name());

        }
        catch (GameModelException e) {
            // not legal. stay false
        }
        // TODO: catch NullPointerException (and return false)
        return legal;
    }

    /*
    The turn ends for the requesting player’s token. The token’s
MovedBySuggestion attribute is set to False. The
ClueLessModel call’s the Turn’s NextTurn operation with the
next token in the TurnOrder.
     */
    public void apply(GameModel model) {
        model.getTurn().setMovedBySuggestion(false);
        model.getTurn().nextTurn(player);
    }
}
