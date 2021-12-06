package com.jclapel.banksystem.back_end;

import javafx.scene.image.ImageView;

public class TestProfile {
	/*

	Test profile class in purpose of "testing".

	Main Contributor(s):
	Contributor(s):

	*/

	// public Image theDefaultImage = new Image(String.valueOf(url));
	//  
	// URL url = Thread.currentThread().getContextClassLoader().getResource("src/main/java/com/jclapel/banksystem/image/unnamed.jpg");

	public ImageView imageView;
	public String firstName;
	public String lastName;
	public String userName;
	public String password;

	public TestProfile(String firstName, String lastName, String userName, String password) {
		// Creates a test profile, containing first name, last name, username and password

		// this.theImageView.setImage(theDefaultImage);
		// trying save default image
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		
		// System.out.println(url);
	}

	//getters
	public ImageView getImageView() {
		// Returns the image view from the profile
		return imageView;
	}
	public String getFirstName() {
		// Returns the profile's first name
		return firstName;
	}
	public String getLastName() {
		// Returns the profile's last name
		return lastName;
	}
	public String getUserName() {
		// Returns the profile's username
		return userName;
	}
	public String getPassword() {
		// Returns the profile's password
		return password;
	}

	public void setImageView(ImageView imageView) {
		// Sets a new image view of the profile
		this.imageView = imageView;
	}
	public void setFirstName(String firstName) {
		// Sets a new first name for the profile
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		// Sets a new last name for the profile
		this.lastName = lastName;
	}
	public void setUserName(String userName) {
		// Sets a new user name for the profile
		this.userName = userName;
	}
	public void setPassword(String password) {
		// Sets a new password name for the profile
		this.password = password;
	}
}
