package ca.qoh.qohfiledirectorylist.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FileSystemItem {

    public FileSystemItem(String name, String path, long size, LocalDateTime lastModifiedTime, ItemType type, long count, List<FileSystemItem> items) {
        this.name = name;
        this.path = path;
        this.size = size;
        this.lastModifiedTime = lastModifiedTime;
        this.type = type;
        this.count = count;
        this.items = items;
    }

    private String name;

    private String path;

    private long size;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedTime;

    private ItemType type;

    private long count;

    private List<FileSystemItem> items;
}
