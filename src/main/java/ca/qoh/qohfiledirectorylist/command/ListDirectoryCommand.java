package ca.qoh.qohfiledirectorylist.command;

import ca.qoh.qohfiledirectorylist.model.FileSystemItem;
import ca.qoh.qohfiledirectorylist.service.FileSystemVisitorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class ListDirectoryCommand {

    private static final String FILE_NOT_FOUND_MISSING_PERMISSION = "file not found or missing permission ";

    private static final ObjectMapper mapper = new ObjectMapper();

    private final FileSystemVisitorService fileSystemVisitorService;

    @PostConstruct
    public void init() {
        mapper.findAndRegisterModules();
    }

    @Autowired
    public ListDirectoryCommand(FileSystemVisitorService fileSystemVisitorService) {
        this.fileSystemVisitorService = fileSystemVisitorService;
    }

    public String execute(String folderName) {
        log.info("folder name: {}", folderName);

        FileSystemItem items = null;
        try {
            items = fileSystemVisitorService.getItems(folderName);
        } catch (Exception exception) {
            log.error("could not traverse file system due to {}", exception.getMessage(), exception);
        }

        return convertToJson(items);
    }

    private String convertToJson(FileSystemItem fileSystemItem) {
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String output = StringUtils.EMPTY;
        try {
            output = ow.writeValueAsString(fileSystemItem);
        } catch (JsonProcessingException e) {
            log.error("could not convert to JSON {}{}", FILE_NOT_FOUND_MISSING_PERMISSION, e.getMessage(), e);
        }
        return output;
    }
}