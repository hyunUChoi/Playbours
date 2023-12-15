package leave.meet.playbours.common.file.controller;

import leave.meet.playbours.common.file.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping("/file/form")
    public String form() {
        return "pages/common/fileUpload";
    }

    @ResponseBody
    @RequestMapping("/file/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        return fileService.saveFile(file);
    }

}
