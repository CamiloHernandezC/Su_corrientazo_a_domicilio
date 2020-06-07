package com.sucorrientazoadomicilio;


import com.sucorrientazoadomicilio.utils.Parameters;
import com.sucorrientazoadomicilio.utils.TextReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextReaderTest {

    final TextReader textReader = new TextReader();

    @Test
    public void testLoadData(){
        String dronFileName = "01";
        List<String> data = textReader.readData(Parameters.READ_DRON_PATH
                + Parameters.READ_DRON_ORDERS_PREFIX + dronFileName + Parameters.READ_DRON_ORDERS_EXTENSION);
        assertFalse(data.isEmpty());
    }

    /*
    @Test
    public void testFileNotFound(){
        String dronFileName = "00";
        FileNotFoundException exception = assertThrows(FileNotFoundException.class, () -> textReader.readData(Parameters.READ_DRON_PATH
                + Parameters.READ_DRON_ORDERS_PREFIX + dronFileName + Parameters.READ_DRON_ORDERS_EXTENSION),
                "File not found exception not thrown");
    }*/
}