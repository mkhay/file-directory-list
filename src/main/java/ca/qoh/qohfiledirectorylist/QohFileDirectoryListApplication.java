package ca.qoh.qohfiledirectorylist;

import ca.qoh.qohfiledirectorylist.command.ListDirectoryCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class QohFileDirectoryListApplication implements CommandLineRunner {

    private final ListDirectoryCommand listDirectoryCommand;

    @Autowired
    public QohFileDirectoryListApplication(ListDirectoryCommand listDirectoryCommand) {
        this.listDirectoryCommand = listDirectoryCommand;
    }

    public static void main(String[] args) {
        SpringApplication.run(QohFileDirectoryListApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (ArrayUtils.isEmpty(args)) {
            return;
        }

        String folderName = args[0];

        String output = listDirectoryCommand.execute(folderName);

        log.info(output);
    }
}
