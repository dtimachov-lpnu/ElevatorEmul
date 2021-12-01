package com.timachov.daniil.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    public enum LoggingLevel {
        Trace, Info, Warning, Debug, Error, Critical
    };

    private LoggingLevel level;
    private File sink;

    public Logger() {
        this.level = LoggingLevel.Info;
    }

    public void setLevel(LoggingLevel level_) {
        this.level = level_;
    }

    public void setSink(String filename) {
        this.sink = new File(filename);
    }

    public void logMessage(String message, LoggingLevel level) {
        if (sink == null)
            return;

        if (level.ordinal() < this.level.ordinal()) 
            return;
        
        if (!sink.exists()) {
            try {
                sink.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        try (FileWriter fw = new FileWriter(sink.getAbsoluteFile(), true)) {
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("[" + level.toString() + "] " + message + "\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logMessage(String message) {
        logMessage(message, LoggingLevel.Info);
    }
};
