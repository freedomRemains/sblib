package com.sb.sblib.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OutputLogService {

    private static final Logger logger = LoggerFactory.getLogger("com.sb.sbwap");

    public void outputSampleLogs() {
        log.trace("[sblib(NOT com.sb.sbwap)]This is a strace log message.");
        log.info("[sblib(NOT com.sb.sbwap)]This is an info log message.");
        log.warn("[sblib(NOT com.sb.sbwap)]This is a warn log message.");
        log.error("[sblib(NOT com.sb.sbwap)]This is an error log message.");
        logger.trace("[NOT com.sb.sbwap, BUT logger specified]This is a strace log message.");
        logger.info("[NOT com.sb.sbwap, BUT logger specified]This is an info log message.");
        logger.warn("[NOT com.sb.sbwap, BUT logger specified]This is a warn log message.");
        logger.error("[NOT com.sb.sbwap, BUT logger specified]This is an error log message.");
    }
}
