package com.opencart.Utils;

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

    public String Download(@NotNull String uri) throws IOException {
        String newUri = uri.replace("http", "https");
        logger.debug(String.format("Выгружаем картинку по адресу: %s", newUri));
        InputStream in = new URL(newUri).openStream();
        String nameImage = ConvertToName(uri);
        Files.copy(in, Paths.get(settings.getSourcePath() + "//" + nameImage), StandardCopyOption.REPLACE_EXISTING);
        logger.debug(String.format("Картинка успешно сохранена по пути: - %s", settings.getSourcePath() + "/" + nameImage));
        return settings.getSourcePath() + "/" + nameImage;
    }

    @NotNull
    private String ConvertToName(@NotNull String uri){
       String name = uri.replace("http://ilgc-", "").replace('/', '-').replace('.', '-');
       return name;
    }
}
