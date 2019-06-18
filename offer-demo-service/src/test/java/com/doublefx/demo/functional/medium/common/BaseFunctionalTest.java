package com.doublefx.demo.functional.medium.common;

import com.doublefx.demo.OfferServiceApplication;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.springframework.util.ResourceUtils.getFile;

@SpringBootTest(
        classes = OfferServiceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("medium-size-test")
public abstract class BaseFunctionalTest {

    protected static File getResource(String path) throws FileNotFoundException {
        return getFile("classpath:" + path);
    }

    protected static String readFile(String path) {
        try {
            return FileUtils.readFileToString(getResource(path), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException("File cannot be read");
        }
    }

}
