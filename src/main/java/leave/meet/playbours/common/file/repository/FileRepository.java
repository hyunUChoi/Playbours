package leave.meet.playbours.common.file.repository;

import leave.meet.playbours.common.file.dto.FileDto;

import java.util.List;

public interface FileRepository {

    void insert(FileDto dto);

    void delete(String saveFileNm);

    int countByFileName(String fileName);

    List<FileDto> findFilesByFileName(String fileName);

    //void removeUnusedFile(String fileName);
    //void updateToNewFileTempYn(String fileName);
    //void updateToDeleteFileTempYn(String fileName);

    FileDto findFile(String saveFileNm);
}
