package com.example.rustem.restbookshopping.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentUpdateRequest {

	private Integer id;
	@Size(min = 2, max = 20, message = "tələbənin adı minimum 2, maksimum 20 simvol olmalıdır.")
	private String name;
	@Size(min = 2, max = 20, message = "tələbənin soyadı minimum 2, maksimum 20 simvol olmalıdır.")
	private String surname;
	@Size(min = 2, max = 20, message = "tələbənin istifadəçi adı minimum 2, maksimum 20 simvol olmalıdır.")
	private String username;
	@Size(min = 2, max = 20, message = "tələbənin şifrəsi minimum 2, maksimum 20 simvol olmalıdır.")
	private String password;
}
