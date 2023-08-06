package com.bookstore.chapterverse.dbaccess;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


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
	            uBean.setDescription(rs.getString(rs.getString("authorDescription")));
	            uBean.setAuthorProfile(rs.getString("profilePic"));
	            uBean.setAuthorPwd(rs.getString("authorPassword"));
	          // Note: Fix column name "age" to the correct one.
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
			if (authorProfile != null) {
				byte[] profileBytes = authorProfile.getBytes();
				Blob profileBlob = conn.createBlob();
				profileBlob.setBytes(1, profileBytes);
				pstmt.setBlob(5, profileBlob);
			} else {
				pstmt.setNull(5, java.sql.Types.BLOB);
			}

		

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
	
	public Author loginAuthor(String authorEmail, String authorPwd) throws SQLException, ClassNotFoundException {
		Author author = null;
		Connection conn = null;
		
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "SELECT * FROM author where authorEmail = ? and authorPassword = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, authorEmail);
			pstmt.setString(2, authorPwd);
			ResultSet rs =pstmt.executeQuery();
			if (rs.next()) {
				System.out.print("login success");
				author = new Author();
				author.setAuthorName(rs.getString("authorName"));
				author.setAuthorPwd(rs.getString("authorPassword"));
				author.setDescription(rs.getString("authorDescription"));
				author.setAuthorID(rs.getInt("authorID"));	
				author.setAuthorEmail(rs.getString("authorEmail"));	
				author.setAuthorPhone(rs.getString("authorPhone"));	
				conn.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
// Make sure to close the connection in the finally block
			if (conn != null) {
				conn.close();
			}
		}
		return author;
	}
	
	
	public int modifyAuthor(int author_id,String authorName, String authorEmail, String authorPwd, String authorPhone,
			String authorProfile, String description) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		int nrow = 0;
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "UPDATE Author SET authorName = ?, authorEmail = ?, authorPassword = ?, authorPhone = ?, profilePic = ?, authorDescription = ? WHERE authorID = ?";
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
			pstmt.setInt(7, author_id);
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
	
	
public Author getAuthorDetails(String userid) throws SQLException{
		
		Author uBean = null;
		Connection conn = null;
		
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "SELECT * from author WHERE authorID =?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, userid);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				uBean = new Author();
				uBean.setAuthorID(rs.getInt("authorID"));
				uBean.setAuthorName(rs.getString("authorName"));
				uBean.setAuthorEmail(rs.getString("authorEmail"));
				System.out.print(".....done wring to bean!!.....");
			}
		}catch(Exception e) {
			System.out.print(".............UserDetailsDB: "+e);
		}
		finally {
			conn.close();
		}
		return uBean;
	}


}