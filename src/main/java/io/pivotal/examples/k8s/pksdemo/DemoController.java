package io.pivotal.examples.k8s.pksdemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoController
 */
@RestController
public class DemoController {

    @GetMapping
    public String helloPks() {
        return "Hello PKS! The current time is: "
                + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date());
    }

}