package samples;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import model.Content;
import model.Genre;
import model.Rate;

class ContentCreator {
    static Random random = new Random(System.currentTimeMillis());

    protected static Content CreateContent(int number, String channelID) {
        String ID = Content.GenerateID();
        String Name = "Content #" + number + " for Channel " + channelID;
        String Description = "Description for content #" + number + " of channel " + channelID;
        Rate ContentRate = Rate.values()[random.nextInt(Rate.values().length)];
        ArrayList<Genre> Genres = RandomGenres();
        double Score = random.nextDouble() * 10;
        long Views = random.nextInt(1000000) + 1;
        long Likes = (long) (random.nextDouble() * Views);
        String[][] Info = new String[][] { { "Length", Integer.toString(random.nextInt(9) * 10 + 90) } };
        boolean Visibility = random.nextInt(3) != 0;
        URL Poster = RandomPoster();

        return new Content(ID, Name, Description, ContentRate, Genres, Score, Views, Likes, Info, Visibility, Poster);
    }

    private static ArrayList<Genre> RandomGenres() {
        ArrayList<Genre> genres = new ArrayList<Genre>();

        for (int i = 0; i < random.nextInt(5) + 1; i++) {
            Genre g = Genre.values()[random.nextInt(Genre.values().length)];
            if (!genres.contains(g))
                genres.add(g);
        }

        return genres;
    }

    private static URL RandomPoster() {
        String[] Urls = new String[] { "http://www.metta.org.uk/travel/images/ridedominicrep.jpg",
                "http://www.metta.org.uk/travel/images/mist2.jpg", "http://www.metta.org.uk/travel/images/galtab.jpg",
                "http://www.metta.org.uk/travel/images/devon_ss.jpg",
                "http://www.metta.org.uk/travel/images/gondala_pole.jpg",
                "http://www.metta.org.uk/travel/images/venicewin.jpg",
                "http://www.metta.org.uk/travel/images/venicecan.jpg",
                "http://www.metta.org.uk/travel/images/widecomdusk.jpg",
                "http://www.metta.org.uk/travel/images/bird1.jpg",
                "http://www.metta.org.uk/travel/images/pond_scape.jpg" };

        try {
            return new URL(Urls[random.nextInt(Urls.length)]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
