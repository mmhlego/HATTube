package tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageDownloader {
    private static boolean Downloaded = false;

    public static boolean isDownloaded() {
        return Downloaded;
    }

    public static void setDownloaded(boolean downloaded) {
        Downloaded = downloaded;
    }

    public static void DownloadImage(String imageUrl, String imageName, boolean changeDownloaded) {
        System.out.println("Starting download");

        String ImagePath = "resource/images/posters/" + imageName + GetImageFormat(imageUrl);
        InputStream ImageFile = null;

        try {
            ImageFile = new URL(imageUrl).openStream();
            Files.copy(ImageFile, Paths.get(ImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ImageFile != null) {
                try {
                    ImageFile.close();
                } catch (IOException e) {
                }
            }
        }

        System.out.println("Download Finished");

        Downloaded = (changeDownloaded) ? true : Downloaded;
    }

    public static String GetImageFormat(String imageUrl) {
        return imageUrl.substring(imageUrl.lastIndexOf("."), imageUrl.length());
    }
}
