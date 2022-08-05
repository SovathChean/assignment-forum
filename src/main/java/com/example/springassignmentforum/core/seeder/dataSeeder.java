package com.example.springassignmentforum.core.seeder;

import com.example.springassignmentforum.core.dao.PostDAO;
import com.example.springassignmentforum.core.dao.UserDAO;
import com.example.springassignmentforum.core.dto.UserCreationDTO;
import com.example.springassignmentforum.core.mapper.UserMapper;
import com.example.springassignmentforum.core.model.UserModel;
import com.example.springassignmentforum.core.service.FileService;
import com.example.springassignmentforum.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class dataSeeder {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PostDAO postDAO;

    @Bean
    CommandLineRunner userSeederRunning(){
        List<UserCreationDTO> userCreationDTOList = new ArrayList<>();
        String password = new BCryptPasswordEncoder().encode("password");
        userCreationDTOList.add(new UserCreationDTO("sovath", "092707070", password));

        for (int i = 0; i < 10; i++)
        {
            userCreationDTOList.add(new UserCreationDTO(
                    "Name"+i,
                    "092877899"+i,
                    password
            ));
        }
        List<UserModel> userModelList = UserMapper.INSTANCE.fromUserCreationToUserModel(userCreationDTOList);
        return args -> {
            userDAO.saveAll(userModelList);
        };
    }
//    @Bean
//    CommandLineRunner fileSeederRunning(FileService fileService) throws IOException {
//        List<MultipartFile> files = new ArrayList<>();
//        for(int i = 1; i<4; i++)
//        {
//            MultipartFile multipartFile = new MockMultipartFile(
//                    "file.png",
//                    new FileInputStream(Paths.get("uploads/files/file"+i+".png").toFile())
//            );
//            files.add(multipartFile);
//        }
//        return args -> {
//            fileService.uploadListFile(files);
//        };
//    }
}
