package samples;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import model.Link;

class LinkCreator {
    static Random random = new Random(System.currentTimeMillis());

    protected static Link CreateLink(int number, String contentID) {
        String ID = Link.GenerateID();
        String Name = "Link #" + number;
        String Description = RandomDescription();
        URL Url = RandomUrl();

        return new Link(ID, contentID, Name, Description, Url);
    }

    private static String RandomDescription() {
        String[] Encoders = new String[] { "mkvcage", "ganool" };
        return "Encoder : " + Encoders[random.nextInt(Encoders.length)];
    }

    private static URL RandomUrl() {
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
