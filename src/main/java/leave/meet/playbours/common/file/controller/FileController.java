/*
package leave.meet.playbours.common.file.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import leave.meet.playbours.common.file.dto.FileDto;
import leave.meet.playbours.common.file.repository.FileRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

@Controller
public class FileController {

    private final FileRepository fileRepository;

    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @RequestMapping("/file/form")
    public String form(@RequestParam String atchFile, Model model) {
        String file = (atchFile != null && !"".equals(atchFile)) ? atchFile : String.valueOf(UUID.randomUUID());
        model.addAttribute("atchFile", file);
        model.addAttribute("resultList", fileRepository.findFilesByFileName(file));
        return "pages/common/fileUpload";
    }

    @ResponseBody
    @RequestMapping("/file/upload")
    public String upload(@RequestParam MultipartFile file, @RequestParam String atchFile, HttpServletRequest request) throws IOException {

        LocalDateTime now = LocalDateTime.now();
        String saveFileNm = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + atchFile;
        String folderPath = now.getYear() + File.separator + now.getMonthValue()  + File.separator + now.getDayOfMonth() + File.separator;

        // TODO 서버컴 경로로 변경
        */
/* window | mac 파일 상대경로 지정 *//*

        String path = File.separator + "Playbours" + File.separator + "attach" + File.separator + "upload" + File.separator;
        if(request.getHeader("USER-AGENT").toLowerCase().contains("mac")) {
            path = File.separator + "Users" + path;
        } else if(request.getHeader("USER-AGENT").toLowerCase().contains("window")) {
            */
/* 사용자의 컴퓨터의 드라이버가 유동적일 것을 감안하여 현재 java가 깔려있는 드라이버에 저장 *//*

            path = System.getProperty("user.dir").split(":")[0] + ":" + path;
        }

        String filepath = path + folderPath + saveFileNm;
        File saveFolder = new File(filepath);
        if(!saveFolder.exists() || saveFolder.isFile()) {
            saveFolder.mkdirs();
        }
        file.transferTo(saveFolder);

        FileDto fileDto = new FileDto();
        fileDto.setFileName(atchFile);
        fileDto.setSaveFileNm(saveFileNm);
        fileDto.setOriginalFileNm(file.getOriginalFilename());
        fileDto.setFileStorePath(path + folderPath);
        fileDto.setFileType(file.getContentType());
        fileDto.setFileSize(file.getSize());
        fileRepository.insert(fileDto);

        return atchFile;
    }

    @RequestMapping("/file/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String storePath = request.getParameter("fileStorePath") + request.getParameter("saveFileNm");
            File file = new File(storePath);
            FileInputStream in = new FileInputStream(storePath);

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + URLEncoder.encode(request.getParameter("originalFileNm"), StandardCharsets.UTF_8).replaceAll("\\+", "%20"));
            OutputStream os = response.getOutputStream();

            try {
                int length;
                byte[] bytes = new byte[(int) file.length()];
                while ((length = in.read(bytes)) > 0) {
                    os.write(bytes, 0, length);
                }
                os.flush();
            } finally {
                os.close();
                in.close();
            }
        } catch(Exception e) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script type=\"text/javascript\">alert('파일을 찾을 수 없습니다.'); history.back();</script>");
            writer.flush();
            writer.close();
        }
    }

    @ResponseBody
    @RequestMapping("/file/delete")
    public int delete(@RequestParam String saveFileNm, @RequestParam String atchFile) {

        */
/* 물리파일 삭제 및 폴더 삭제 - 보류 *//*

        */
/*FileDto fileDto = fileRepository.findFile(saveFileNm);
        File file = new File(fileDto.getFileStorePath(), fileDto.getSaveFileNm());
        File directory = new File(fileDto.getFileStorePath());

        file.delete();
        directory.delete();*//*

        fileRepository.delete(saveFileNm);
        return fileRepository.countByFileName(atchFile);
    }

    @ResponseBody
    @RequestMapping("/file/tmpFile")
    public void tmpFile(@RequestBody HashMap<String, Object> param) {
        */
/* parameter String casting 시 bootstrap 3.X.X version error 발생 *//*

        Object atchFile = param.get("atchFile");
        Object type = param.get("type");

        if(type.equals("submit")) {
            fileRepository.deleteDelY(atchFile);
            fileRepository.updateTempN(atchFile);
        } else if(type.equals("cancel")) {
            fileRepository.deleteTempY(atchFile);
            fileRepository.updateDelN(atchFile);
        }
    }

}
*/
