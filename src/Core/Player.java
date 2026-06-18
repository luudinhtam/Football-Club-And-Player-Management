
package Core;

import java.util.Objects;

/**
 *
 * @author acer
 */

public class Player {

    private String playerId; // Id cua cau thu
    private String clubId; // Id cua cau lac bo
    private String playerName; // Ten cua cau thu
    private String position; // Vi tri cua cau thu
    private int shirtNumber; // So ao cua cau thu

    public Player(String playerId, String clubId, String playerName,
                  String position, int shirtNumber) {
        this.playerId = playerId;
        this.clubId = clubId;
        this.playerName = playerName;
        this.position = position;
        this.shirtNumber = shirtNumber;
    }

    public Player(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Player)) {
            return false;
        }
        Player other = (Player) obj;
        return Objects.equals(playerId, other.playerId);
    }

    @Override
    public String toString() {
        return playerId + ", "
                + clubId + ", "
                + playerName + ", "
                + position + ", "
                + shirtNumber;
    }
    
    public String toStringOnFile() {
        return playerId + ", "
                + clubId + ", "
                + playerName + ", "
                + position + ", "
                + shirtNumber;
    }
}
