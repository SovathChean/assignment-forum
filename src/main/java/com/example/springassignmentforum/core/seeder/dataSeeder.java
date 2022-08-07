package com.example.springassignmentforum.core.seeder;

import com.example.springassignmentforum.core.dao.PostDAO;
import com.example.springassignmentforum.core.dao.UserDAO;
import com.example.springassignmentforum.core.dto.PostDTO.PostCreationDTO;
import com.example.springassignmentforum.core.dto.userDTO.UserCreationDTO;
import com.example.springassignmentforum.core.mapper.PostMapper;
import com.example.springassignmentforum.core.mapper.UserMapper;
import com.example.springassignmentforum.core.model.PostModel;
import com.example.springassignmentforum.core.model.UserModel;
import com.example.springassignmentforum.core.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
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
    @Bean
    CommandLineRunner postSeederRunning(){
        List<PostCreationDTO> postCreationDTOS = new ArrayList<>();
        postCreationDTOS.add(new PostCreationDTO("Testing", "ForTesting", (long) 1, null));

        for (int i = 0; i < 10; i++)
        {
            postCreationDTOS.add(new PostCreationDTO(
                    "PostTesting"+i,
                    "ForTesting"+i,
                    (long) 1,
                    null
            ));
        }
        List<PostModel> postModels = PostMapper.INSTANCE.fromPostCreationToPostModel(postCreationDTOS);
        return args -> {
            postDAO.saveAll(postModels);
        };
    }
    @Bean
    CommandLineRunner fileSeederRunning(FileService fileService) throws IOException {
        FileUtils.deleteDirectory(Paths.get("uploads").toFile());
        List<MultipartFile> files = new ArrayList<>();
        File file = new File("files/image.png");
        MultipartFile multipartFile = new MockMultipartFile(
                "file.png",
                 new FileInputStream(file)
        );
        files.add(multipartFile);

        return args -> {
            fileService.uploadListFile(files);
        };
    }
}
