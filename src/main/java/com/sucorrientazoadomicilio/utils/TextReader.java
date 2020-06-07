package com.sucorrientazoadomicilio.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextReader implements DataReader {

    private static final Logger logger = LogManager.getLogger(TextReader.class);
    /**
     * Se utiliza la supresión de excepciones para cerrar el Buffer reader de manera automática
     */
    @Override
    public List<String> readData(String readPath) {
        List<String> data = new ArrayList<>();
        File file = new File(readPath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String st;
            while ((st = br.readLine()) != null) {
                data.add(st);
            }
        } catch (FileNotFoundException e) {
            logger.error(e.getClass());
        } catch (IOException e) {
            logger.error(e.getClass());
        }
        return data;
    }
}
