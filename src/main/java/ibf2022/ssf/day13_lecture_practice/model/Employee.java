package ibf2022.ssf.day13_lecture_practice.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @NotEmpty (message = "First name is a mandatory field")
    @Size (min = 1, max = 100, message = "First name must be between 1 ~ 100 characters")
    private String firstName;

    @NotEmpty (message = "Last name is a mandatory field")
    @Size (min = 1, max = 100, message = "Last name must be between 1 ~ 100 characters")
    private String lastName;

    @Email (message = "Invalid email format")
    @Size (max = 100)
    @Pattern(regexp =  ".+@.+\\..+", message = "Wrong email format")
    @NotBlank(message = "Email is a mandatory field")
    private String email;

    // must start from 8 or 9, plus 7 chars of 0-9
    @Pattern(regexp = "(8|9)[0-9]{7}", message = "Invalid phone format")
        private String phoneNo;

    @Min(value = 1500, message = "Min salary is $1,500")
    @Max(value = 400000, message = "Max salary is 400,000")
    private Integer salary;

    // @DateTimeFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    @PastOrPresent(message = "Birthday cannot be after today")
    private Date birthday;

    private String address;

    @Digits(fraction = 0, integer = 6, message = "Postal code is 6 digits")
    private Integer postalCode;
    
}
