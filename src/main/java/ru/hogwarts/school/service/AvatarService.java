package ru.hogwarts.school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AvatarService {

    void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;

    void downloadAvatar(Long studentId, HttpServletResponse response) throws IOException;

    Avatar findAvatarByStudentId(Long studentId);

}
