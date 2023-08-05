package com.bookstore.chapterverse.dbaccess;

import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;



public class AuthorDAO {
	public ArrayList<Author> listAllAuthor() throws SQLException {
	    ArrayList<Author> authorList = new ArrayList<>();
	    Connection conn = null;
	    try {
	        conn = DBConnection.getConnection();
	        String sqlStr = "SELECT * FROM author";
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(sqlStr);
	        while (rs.next()) {
	            Author uBean = new Author();
	            uBean.setAuthorID(rs.getInt("authorID"));
	            uBean.setAuthorName(rs.getString("authorName"));
	            uBean.setAuthorEmail(rs.getString("age")); // Note: Fix column name "age" to the correct one.
	            uBean.setAuthorPhone(rs.getString("authorPhone"));
	            authorList.add(uBean);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e; // Rethrow the exception to the calling method or handle it properly.
	    } finally {
	        if (conn != null) {
	            conn.close();
	        }
	    }
	    return authorList;
	}

	
	
	
	public int insertAuthor(String authorName, String authorEmail, String authorPwd, String authorPhone,
			String authorProfile, String description) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		int nrow = 0;
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "INSERT INTO Author (authorName, authorEmail, authorPassword, authorPhone, profilePic, authorDescription) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, authorName);
			pstmt.setString(2, authorEmail);
			pstmt.setString(3, authorPwd);
			pstmt.setString(4, authorPhone);
			URL url = new URL(authorProfile);
			InputStream inputStream = url.openStream();
			byte[] profilePicBytes = inputStream.readAllBytes();
			Blob profilePicBlob = conn.createBlob();
			profilePicBlob.setBytes(1, profilePicBytes);

			pstmt.setBlob(5, profilePicBlob);

			pstmt.setString(6, description);
			nrow = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
// Make sure to close the connection in the finally block
			if (conn != null) {
				conn.close();
			}
		}
		return nrow;
	}

	public int deleteUser(int authorID) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		int nrow = 0;
		try {
			conn = DBConnection.getConnection();

			// Step 1: Delete the author's books
			String deleteBooksSQL = "DELETE FROM books WHERE authorID = ?";
			PreparedStatement deleteBooksStmt = conn.prepareStatement(deleteBooksSQL);
			deleteBooksStmt.setInt(1, authorID);
			int deletedBooks = deleteBooksStmt.executeUpdate();
			System.out.println("Deleted " + deletedBooks + " books of authorID: " + authorID);

			// Step 2: Delete the author
			String deleteAuthorSQL = "DELETE FROM author WHERE authorID = ?";
			PreparedStatement deleteAuthorStmt = conn.prepareStatement(deleteAuthorSQL);
			deleteAuthorStmt.setInt(1, authorID);
			nrow = deleteAuthorStmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return nrow;
	}

}