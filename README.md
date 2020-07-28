# Yandex-SpeechKit-Java-SDK

Библиотека для интеграции с сервисом речевых технологий

Порт PHP-библиотеки для работы с ["Yandex SpeechKit"](https://cloud.yandex.ru/services/speechkit) вот с [этого репозитория](https://github.com/itpanda-llc/yandex-speechkit-php-sdk)

## Возможности

* Формирование параметров синтезируемого аудио (добавление текста, выбор языка, голоса, эмоциональной окраски, темпа, формата, частоты дискретизации для произносимой речи)
* Формирование параметров распознавания короткого аудио (добавление, выбор формата, частоты дискретизации, языковой модели и фильтра ненормативной лексики аудио, указание языка произношения)
* Аутентификация в HTTP API-сервисе ["Яндекс.Облако"](https://cloud.yandex.ru) с помощью пользовательского аккаунта
* Синтезирование и распознавание речи с помощью сервиса ["Yandex SpeechKit"](https://cloud.yandex.ru/services/speechkit)

## Требования

* JDK11
* Maven
* Gson

### Создание сервиса и аутентификация

```java
try {
  Cloud cloud = new Cloud("token", "id");
} catch (ClientException e) {
  e.printStackTrace();
}
```

### Синтез речи

Создание задачи

```java
try {
  // Обязательный параметр: "Текст"
  Speech speech = new Speech("Привет, разработчик!");
} catch (ClientException e) {
  e.printStackTrace();
}
```

### Распознавание речи

Создание задачи

```java
// Обязательный параметр: "путь к файлу"
text = new Text("greeting_developer.ogg");
```

Добавление параметров речи (необязательно)

```java
/*
* Добавление обязательного параметра: "Язык"
* Возможно использование других констант класса "Lang" в качестве параметра
*/
text.setLang(Lang.RU)

  /*
   * Добавление обязательного параметра: "Языковая модель"
   * Возможно использование других констант класса "Topic" в качестве параметра
   */
  .setTopic(Topic.GENERAL)

  /*
   * Добавление обязательного параметра: "Фильтр ненормативной лексики"
   * Возможно использование других констант класса "Filter" в качестве параметра
   */
  .setFilter(Filter.FALSE)

  /*
   * Добавление обязательного параметра: "Формат аудио"
   * Возможно использование других констант класса "Format" в качестве параметра
   */
  .setFormat(Format.LPCM)

  /*
   * Добавление обязательного параметра: "Частота дискретизации"
   * Возможно использование других констант класса "Rate" в качестве параметра
   */
  .setRate(Rate.HIGH);
```

Выполнение задачи

```java
try {
  // Обязательный параметр: "Задача"
  cloud.request(text);
} catch (ClientException e) {
  e.printStackTrace();
}
```

Простой пример целиком (распознавание и запись в файл)

```java
try {
    Cloud cloud = new Cloud("token", "id");
    Speech speech = new Speech("Бла бла бла");
    speech.setVoice(Voice.random());
    byte[] oggFile = cloud.request(speech);
    ByteArrayToFile.save("test.ogg", oggFile);
} catch (Exception e) {
    e.printStackTrace();
}
```