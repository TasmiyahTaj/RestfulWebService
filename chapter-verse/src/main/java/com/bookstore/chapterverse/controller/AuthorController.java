package com.bookstore.chapterverse.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.chapterverse.dbaccess.*;

@RestController
public class AuthorController {
	 

	@RequestMapping(method = RequestMethod.POST, path = "/addAuthor", consumes = "application/json")
	public int createUser(@RequestBody Author author) {
		int rec = 0;
		try {
			AuthorDAO db = new AuthorDAO();
			int id = author.getAuthorID();
			System.out.println("...Inside auth controller id " + id);
			String authorName = author.getAuthorName();
			String authorEmail = author.getAuthorEmail();
			String authorDesc = author.getDescription();
			String authorPhone = author.getAuthorPhone();
			String authorpwd = author.getAuthorPwd();
			String authorProfile = author.getAuthorProfile();
			rec = db.insertAuthor(authorName, authorEmail, authorpwd, authorPhone, authorProfile, authorDesc);
			System.out.println("done creating user");
		} catch (Exception e) {
		}
		return rec;

	}

	@RequestMapping(method = RequestMethod.GET, path = "/getAllAuthor")
	public ArrayList<Author> getAllAuthor() {
		ArrayList<Author> myList = new ArrayList<>();
		try {
			AuthorDAO db = new AuthorDAO();
			myList = db.listAllAuthor();
		} catch (SQLException e) {
			e.printStackTrace();

			System.out.println("ERROR: " + e.getMessage());
		}
		return myList;
	}


}
