import com.fasterxml.jackson.databind.ObjectMapper;
import dto.response.DogImageResponse;
import dto.response.SubBreedResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

public class ImageUploader {
    private static final String BASE_URL = "https://dog.ceo/api/breed/";
    private static final String GET_RANDOM_IMAGE = "/images/random";
    private static final String GET_SUBBREED_LIST = "/list";
    private static final Logger logger = LoggerFactory.getLogger(ImageUploader.class);

    public static String getMasterImage(String breed) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        Request request = new Request.Builder()
                .url(BASE_URL + breed.toLowerCase() + GET_RANDOM_IMAGE)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String jsonResponse = response.body().string();
            DogImageResponse dogImageResponse = objectMapper.readValue(jsonResponse, DogImageResponse.class);
            return dogImageResponse.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }


    public static ArrayList<String> getSubBreed(String breed) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        Request request = new Request.Builder()
                .url(BASE_URL + breed.toLowerCase() + GET_SUBBREED_LIST)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String jsonResponse = response.body().string();
            SubBreedResponse dogImageResponse = objectMapper.readValue(jsonResponse, SubBreedResponse.class);
            return dogImageResponse.getMessage();
        } catch (IOException e) {
            logger.error("Упс, такая порода нам не знакома: {}", e.getMessage(), e);
            throw e;
        }
    }

    public static String getSubBreedImage(String breed, String subBreed) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        Request request = new Request.Builder()
                .url(BASE_URL + breed.toLowerCase() + "/" + subBreed.toLowerCase() + GET_RANDOM_IMAGE)
                .build();
        try (Response response = client.newCall(request).execute()) {
       if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String jsonResponse = response.body().string();
            DogImageResponse dogImageResponse = objectMapper.readValue(jsonResponse, DogImageResponse.class);
            return dogImageResponse.getMessage();
        } catch (IOException e) {
            logger.error("Упс, такая порода нам не знакома: {}", e.getMessage(), e);
            throw e;
        }
    }


    public static String downloadFileToYaDisk(String breed) throws IOException {
        ArrayList<String> breeds = getSubBreed(breed);
        if (!breeds.isEmpty()) {
            for (String breedString : breeds) {
                StorageUploader.postFile(getSubBreedImage(breed, breedString), breedString);
                logger.info("Милое фото {} уже загружено. Эта порода - подпорода {}", breedString, breed);
            }
        } else {
            StorageUploader.postFile(getMasterImage(breed), breed);
            logger.info("Милое фото {} уже загружено", breed);
        }
        return null;
    }


}
