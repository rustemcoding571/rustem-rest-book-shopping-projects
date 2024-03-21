package com.example.rustem.restbookshopping.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookAddRequest {

	@Size(min=2,max=40,message = "kitabın adı minimum 2, maksimum 40 simvol olmalıdır.")
	private String name;
	@Min(value = 0,message = "kitabın qiyməti minimum 0 olmalıdır.")
	@Max(value = 10000,message = "kitabın qiyməti maksimum 10_000 olmalıdır.")
	private Double price;
	@Past(message = "kitabın nəşr olunma tarixi indiki tarixdən əvvəlki (keçmiş) tarix olmalıdır.")
	private LocalDate dateOf;
}
