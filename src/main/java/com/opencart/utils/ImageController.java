package com.opencart.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageController {
    private Settings settings;
    private static final Logger logger = LogManager.getLogger();

    @Contract(pure = true)
    public ImageController() {
        this.settings = new Settings();
    }

    /**
     * Метод загрузки картинок
     * @param uri адресс с которого нужно грузить картинку
     * @return
     * @throws IOException
     */
    public String Download(@NotNull String uri) throws IOException {
        try{
            if(!uri.contains("https")){
                String newUri = uri.replace("http", "https");
                logger.debug(String.format("Выгружаем картинку по адресу: %s", newUri));
                InputStream in = new URL(newUri).openStream();
                String nameImage = ConvertToName(uri);
                Files.copy(in, Paths.get(settings.getCanonicalPath() + "/" + nameImage + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
                logger.debug(String.format("Картинка успешно сохранена по пути: - %s", settings.getSourcePath() + "/" + nameImage + ".jpg"));
                return settings.getSourcePath() + "/" + nameImage + ".jpg";
            }else{
                logger.debug(String.format("Выгружаем картинку по адресу: %s", uri));
                InputStream in = new URL(uri).openStream();
                String nameImage = ConvertToName(uri);
                Files.copy(in, Paths.get(settings.getCanonicalPath() + "/" + nameImage + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
                logger.debug(String.format("Картинка успешно сохранена по пути: - %s", settings.getSourcePath() + "/" + nameImage + ".jpg"));
                return settings.getSourcePath() + "/" + nameImage + ".jpg";
            }

        }catch (Exception ex){
            logger.error(String.format("Возникла необработанная ошибка, подробности - %s", ex.fillInStackTrace()));
            return null;
        }

    }

    /**
     * Вспомогательный метод, преобразует строку в нужный формат, убирает в пути - '/'
     * @param uri - uri, который необходимо преобразовать.
     * @return name - преобразовананя стркоа
     */
    @NotNull
    private String ConvertToName(@NotNull String uri){
        if(uri.contains("ilgc-")){
            String name = uri.replace("http://ilgc-", "").replace('/', '-').replace('.', '-');
            return name;
        }else {
            String name = uri.replace('/', '-').replace('.', '-');
            return name;
        }

    }
}
