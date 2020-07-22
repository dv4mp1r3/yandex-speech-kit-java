package org.dvp.yask.speechkit;

import org.dvp.yask.speechkit.exception.ClientException;

/**
 * Speech Формирование задачи для синтеза речи (Text-To-Speech, TTS)
 */
public class Speech extends Kit implements Task {

    /**
     * Наименования параметра "Текст"
     */
    private final static String TEXT = "text";

    /**
     * Наименования параметра "SSML"
     */
    private final static String SSML = "ssml";

    /**
     * Наименования параметра "Голос"
     */
    private final static String VOICE = "voice";

    /**
     * Наименования параметра "Эмоциональная окраска"
     */
    private final static String EMOTION = "emotion";

    /**
     * Наименования параметра "Темп"
     */
    private final static String SPEED = "speed";

    public Speech(String text) throws ClientException {
        if (text.length() > Limit.SYNTHESIZE_TEXT_LENGTH) {
            throw new ClientException(Message.LENGTH_ERROR);
        }
        task.put(TEXT, text);
    }

    public Speech setSSML() {
        String text = task.containsKey(SSML) ? task.get(SSML) : task.get(TEXT);
        task.put(SSML, text);
        task.remove(TEXT);
        return this;
    }

    public Speech setVoice(String voice) {
        task.put(VOICE, voice);
        return this;
    }

    public Speech setEmotion(String emotion) {
        task.put(EMOTION, emotion);
        return this;
    }

    public Speech setSpeed(String speed) {
        task.put(SPEED, speed);
        return this;
    }

    @Override
    public String getParam() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        task.forEach((k, v) -> {
            if (sb.length() > 0)
            {
                sb.append("&");
            }
            sb.append(k).append("=").append(v);
        });
        return sb.toString();
    }

    @Override
    public String getURL() {
        return URL.SYNTHESIZE;
    }
}
