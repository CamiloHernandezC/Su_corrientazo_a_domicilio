package com.sucorrientazoadomicilio.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.List;

public class TextWriter implements DataWriter {

    private static final Logger logger = LogManager.getLogger(TextWriter.class);
    /**
     * Se utiliza la supresión de excepciones para cerrar el FileWriter y el BufferWriter de manera automática
     */
    @Override
    public void writeData(String writePath, String fileName, List<String> text) {

        File directory = new File(writePath);
        if (!directory.exists()){
            if(!directory.mkdir()){
                logger.error(Parameters.DIRECTORY_NOT_CREATED);
                return;
            }
        }

        try (
                FileWriter file = new FileWriter(writePath+fileName);
                BufferedWriter bw = new BufferedWriter(file)
        ) {
            for (String line : text){
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            logger.error(e.getClass());
        }
    }
}
