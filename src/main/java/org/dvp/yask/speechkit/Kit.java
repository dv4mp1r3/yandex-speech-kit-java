package org.dvp.yask.speechkit;

import java.util.HashMap;
import java.util.Map;

/**
 * Комплект
 */
public abstract class Kit implements Task {

    /**
     * Наименования параметра "Язык"
     */
    protected final static String LANG = "lang";

    /**
     * Наименования параметра "Формат аудио"
     */
    protected final static String FORMAT = "format";

    /**
     * Наименования параметра "Темп"
     */
    protected final static String RATE = "sampleRateHertz";

    /**
     * Параметры задачи
     */
    protected Map<String, String> task;

    public Kit()
    {
        task = new HashMap<>();
    }

    public Kit setLang(String lang)
    {
        task.put(LANG, lang);
        return this;
    }

    public Kit setFormat(String format)
    {
        task.put(RATE, !format.equals(Format.OGGOPUS) ? Rate.HIGH : null);
        task.put(FORMAT, format);

        return this;
    }


    @Override
    public void addParam(Map<String, String> param) {
        task.putAll(param);
    }

    @Override
    public abstract String getParam();

    @Override
    public abstract String getURL();
}
