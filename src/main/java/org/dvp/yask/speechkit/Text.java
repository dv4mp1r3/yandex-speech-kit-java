package org.dvp.yask.speechkit;

public class Text extends Kit {

    /**
     * Наименования параметра "Языковая модель"
     */
    private final static String TOPIC = "topic";

    /**
     * Наименования параметра "Фильтр ненормативной лексики"
     */
    private final static String FILTER = "profanityFilter";

    private String filePath;

    public Text(String filePath)
    {
        this.filePath = filePath;
    }

    public Text setTopic(String topic)
    {
        task.put(TOPIC, topic);
        return this;
    }

    public Text setFilter(String filter)
    {
        task.put(FILTER, filter);
        return this;
    }

    @Override
    public String getParam() {
        return filePath;
    }

    @Override
    public String getURL() {
        StringBuilder sb = new StringBuilder();
        sb.append(URL.RECOGNIZE).append("?");
        task.forEach((k, v) -> {
            if (sb.length() > 0)
            {
                sb.append("&");
            }
            sb.append(k).append("=").append(v);
        });
        return sb.toString();
    }
}
