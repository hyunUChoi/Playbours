package leave.meet.playbours.common.file.repository;

import leave.meet.playbours.common.file.dto.FileDto;

import java.util.List;

public interface FileRepository {

    void insert(FileDto dto);

    void delete(String fileName);

    List<FileDto> findFilesByFileName(String fileName);

    FileDto findFile(String fileName);
}
