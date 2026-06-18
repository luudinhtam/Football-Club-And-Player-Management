
package Core;

import java.util.Objects;

/**
 *
 * @author acer
 */

public class Club {

    // Attributes of Club
    private String clubId; // Id cua cau lac bo
    private String clubName; // Ten cua cau lac bo
    private String sponsorBrand; // Ten cua thuong hieu tai tro
    private double budget; // Ngan sach cua cau lac bo

    public Club(String clubId, String clubName, String sponsorBrand, double budget) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.sponsorBrand = sponsorBrand;
        this.budget = budget;
    }

    public Club(String clubId) {
        this.clubId = clubId;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getSponsorBrand() {
        return sponsorBrand;
    }

    public void setSponsorBrand(String sponsorBrand) {
        this.sponsorBrand = sponsorBrand;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Club)) {
            return false;
        }

        Club other = (Club) obj;
        return Objects.equals(clubId, other.clubId);
    }

    @Override
    public String toString() {
        return clubId + ", "
                + clubName + ", "
                + sponsorBrand + ", "
                + budget;
    }
    
    
    public String toStringOnFile() {
        return clubId + ", "
                + clubName + ", "
                + sponsorBrand + ", "
                + budget;
    }
}
