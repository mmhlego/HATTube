package api;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

public class Uploader {
    public static URL UploadImage(String path) {
        try {
            return UploadImage(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static URL UploadImage(File image) {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "mmhlego", "api_key", "649523878373588",
                "api_secret", "yl_NlnXusn4v4_10jnAXnmOjYDs"));
        // cloudinar

        // CLOUDINARY_URL=cloudinary://649523878373588.yl_NlnXusn4v4_10jnAXnmOjYDs@mmhlego
        try {
            Map uploadResult = cloudinary.uploader().upload(image, ObjectUtils.emptyMap());
            return new URL((String) uploadResult.get("url"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
