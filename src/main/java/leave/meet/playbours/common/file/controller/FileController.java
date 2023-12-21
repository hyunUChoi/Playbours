package leave.meet.playbours.common.file.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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
        model.addAttribute("resultList", fileRepository.findFilesByFileName(atchFile));
        return "pages/common/fileUpload";
    }

    @ResponseBody
    @RequestMapping("/file/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {

        LocalDateTime now = LocalDateTime.now();
        String fileNm = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + UUID.randomUUID();
        String folderPath = now.getYear() + File.separator + now.getMonthValue()  + File.separator + now.getDayOfMonth() + File.separator;
        String filepath = PATH + folderPath + fileNm;

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
        fileDto.setFileStorePath(PATH + folderPath);
        fileDto.setFileType(file.getContentType());
        fileDto.setFileSize(file.getSize());
        fileRepository.insert(fileDto);

        return fileNm;
    }

    @RequestMapping("/file/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FileDto fileDto = fileRepository.findFile(request.getParameter("fileName"));
        String storePath = fileDto.getFileStorePath() + fileDto.getFileName();

        File file = new File(storePath);
        FileInputStream in = new FileInputStream(storePath);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + URLEncoder.encode(fileDto.getOriginalFileNm(), StandardCharsets.UTF_8).replaceAll("\\+", "%20"));
        OutputStream os = response.getOutputStream();

        int length;
        byte[] bytes = new byte[(int) file.length()];
        while ((length = in.read(bytes)) > 0) {
            os.write(bytes, 0, length);
        }

        os.flush();
        os.close();
        in.close();
    }

    @ResponseBody
    @RequestMapping("/file/delete")
    public void delete(@RequestParam String fileName) throws IOException {
        if(!"".equals(fileName)) {
            FileDto fileDto = fileRepository.findFile(fileName);
            File file = new File(fileDto.getFileStorePath(), fileDto.getFileName());
            File directory = new File(fileDto.getFileStorePath());

            file.delete();
            directory.delete();
            fileRepository.delete(fileName);
        }
    }

}
