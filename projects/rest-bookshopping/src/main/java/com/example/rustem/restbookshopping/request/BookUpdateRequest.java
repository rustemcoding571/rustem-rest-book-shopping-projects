package com.example.rustem.restbookshopping.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookUpdateRequest {

	private Integer bookId;
	@Size(min = 2, max = 40, message = "kitabın adı minimum 2, maksimum 40 simvol olmalıdır.")
	private String name;
	@Min(value = 0, message = "kitabın qiyməti minimum 0 olmalıdır.")
	@Max(value = 10000, message = "kitabın qiyməti maksimum 10_000 olmalıdır.")
	private Double price;
}
