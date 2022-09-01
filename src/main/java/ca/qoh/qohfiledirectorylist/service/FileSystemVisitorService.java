package ca.qoh.qohfiledirectorylist.service;

import ca.qoh.qohfiledirectorylist.model.FileSystemItem;
import ca.qoh.qohfiledirectorylist.model.ItemType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class FileSystemVisitorService {

    private static final String FILE_NOT_FOUND_MISSING_PERMISSION = "file not found or missing permission ";

    public FileSystemItem getItems(String folderName) {
        File file = Paths.get(getPath(folderName)).toFile();
        return getFileSystemItem(file);
    }

    private FileSystemItem getFileSystemItem(File file) {

        if (file.isDirectory()) {
            List<FileSystemItem> fileSystemItems = getFileSystemItems(file);
            long directorySize = fileSystemItems.stream().mapToLong(FileSystemItem::getSize).sum();

            FileSystemItem directory = createFileSystemItem(file, ItemType.directory, directorySize);
            directory.setCount(fileSystemItems.size());
            directory.setItems(fileSystemItems);

            fileSystemItems.sort((item1, item2) -> Math.toIntExact(item2.getSize() - item1.getSize()));

            return directory;
        } else {
            return createFileSystemItem(file, ItemType.file, file.length());
        }
    }

    private List<FileSystemItem> getFileSystemItems(File directory) {
        List<FileSystemItem> items = new ArrayList<>();
        for (File item : Objects.requireNonNull(directory.listFiles())) {
            items.add(getFileSystemItem(item));
        }
        return items;
    }

    private FileSystemItem createFileSystemItem(File file, ItemType type, long size) {
        LocalDateTime lastModifiedTime = null;
        try {
            FileTime fileTime = Files.getLastModifiedTime(file.toPath());
            lastModifiedTime = LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault());
        } catch (NoSuchFileException e) {
            log.error("could not read file or directory due to {}{}", FILE_NOT_FOUND_MISSING_PERMISSION, e.getMessage());
            throw new IllegalArgumentException(FILE_NOT_FOUND_MISSING_PERMISSION + file.getAbsolutePath());
        } catch (IOException e) {
            log.warn("could not read file attribute(s) due to {}", e.getMessage(), e);
        }

        return new FileSystemItem(file.getName(), file.getAbsolutePath(), size,
                lastModifiedTime, type, 0L, new ArrayList<>());
    }

    private String getPath(String folderName) {
        String baseDirectory = System.getProperty("user.dir");
        return Paths.get(baseDirectory, folderName.trim()).toFile().getAbsolutePath();
    }
}
