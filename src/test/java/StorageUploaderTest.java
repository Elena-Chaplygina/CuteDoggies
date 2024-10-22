import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StorageUploaderTest {

    @Test
    public void postFileSuccess() throws IOException {
        OkHttpClient mockClient = mock(OkHttpClient.class);
        Response mockResponse = new Response.Builder()
                .request(new Request.Builder().url("http://localhost").build())
                .protocol(Protocol.HTTP_2)
                .code(202)
                .message("OK")
                .body(ResponseBody.create(null, ""))
                .build();
        when(mockClient.newCall(any(Request.class))).thenReturn(mock(Call.class));
        when(mockClient.newCall(any(Request.class)).execute()).thenReturn(mockResponse);

        StorageUploader uploader = new StorageUploader();
        uploader.postFile("http://example.com/dog.jpg", "bulldog");

    }



}