package com.bounteous.model;

import org.joda.time.DateTime;

public class LogMessage {

    private final DateTime time = new DateTime();
    private final String message;
    private final LogSeverity severity;

    public LogMessage(LogSeverity severity, String message) {
        this.severity = severity;
        this.message = message;
    }

    public enum LogSeverity {
        DEBUG,
        INFO,
        WARN,
        ERROR
    }
}
