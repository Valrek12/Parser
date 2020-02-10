package com.opencart;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        StartLoader startLoader = new StartLoader();
        startLoader.LoadDataBaseData();
    }


}

