
package Core;

import Tool.ConsoleInputter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class PlayerList extends ArrayList<Player> {

    static String playerIdPat = "^P\\d{4}$";
    static String clubIdPat = "^CL-\\d{4}$";
    static String positionPat = "(?i)Goalkeeper|Defender|Midfielder|Forward|Winger";
    
    ClubList clubList;

    public PlayerList(ClubList clubList) {
        this.clubList = clubList;
    }

    
    
    // Display
    public void display() {

        if (this.isEmpty()) {
            System.out.println("No player found.");
            return;
        }

        System.out.println("--------------------------------------------------------------------");
        System.out.println("Player ID | Club ID |    Player Name   |   Position   | Shirt Number");
        System.out.println("--------------------------------------------------------------------");

        for (Player p : this) {
            System.out.format(
                    "%-9s | %-7s | %-16s | %-12s | %-10d\n",
                    p.getPlayerId(),
                    p.getClubId(),
                    p.getPlayerName(),
                    p.getPosition(),
                    p.getShirtNumber());
        }

        System.out.println("--------------------------------------------------------------------");
    }

    // 6. List all players in ascending order of club names
    public void listPlayersSortedByClubName() {
        if (this.isEmpty()) {
            System.out.println("No player.");
            return;
        }
        
        PlayerList resultList = new PlayerList(clubList);
        resultList.addAll(this);
        
        int n = resultList.size();
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                Player p1 = resultList.get(j);
                Player p2 = resultList.get(j + 1);
                String clubName1 = clubList.getClubNameById(p1.getClubId());
                String clubName2 = clubList.getClubNameById(p2.getClubId());
                boolean needSwap = false;
                // Sort by club name ascending
                if (clubName1.compareToIgnoreCase(clubName2) > 0) {
                    needSwap = true;
                }
                // Same club -> sort by shirt number ascending
                else if (clubName1.equalsIgnoreCase(clubName2)
                        && p1.getShirtNumber() > p2.getShirtNumber()) {
                    needSwap = true;
                }
                if (needSwap) {
                    resultList.set(j, p2);
                    resultList.set(j + 1, p1);
                }
            }
        }
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Player ID |         Club Name        |    Player Name   |   Position   | Shirt Number");
        System.out.println("--------------------------------------------------------------------");
        
        for (Player p : this) {
            System.out.format(
                    "%-9s | %-24s | %-16s | %-12s | %-10d\n",
                    p.getPlayerId(),
                    this.clubList.getClubNameById(p.getClubId()),
                    p.getPlayerName(),
                    p.getPosition(),
                    p.getShirtNumber());
        }

        System.out.println("--------------------------------------------------------------------");
        
    }

    // 7. Search players by partial player name match
    public void searchPlayersByName() {

        PlayerList resultList = new PlayerList(clubList);

        String searchedName = ConsoleInputter.getStr("Player name for searching").toLowerCase().trim();

        for (Player p : this) {

            String playerName = p.getPlayerName().toLowerCase();

            if (playerName.contains(searchedName)) {
                resultList.add(p);
            }
        }

        resultList.display();
    }
    

    // 8. Add a new player
    public void addPlayer() {

        String playerId;
        String clubId;
        String playerName;
        String position;
        int shirtNumber;
        int pos;
        boolean duplicated;

        // Player ID
        do {
            playerId = ConsoleInputter.getStr("Enter player ID: ", playerIdPat, "Player ID must be in format Pxxxx");

            pos = this.indexOf(new Player(playerId));

            if (pos >= 0)
                System.out.println("This player ID already exists!");

        } while (pos >= 0);

        // Club ID
        do {
            clubId = ConsoleInputter.getStr("Enter club ID: ", clubIdPat, "Club ID must be in format CL-xxxx");

            pos = this.clubList.indexOf(new Club(clubId));

            if (pos < 0)
                System.out.println("This club does not exist!");

        } while (pos < 0);

        // Player Name
        do {
            playerName = ConsoleInputter.getStr("Enter player name: ");

            if (playerName.isEmpty())
                System.out.println("Player name cannot be empty!");

        } while (playerName.isEmpty());

        // Position
        position = ConsoleInputter.getStr(
                "Enter position: ",
                positionPat,
                "Position must be Goalkeeper, Defender, Midfielder, Forward or Winger");

        // Shirt Number
        do {
            duplicated = false;

            shirtNumber = ConsoleInputter.getInt(
                    "Enter shirt number: ", 1, 99);
            
            //Kiem tra xem so ao da co trong cau lac bo chua
            for (Player p : this) {
                if (p.getClubId().equalsIgnoreCase(clubId)
                        && p.getShirtNumber() == shirtNumber) {
                    duplicated = true;
                    System.out.println("This shirt number already exists in this club!");
                    break;
                }
            }

        } while (duplicated);

        // Add new player
        this.add(new Player(playerId, clubId, playerName, position, shirtNumber));

        System.out.println("Player added successfully.");
    }
    
    // 9. Remove a player with ID
    
    public void removePlayer() {
        String playerId;

        playerId = ConsoleInputter.getStr("Player ID for remove", playerIdPat, "Player ID must be in format Pxxxx");

        int pos = this.indexOf(new Player(playerId));

        if (pos < 0)
            System.out.println("This player does not exist.");
        else {
            Player p = this.get(pos);

            System.out.println("Player Information");

            String msg = "-----------------------------------" + "\n"
                    + "Player ID   : " + p.getPlayerId() + "\n"
                    + "Club ID     : " + p.getClubId() + "\n"
                    + "Player Name : " + p.getPlayerName() + "\n"
                    + "Position    : " + p.getPosition() + "\n"
                    + "Shirt Number: " + p.getShirtNumber() + "\n"
                    + "-----------------------------------";

            System.out.println(msg);

            boolean response = ConsoleInputter.getBoolean(
                    "Do you really want to remove this player");

            if (response == true) {
                this.remove(pos);
                System.out.println("The player has been successfully deleted.");
            }
        }
    }
    
    // 10. Update a player by ID
    
    public void updatePlayer() {

        String playerId = ConsoleInputter.getStr("Player ID for update: ")
                .toUpperCase().trim();

        int pos = this.indexOf(new Player(playerId));

        if (pos < 0) {
            System.out.println("This player does not exist.");
            return;
        }

        Player p = this.get(pos);

        String input;

        String clubId = p.getClubId();
        String playerName = p.getPlayerName();
        String position = p.getPosition();
        int shirtNumber = p.getShirtNumber();

        // Club ID
        while (true) {
            input = ConsoleInputter.getStr("Club ID (Enter to skip the update for this field)").trim();

            if (input.isEmpty()) break;

            if (!input.matches(clubIdPat)) {
                System.out.println("Club ID must be CL-xxxx");
                continue;
            }

            if (this.clubList.indexOf(new Club(input)) < 0) {
                System.out.println("This club does not exist");
                continue;
            }

            clubId = input;
            break;
        }

        // Player Name
        input = ConsoleInputter.getStr("Player Name (Enter to skip the update for this field)").trim();
        if (!input.isEmpty()) {
            playerName = input;
        }

        // Position
        while (true) {
            input = ConsoleInputter.getStr("Position (Enter to skip the update for this field)").trim();

            if (input.isEmpty()) break;

            if (!input.matches(positionPat)) {
                System.out.println("Invalid position (Goalkeeper/Defender/Midfielder/Forward/Winger)");
                continue;
            }

            position = input;
            break;
        }

        // Shirt Number
        while (true) {
            input = ConsoleInputter.getStr("Shirt Number (1-99, Enter to skip the update for this field)").trim();

            if (input.isEmpty()) break;

            if (!input.matches("\\d+")) {
                System.out.println("Shirt number must be a number");
                continue;
            }

            int num = Integer.parseInt(input);

            if (num < 1 || num > 99) {
                System.out.println("Shirt number must be 1-99");
                continue;
            }

            boolean duplicated = false;

            for (Player other : this) {
                if (!other.getPlayerId().equalsIgnoreCase(playerId)
                        && other.getClubId().equalsIgnoreCase(clubId)
                        && other.getShirtNumber() == num) {

                    duplicated = true;
                    System.out.println("Shirt number already exists in this club!");
                    break;
                }
            }

            if (duplicated) continue;

            shirtNumber = num;
            break;
        }

        // Update a player
        p.setClubId(clubId);
        p.setPlayerName(playerName);
        p.setPosition(position);
        p.setShirtNumber(shirtNumber);

        System.out.println("The player has been updated.");
    }
    
    // 11. List all players by a specific position
    
    public void listPlayersByPosition() {

        String position;
        boolean found = false;

        position = ConsoleInputter.getStr(
                "Enter position",
                positionPat,
                "Position must be Goalkeeper, Defender, Midfielder, Forward or Winger");

        System.out.println("--------------------------------------------------------------------");
        System.out.println("Player ID | Club ID |    Player Name   |   Position   | Shirt Number");
        System.out.println("--------------------------------------------------------------------");

        for (Player p : this) {

            if (p.getPosition().equalsIgnoreCase(position)) {

                System.out.format(
                        "%-9s | %-7s | %-16s | %-12s | %-10d\n",
                        p.getPlayerId(),
                        p.getClubId(),
                        p.getPlayerName(),
                        p.getPosition(),
                        p.getShirtNumber());

                found = true;
            }
        }

        System.out.println("--------------------------------------------------------------------");

        if (!found)
            System.out.println("No player found with this position.");
    }
    
    // 12. Save data to file
    
    public void saveToFile(String fName) {

        if (this.isEmpty())
            System.out.println("No player.");
        else {
            try {
                PrintWriter fw = new PrintWriter(fName);

                for (Player p : this)
                    fw.println(p.toStringOnFile());

                fw.close();

            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    
    // Doc file
    public void readFile(String fName) {
        File f = new File(fName);
        if (!f.exists())
            System.out.println("The file doesn't exist!");
        else {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader bf = new BufferedReader(fr);

                String line;
                while ((line = bf.readLine()) != null) {
                    String[] parts = line.split(",");

                    String playerId = parts[0].trim();
                    String clubId = parts[1].trim();
                    String name = parts[2].trim();
                    String position = parts[3].trim();
                    int shirtNumber = Integer.parseInt(parts[4].trim());

                    Player p = new Player(playerId, clubId, name, position, shirtNumber);
                    this.add(p);
                }

                bf.close();
                fr.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
