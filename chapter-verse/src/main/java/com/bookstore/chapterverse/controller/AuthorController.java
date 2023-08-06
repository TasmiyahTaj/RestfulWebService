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

	@RequestMapping(method=RequestMethod.GET, path="/getAllAuthor")
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
	
	@RequestMapping(method=RequestMethod.POST, path="/AuthorLogin",consumes="application/json")
	public Author LoginUser(@RequestBody Author author) {
		Author author1=null;
		try {
			AuthorDAO db = new AuthorDAO();
			/*
			 * String authorEmail=author.getAuthorEmail(); String
			 * authorpwd=author.getAuthorPwd();
			 */	
			author1=db.loginAuthor(author.getAuthorEmail(), author.getAuthorPwd());
			System.out.println("done loging user");
		}catch(Exception e) {}
		return author1;

	}
	
	
	
	@RequestMapping(method=RequestMethod.PUT, path="/modifyAuthor/{uid}",consumes="application/json")
	public int modify(@RequestBody Author author, @PathVariable("uid")int uid) {
		int rec=0;
		try {
			AuthorDAO db = new AuthorDAO();
			int id= author.getAuthorID();
			System.out.println("...Inside auth controller id "+id);
			int authorID=author.getAuthorID();
			String authorName=author.getAuthorName();
			String authorEmail=author.getAuthorEmail();
			String authorDesc=author.getDescription();
			String authorPhone=author.getAuthorPhone();
			String authorpwd=author.getAuthorPwd();
			String authorProfile=author.getAuthorProfile();
			rec=db.modifyAuthor(authorID,authorName, authorEmail,authorPhone, authorDesc);
			System.out.println("done modifying author");
		}catch(Exception e) {}
		return rec;

	}
	
	@RequestMapping(method=RequestMethod.GET,path="/getAuthor/{uid}")
	
	public Author getAuthor(@PathVariable("uid")String uid) {
		Author author = new Author();
		try {
			AuthorDAO db = new AuthorDAO();
			author = db.getAuthorDetails(uid);
		}
		catch(Exception e) {
			System.out.println("Error : "+e);
		}
		return author;
	}
	@RequestMapping(method=RequestMethod.DELETE,path="/deleteAuthor/{uid}")
	
	public int deleteAuthor(@PathVariable("uid")int uid) {
		Author author = new Author();
		int delete=0;
		try {
			AuthorDAO db = new AuthorDAO();
			 delete = db.deleteUser(uid);
		}
		catch(Exception e) {
			System.out.println("Error : "+e);
		}
		return delete;
	}

}