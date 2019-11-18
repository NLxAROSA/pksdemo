package io.pivotal.examples.k8s.pksdemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoController
 */
@RestController
public class DemoController {

    @GetMapping("/")
    public String helloPks() {
        return "Hello PKS! The current time is: "
                + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date()) + ", have a great day today!";
    }

    @GetMapping("/filez")
    public String getFile() throws Exception {
        File f = new File("/mnt/azure/test.txt");
        if (f.exists()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String s = br.readLine();
            return s;
        } else {
            return "Can't find file..";
        }
    }
}