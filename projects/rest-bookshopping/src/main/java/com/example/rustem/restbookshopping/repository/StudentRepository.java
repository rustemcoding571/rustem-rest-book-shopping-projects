package com.example.rustem.restbookshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rustem.restbookshopping.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
