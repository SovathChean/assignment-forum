package com.example.springassignmentforum.core.dto.userDTO;

import com.example.springassignmentforum.core.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private Long id;
	private String name;
	private String phone;
	private String password;
}
