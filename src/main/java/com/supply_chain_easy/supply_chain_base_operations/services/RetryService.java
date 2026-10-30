package com.supply_chain_easy.supply_chain_base_operations.services;

import com.supply_chain_easy.supply_chain_base_operations.exceptions.RetryAttemptsFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetryService {

    public static void retry(Runnable operation) {

        log.info("Retry operation started");

        for (int i = 1; i <= 3; i++) {
            try {
                log.info("Executing operation. Attempt={}", i);

                operation.run();

                log.info("Operation completed successfully on attempt={}", i);
                return;

            } catch (Exception e) {
                log.error("Operation failed on attempt={}. Error={}", i, e.getMessage(), e);
            }
        }

        log.error("Operation failed after all retry attempts");

        throw new RetryAttemptsFailedException("All attempts exhausted");
    }
}

