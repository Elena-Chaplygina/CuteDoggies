import okhttp3.*;
import org.aeonbits.owner.ConfigFactory;
import utils.AppConfig;

import java.io.IOException;

public class StorageUploader {
    private static final String BASE_URL_DISK = "https://cloud-api.yandex.net/v1/disk/resources/upload";
    static AppConfig config = ConfigFactory.create(AppConfig.class);

    public static void postFile(String urlDog, String breed) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String addPath = String.format("?path=%s.jpg&url=%s", breed, urlDog);
        Request request = new Request.Builder()
                .url(BASE_URL_DISK + addPath)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", config.token())
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), ""))
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
