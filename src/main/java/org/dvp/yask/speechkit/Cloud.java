package org.dvp.yask.speechkit;

import com.google.gson.Gson;
import org.dvp.yask.speechkit.auth.Token;
import org.dvp.yask.speechkit.exception.ClientException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Cloud Аутентификация в облаке и выполнение задачи
 */
public class Cloud extends Request {
    /**
     * Наименование параметра "OAUTH-токен"
     */
    private final static String OAUTH_TOKEN = "yandexPassportOauthToken";

    /**
     * Наименование параметра "ID каталога"
     */
    private final static String FOLDER_ID = "folderId";

    /**
     * Заголовки web-запроса
     */
    private Map<String, String> headers;

    /**
     * Параметры задачи
     */
    private Map<String, String> task;

    public Cloud(String oAuthToken, String folderId) throws ClientException, InterruptedException, IOException, URISyntaxException {
        if (folderId.length() > Limit.FOLDER_ID_LENGTH) {
            throw new ClientException(Message.LENGTH_ERROR);
        }
        headers = new HashMap<>();
        task = new HashMap<>();
        String iamToken = getIAMToken(oAuthToken);
        setAuthHeaders(iamToken);

        task.put(FOLDER_ID, folderId);
    }

    private String getIAMToken(String oAuthToken) throws InterruptedException, IOException, URISyntaxException {
        String s = new String(send(URL.IAM_TOKEN, String.format("{'%s':'%s'}", OAUTH_TOKEN, oAuthToken)));
        Gson gson = new Gson();
        Token token = gson.fromJson(s, Token.class);
        return token.getIamToken();
    }

    private void setAuthHeaders(String iamToken) {
        headers.put("Authorization",
                (new StringBuilder().append("Bearer ").append(iamToken).toString()));
    }

    public byte[] request(Task task) throws InterruptedException, IOException, URISyntaxException {
        task.addParam(this.task);
        return send(task.getURL(), task.getParam(), headers);
    }

}
