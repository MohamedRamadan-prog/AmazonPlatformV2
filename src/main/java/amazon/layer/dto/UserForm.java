package amazon.layer.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;


public class UserForm {
   
	@NotEmpty(message = "This field is required")
	private String name = "";
	 
	@NotEmpty(message = "This field is required")
	@Size(min = 4, max = 50, message = "This field must be from 4 min tp 50 max ")
	@Email(message = "must be in Email Form '*.*.com' ")
    private String username = "";

	@NotEmpty(message = "This field is required")
	@Size(min = 4, max = 50, message = "This field must be from 4 min tp 50 max ")
    private String password = "";

	@NotEmpty(message = "This field is required")
	@Size(min = 4, max = 50, message = "This field must be from 7 min tp 50 max ")
    private String passwordCheck = "";

    @NotEmpty(message = "field is required")
    @NonNull
    private String role = "";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
