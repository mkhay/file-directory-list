package ca.qoh.qohfiledirectorylist.controller;

import ca.qoh.qohfiledirectorylist.model.ApiError;
import ca.qoh.qohfiledirectorylist.model.FileSystemItem;
import ca.qoh.qohfiledirectorylist.service.FileSystemVisitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/folders")
public class ListDirectoryController {

    private static final String UNABLE_TO_PROCESS_REQUEST_DUE_TO = "unable to process request due to ";

    private final FileSystemVisitorService fileSystemVisitorService;

    @Autowired
    public ListDirectoryController(FileSystemVisitorService fileSystemVisitorService) {
        this.fileSystemVisitorService = fileSystemVisitorService;
    }


    @GetMapping("/{folderName}")
    public FileSystemItem listItems(@PathVariable String folderName) {
        return fileSystemVisitorService.getItems(folderName);
    }

    @Operation(summary = "return files and directories structure given a directory name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully traversed all files and folders under the given directory name",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FileSystemItem.class))}),
            @ApiResponse(responseCode = "422", description = "Invalid folder name or no sufficient permissions"),
            @ApiResponse(responseCode = "500", description = "Service not available")
    })
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> handleUnprossableEntityException(Exception exception) {
        log.error(UNABLE_TO_PROCESS_REQUEST_DUE_TO + exception.getMessage());
        return createApiErrorResponse(exception, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleUnknownException(Exception exception) {
        log.error(UNABLE_TO_PROCESS_REQUEST_DUE_TO + exception.getMessage());
        return createApiErrorResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Add more exception handlers

    private ResponseEntity<Object> createApiErrorResponse(Exception exception, HttpStatus status) {
        final ApiError apiError = new ApiError(status, exception.getMessage(), exception);
        if (exception.getCause() != null) {
            apiError.setDetails(exception.getCause().toString());
        }
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
