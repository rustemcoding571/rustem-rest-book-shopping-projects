package com.example.rustem.restbookshopping;

import com.example.rustem.restbookshopping.entity.Authority;
import com.example.rustem.restbookshopping.entity.BorrowedBook;
import com.example.rustem.restbookshopping.entity.User;
import com.example.rustem.restbookshopping.repository.BookRepository;
import com.example.rustem.restbookshopping.service.AuthorityService;
import com.example.rustem.restbookshopping.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class RestBookshoppingApplication implements CommandLineRunner {
	private final UserService userService;
	private final AuthorityService authorityService;
	private final BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestBookshoppingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// insert user
		User user = new User();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		user.setUsername("Rustem");

		user.setPassword(passwordEncoder.encode("password"));
		user.setEnabled(1);
		userService.addStudent(user);
		Authority authority = new Authority();
		authority.setAuthority("ROLE_ADMIN");
		authority.setUsername(user.getUsername());
		authorityService.save(authority);

		//

//		BorrowedBook borrowedBook = new BorrowedBook();
//
//		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//		borrowedBook.setCreatorUsername(username);
//
//		//if there is book with this id
//		Integer id = 1 ;
//		bookRepository.findById(id).orElseThrow(()-> new RuntimeException("There is no book with this id:" + id));
//
//		borrowedBook.setBook_id(1);
//		//if there is student with this id
//
//		// eynisi
//		borrowedBook.setStudent_id(1);




		/*
		kitabxanaçı öz qeydiyyat etdiyi tələbələrə öz qeydiyyat etdiyi kitabları verə bilməlidir +

		 və verdiyi kitabların siyahısını qaytaran veb servis yazmaq lazımdır,
		  bu veb servis də verilən kitabın məlumatları həmdə kimə verilməsi ilə bağlı məlumatlar olmalıdır.

	 Həmçinin tələbələrdən kitabların geri qaytarılması apisinidə qurmaq lazımdır.

	Bu api kimlərə kitab verilib və qaytarıblar o məlumatları qaytaracaq və bu apilər kitabların verilmə və qaytarılma

	tarixlərinidə göstərməlidir...

	Burada lazım olan model və entity sinifləri hər kəs özü düşünüb qurmalıdır.
		* */

	}

	/*
	--
-- insert into users
-- (username,password,enabled)
-- values('librarian1','$2a$12$XYdHW6MDTpTaVJjyogjY/umSHm3IppO9QleeNZb2j/VcwwRozG4Ve',1);
--
-- insert into users
-- (username,password,enabled)
-- values('student1','$2a$12$So0oM3O5hu.TRbwU.dmjNOes1JjI3nJwUo31oaQcN0JqWkEVbjf/.',1);
--
-- insert into authorities
-- (username,authority)
-- values
-- ('student1','ROLE_ADMIN');
--
-- insert into authorities
-- (username,authority)
-- values
-- ('librarian1','ROLE_ADMIN');
	* */

}
