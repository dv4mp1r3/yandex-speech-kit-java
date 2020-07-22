package org.dvp.yask.speechkit;

public class URL
{
    /**
     * Получение IAM-токена
     */
    public final static String IAM_TOKEN = "https://iam.api.cloud.yandex.net/iam/v1/tokens";

    /**
     * Синтез речи
     */
    public final static String SYNTHESIZE = "https://tts.api.cloud.yandex.net/speech/v1/tts:synthesize";

    /**
     * Распознавание речи
     */
    public final static String RECOGNIZE = "https://stt.api.cloud.yandex.net/speech/v1/stt:recognize";
}
