package com.example.rest;/*
 *Created by vijay
 *Date: 22/01/21
 *Time: 1:36 PM
 */

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {
}
