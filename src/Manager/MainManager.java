
package Manager;

import Core.ClubList;
import Core.PlayerList;
import Tool.ConsoleInputter;

/**
 *
 * @author acer
 */

public class MainManager {
    public static void main(String[] args) {
        
        // Khai bao ten va dinh dang FILE cua Club va Player
        String clubFile = "clubs.txt";
        String playerFile = "players.txt";
        
        // Danh sach cac cau lac bo phai co truoc
        ClubList clubList = new ClubList();
        clubList.readFile(clubFile); // Doc file chua thong tin ve cac Club
        
        // Danh sach cac cau thu phai co truoc
        PlayerList playerList = new PlayerList(clubList);
        playerList.readFile(playerFile); // Doc file chua thong tin ve cac Player
        
        // Các mục menu chép từ đề bài
        String[] options = {
                "List all clubs",
                "Add a new club",
                "Search for a club by ID", 
                "Update a club by ID",
                "List all clubs with budgets less than or equal to an input value",
                
                "List all players in ascending order of club names",
                "Search players by partial player name match",
                "Add a new player", 
                "Remove a player with ID", 
                "Update a player by ID", 
                "List all players by a specific position",
                "Save data to files",
                "Load data from files",
                "Quit program"
        };

        boolean clubChanged = false;
        boolean playerChanged = false;

        int choice = 0;

        
        do {
            System.out.println("\n ===== Football Club and Player Management ===== \n");
            choice = ConsoleInputter.intMenu(options);
            switch (choice) {
                case 1:
                    clubList.listAllClubs();
                    break;
                case 2:
                    clubList.addClub();
                    clubChanged = true;
                    break;
                case 3:
                    clubList.searchClubById();
                    break;
                case 4:
                    clubList.updateClub();
                    clubChanged = true;
                    break;
                case 5:
                    clubList.listClubsByBudget();
                    break;
                case 6:
                    playerList.listPlayersSortedByClubName();
                    break;
                case 7:
                    playerList.searchPlayersByName();
                    break;
                case 8:
                    playerList.addPlayer();
                    playerChanged = true;
                    break;
                case 9:
                    playerList.removePlayer();
                    playerChanged = false;
                    break;
                case 10:
                    playerList.updatePlayer();
                    playerChanged = false;
                    break;
                case 11:
                    playerList.listPlayersByPosition();
                    break;
                case 12:
                    playerList.saveToFile(playerFile);
                    clubList.saveToFile(clubFile);
                    
                    System.out.println("Data has been saved!");
                    break;
                case 13:
                    boolean loadResp = ConsoleInputter.getBoolean("Clear all current data and load data again? Y/N");
                    if (loadResp == true) {
                        playerList.readFile(playerFile);
                        clubList.readFile(clubFile);
                        System.out.println("Load data successfully!");
                    }
                    break;
                case 14:
                    if (playerChanged == true) { // user thoát program mà quên lưu file Player
                        boolean resp = ConsoleInputter.getBoolean("Save changes on Player List? Y/N");
                        if (resp == true) {
                            playerList.saveToFile(playerFile);
                            System.out.println("Data has been saved!");
                        }   
                    }
                    
                    if (clubChanged == true) { // user thoát program mà quên lưu file Club
                        boolean resp = ConsoleInputter.getBoolean("Save changes on Club List? Y/N");
                        if (resp == true) {
                            clubList.saveToFile(clubFile);
                            System.out.println("Data has been saved!");
                        }   
                    }
                    
                    System.out.println("Good Bye! See you again...");
            }
        } while (choice >= 1 && choice < options.length);
        
        
    }
}
