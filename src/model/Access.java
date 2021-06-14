package model;

public enum Access {
    Level(1), CreateChannel(2), EditChannel(3), RestrictChannel(4), CreateContent(5), EditContent(6),
    RestrictContent(7), EditUser(8), BanUser(9), EditAdmin(10), BanAdmin(11);

    int Location;

    private Access(int location) {
        Location = location;
    }
}
