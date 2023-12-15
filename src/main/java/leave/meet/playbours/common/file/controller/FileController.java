package leave.meet.playbours.common.file.controller;

import leave.meet.playbours.common.file.dto.FileDto;
import leave.meet.playbours.common.file.repository.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Controller
public class FileController {

    @Value("${file.upload.path}")
    private String PATH;

    private final FileRepository fileRepository;

    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @RequestMapping("/file/form")
    public String form(@RequestParam String atchFile, Model model) {
        model.addAttribute("resultList", fileRepository.findByFileName(atchFile));
        return "pages/common/fileUpload";
    }

    @ResponseBody
    @RequestMapping("/file/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {

        String fileNm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + UUID.randomUUID();
        String filepath = PATH + getFilePath() + fileNm;

        if(!"".equals(filepath)) {
            File saveFolder = new File(filepath);
            if(!saveFolder.exists() || saveFolder.isFile()) {
                saveFolder.mkdirs();
            }
            file.transferTo(saveFolder);
        }

        FileDto fileDto = new FileDto();
        fileDto.setFileName(fileNm);
        fileDto.setOriginalFileNm(file.getOriginalFilename());
        fileDto.setFileType(file.getContentType());
        fileDto.setFileSize(file.getSize());
        fileRepository.insert(fileDto);

        return fileNm;
    }

    public String getFilePath() {
        LocalDateTime now = LocalDateTime.now();
        return now.getYear() + File.separator + now.getMonthValue()  + File.separator + now.getDayOfMonth() + File.separator;
    }

}
