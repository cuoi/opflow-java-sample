package com.devebot.opflow.sample;

import com.devebot.opflow.OpflowBuilder;
import com.devebot.opflow.OpflowServerlet;
import com.devebot.opflow.exception.OpflowConnectionException;
import com.devebot.opflow.sample.services.AlertSenderImpl;
import com.devebot.opflow.sample.services.FibonacciCalculatorImpl;

/**
 *
 * @author drupalex
 */
public class FibonacciWorker {
    public static void main(String[] argv) throws Exception {
        try {
            System.out.println("[+] FibonacciWorker start:");
            final OpflowServerlet worker = OpflowBuilder.createServerlet("worker.properties");
            worker.instantiateType(AlertSenderImpl.class);
            worker.instantiateType(FibonacciCalculatorImpl.class);
            worker.start();
            System.out.println("[*] Waiting for message. To exit press CTRL+C");
        }
        catch (OpflowConnectionException e) {
            System.err.println("[*] Invalid connection parameters or the RabbitMQ Server not available");
        }
    }
}
