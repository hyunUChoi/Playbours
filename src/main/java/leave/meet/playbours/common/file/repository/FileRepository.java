package leave.meet.playbours.common.file.repository;

import leave.meet.playbours.common.file.dto.FileDto;

import java.util.List;

public interface FileRepository {

    void insert(FileDto dto);

    void delete(String saveFileNm);

    int countByFileName(String fileName);

    List<FileDto> findFilesByFileName(String fileName);

    void deleteDelY(Object fileName);

    void updateTempN(Object fileName);

    void deleteTempY(Object fileName);

    void updateDelN(Object fileName);
}
