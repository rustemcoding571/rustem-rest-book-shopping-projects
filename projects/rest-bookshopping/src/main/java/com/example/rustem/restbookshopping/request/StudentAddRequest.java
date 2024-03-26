package com.example.rustem.restbookshopping.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentAddRequest {

	@Size(min = 2, max = 20, message = "tələbənin adı minimum 2, maksimum 20 simvol olmalıdır.")
	private String name;
	@Size(min = 2, max = 20, message = "tələbənin soyadı minimum 2, maksimum 20 simvol olmalıdır.")
	private String surname;
	@Size(max = 200, message = "tələbənin ünvanı maksimum 200 simvol olmalıdır.")
	private String address;
	@Pattern(regexp = "\\(\\d{3}\\)-\\d{3}-\\d{4}", message = "Telefon numarası (000)-000-0000 formatında olmalıdır.")
	private String phone;
	@Size(min = 2, max = 20, message = "tələbənin istifadəçi adı minimum 2, maksimum 20 simvol olmalıdır.")
	private String username;
	@Size(min = 2, max = 20, message = "tələbənin şifrəsi minimum 2, maksimum 20 simvol olmalıdır.")
	private String password;
}
