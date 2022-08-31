package ca.qoh.qohfiledirectorylist;

import ca.qoh.qohfiledirectorylist.service.FileSystemVisitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class QohFileDirectoryListApplication implements CommandLineRunner {

    private final FileSystemVisitorService fileSystemVisitorService;

    @Autowired
    public QohFileDirectoryListApplication(FileSystemVisitorService fileSystemVisitorService) {
        this.fileSystemVisitorService = fileSystemVisitorService;
    }

    public static void main(String[] args) {
        SpringApplication.run(QohFileDirectoryListApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String folderName = args[0];

        log.info("folder name: {}", folderName);

        String output;
        try {
            output = fileSystemVisitorService.getItemsAsJson(folderName);
        } catch (Exception exception) {
            return;
        }

        log.info(output);
    }
}
