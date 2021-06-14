package model;

public enum Rate {
    G(0, false), PG(13, false), PG13(13, true), R(17, true), NC17(17, true);

    int MinimumAge;
    boolean ParentsNeeded;

    private Rate(int minimumAge, boolean parentsNeeded) {
        MinimumAge = minimumAge;
        ParentsNeeded = parentsNeeded;
    }

    public boolean IsOkay(int age) {
        return age > MinimumAge;
    }
}
