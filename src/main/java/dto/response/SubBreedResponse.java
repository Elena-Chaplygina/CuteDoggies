package dto.response;

import java.util.ArrayList;

public class SubBreedResponse {
    private ArrayList<String> message;
    private String status;


    public ArrayList<String> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<String> message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
