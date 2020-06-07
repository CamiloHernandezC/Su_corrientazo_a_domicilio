package com.sucorrientazoadomicilio.utils;

import java.util.List;

public interface DataWriter {
    void writeData(String writePath, String fileName, List<String> text);
}
