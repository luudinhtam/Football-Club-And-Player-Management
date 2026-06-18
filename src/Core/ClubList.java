
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
public class ClubList extends ArrayList<Club> {
    
    static String clubIdPat = "^CL-\\d{4}$";
    
    // Method display
    public void display() {

        if (this.isEmpty()) {
            System.out.println("No club found.");
            return;
        }

        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Club ID  | Club Name                 | Sponsor Brand      | Budget");
        System.out.println("----------------------------------------------------------------------------");

        for (Club c : this) {

            System.out.format(
                    "%-8s | %-25s | %-18s | %.2f\n",
                    c.getClubId(),
                    c.getClubName(),
                    c.getSponsorBrand(),
                    c.getBudget());
        }

        System.out.println("----------------------------------------------------------------------------");
    }
    
    // Method tim ten cua 1 Club bang Id
    public String getClubNameById(String clubId) {
        for (Club c : this) {
            if (c.getClubId().equalsIgnoreCase(clubId)) {
                return c.getClubName();
            }
        }
        return "";
    }
    
    // Cac Method chinh
    // 1. List all clubs
    public void listAllClubs() {
        if (this.isEmpty())
            System.out.println("The list is empty.");
        else {
            
            System.out.println("----------------------------------------------------------");
            System.out.println("Club ID |         Club Name        |   Sponsor Brand  |   Budget   ");
            System.out.println("----------------------------------------------------------");
            for (Club c : this)
                
                // Dấu - là giống hàng trái, không có '-' là giống hàng phải
                System.out.format("%-4s | %-24s | %-16s | %.2f \n", c.getClubId(), c.getClubName(), c.getSponsorBrand(), c.getBudget());
            // Đóng khung
            System.out.println("----------------------------------------------------------");
        }
    }
    
    // 2. Add a new club
    public void addClub() {
        String clubId;
        String clubName;
        String sponsor;
        double budget;
        int pos;

        // Club ID
        do {
            clubId = ConsoleInputter.getStr("Club ID: ", clubIdPat, "Club ID must be in format CL-xxxx");

            pos = this.indexOf(new Club(clubId));

            if (pos >= 0)
                System.out.println("This club ID already exists!");

        } while (pos >= 0);

        // Club Name
        do {
            clubName = ConsoleInputter.getStr("Club Name: ");

            if (clubName.isEmpty())
                System.out.println("Club name cannot be empty!");

        } while (clubName.isEmpty());

        // Sponsor Brand
        do {
            sponsor = ConsoleInputter.getStr("Sponsor Brand: ");

            if (sponsor.isEmpty())
                System.out.println("Sponsor brand cannot be empty!");

        } while (sponsor.isEmpty());

        // Budget
        budget = ConsoleInputter.getDouble("Budget: ", 0.01, Double.MAX_VALUE);

        // Add new club
        Club c = new Club(clubId, clubName, sponsor, budget);

        this.add(c);

        System.out.println("The club has been added.");
    }
    
    // 3. Search for a club by ID
    public void searchClubById() {
        String clubId;

        clubId = ConsoleInputter.getStr("Club ID for searching: ", clubIdPat, "Club ID must be in format CL-xxxx");

        int pos = this.indexOf(new Club(clubId));

        if (pos < 0)
            System.out.println("This club does not exist.");
        else {
            Club c = this.get(pos);

            System.out.println("Club Information");

            String msg = "-----------------------------------" + "\n"
                    + "Club ID      : " + c.getClubId() + "\n"
                    + "Club Name    : " + c.getClubName() + "\n"
                    + "Sponsor Brand: " + c.getSponsorBrand() + "\n"
                    + "Budget       : " + c.getBudget() + "\n"
                    + "-----------------------------------";

            System.out.println(msg);
        }
    }
    
    // 4. Update a club by ID
    public void updateClub() {
        String clubId;
        String clubName;
        String sponsorBrand;
        String input;
        double budget;

        clubId = ConsoleInputter.getStr("Club ID for update: ", clubIdPat, "Club ID must be in format CL-xxxx");

        int pos = this.indexOf(new Club(clubId));

        if (pos < 0)
            System.out.println("This club does not exist.");
        else {

            Club c = this.get(pos);

            System.out.println("Current Club Information");

            String msg = "-----------------------------------" + "\n"
                    + "Club ID      : " + c.getClubId() + "\n"
                    + "Club Name    : " + c.getClubName() + "\n"
                    + "Sponsor Brand: " + c.getSponsorBrand() + "\n"
                    + "Budget       : " + c.getBudget() + "\n"
                    + "-----------------------------------";

            System.out.println(msg);

            // Club Name
            input = ConsoleInputter.getStr("New Club Name (Enter to keep current): ");

            if (!input.isEmpty())
                c.setClubName(input);

            // Sponsor Brand
            input = ConsoleInputter.getStr("New Sponsor Brand (Enter to keep current): ");

            if (!input.isEmpty())
                c.setSponsorBrand(input);

            // Budget
            input = ConsoleInputter.getStr("New Budget (Enter to keep current): ");

            if (!input.isEmpty()) {
                // Su dung Try Catch de bat loi khi chuyen tu String -> Double. Input phai la so double, khong phai vi du: 12abc
                try {
                    budget = Double.parseDouble(input);

                    if (budget > 0)
                        c.setBudget(budget);
                    else
                        System.out.println("Budget must be positive. Current value kept.");
                } catch (Exception e) {
                    System.out.println("Invalid budget. Current value kept.");
                }
            }

            System.out.println("The club has been updated.");
        }
    }
    
    // 5. List all clubs with budgets less than or equal to an input value
    public void listClubsByBudget() {

        ClubList resultList = new ClubList();

        double budget = ConsoleInputter.getDouble("Enter maximum budget: ", 0 , Double.MAX_VALUE);

        for (Club c : this) {

            if (c.getBudget() <= budget)
                resultList.add(c);
        }

        resultList.display();
    }
    
    // Save to File
    public void saveToFile(String fName) {

        if (this.isEmpty())
            System.out.println("No club.");
        else {
            try {
                PrintWriter fw = new PrintWriter(fName);

                for (Club c : this)
                    fw.println(c.toStringOnFile());

                fw.close();

            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    
    // Read from File
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
                    
                    String clubId = parts[0].trim();
                    String clubName = parts[1].trim();
                    String sponsorBrand = parts[2].trim();
                    double budget = Double.parseDouble(parts[3].trim());
                    
                    Club c = new Club(clubId, clubName, sponsorBrand, budget);
                    this.add(c);
                }
                
                bf.close();
                fr.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
}
