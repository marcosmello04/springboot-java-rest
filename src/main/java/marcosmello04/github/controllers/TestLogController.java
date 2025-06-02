package marcosmello04.github.controllers;

import marcosmello04.github.services.PersonServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TestLogController {

    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    @GetMapping("/test")
    public String testLog() {
        logger.debug("Debug Log");
        logger.info("Info Log");
        logger.warn("Warn Log");
        logger.error("Error Log");
        return "Log generated.";
    }

}
