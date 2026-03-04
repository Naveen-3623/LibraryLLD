package model;

public class Member {

    private int memberId;
    private String name;
    private int totalBorrowed;

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.totalBorrowed = 0;
    }

    public int getMemberId() { return memberId; }

    public String getName() { return name; }

    public int getTotalBorrowed() { return totalBorrowed; }

    public void increaseBorrowCount() {
        totalBorrowed++;
    }
}