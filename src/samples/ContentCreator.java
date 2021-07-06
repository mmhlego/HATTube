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
        String[] Urls = new String[] {
                "https://s18.picofile.com/file/8437705634/MV5BYjkxYzE3ODktZjExMi00YmM0LTgwNTMtMmU2OTE3ZDI0NDQzXkEyXkFqcGdeQXVyODEyMDIxNDY_V1_QL75_UY207_CR4_0_140_207_.jpg",
                "https://s18.picofile.com/file/8437705850/MV5BODNiODVmYjItM2MyMC00ZWQyLTgyMGYtNzJjMmVmZTY2OTJjXkEyXkFqcGdeQXVyNzk3NDUzNTc_V1_QL75_UX140_CR0_0_140_207_.jpg",
                "https://s18.picofile.com/file/8437705918/MV5BMTE2ODU4NDEtNmRjNS00OTk1LTg4NmMtNTAzYzVlNzJmYjgzXkEyXkFqcGdeQXVyODk4OTc3MTY_V1_QL75_UX140_CR0_0_140_207_.jpg",
                "https://s18.picofile.com/file/8437705968/MV5BOWI5YTUxOWEtZmRiZS00ZmQxLWE2NzctYTRiODA2NzE1ZjczXkEyXkFqcGdeQXVyMDM2NDM2MQ_V1_QL75_UX140_CR0_0_140_207_.jpg",
                "https://s18.picofile.com/file/8437706000/MV5BZTQyNTU0MDktYTFkYi00ZjNhLWE2ODctMzBkM2U1ZTk3YTMzXkEyXkFqcGdeQXVyNTI4MzE4MDU_V1_QL75_UX140_CR0_0_140_207_.jpg",
                "https://s18.picofile.com/file/8437706142/MV5BNGVjNWI4ZGUtNzE0MS00YTJmLWE0ZDctN2ZiYTk2YmI3NTYyXkEyXkFqcGdeQXVyMTkxNjUyNQ_V1_QL75_UX140_CR0_0_140_207_.jpg",
                "https://s19.picofile.com/file/8437706176/MV5BMjhiMzgxZTctNDc1Ni00OTIxLTlhMTYtZTA3ZWFkODRkNmE2XkEyXkFqcGdeQXVyNzkwMjQ5NzM_V1_QL75_UY207_CR5_0_140_207_.jpg",
                "https://s19.picofile.com/file/8437706268/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM_V1_QL75_UX140_CR0_0_140_207_.jpg",
                "https://s18.picofile.com/file/8437706384/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw_V1_QL75_UX140_CR0_0_140_207_.jpg",
                "https://s19.picofile.com/file/8437706442/MV5BYWZjMjk3ZTItODQ2ZC00NTY5LWE0ZDYtZTI3MjcwN2Q5NTVkXkEyXkFqcGdeQXVyODk4OTc3MTY_V1_QL75_UX140_CR0_0_140_207_.jpg" };

        try {
            return new URL(Urls[random.nextInt(Urls.length)]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
