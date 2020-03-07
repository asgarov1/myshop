package com.asgarov.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class ContactDetails extends AbstractPersistable<Long> {

    @Column
    @NotNull
    @NotEmpty(message = "{registerForm.error.empty}")
    String firstName;

    @Column
    String lastName;

    @Column
    @NotNull
    @NotEmpty(message = "{registerForm.error.empty}")
    @Email
    String email;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String postalCode;

    @Column
    private String country;

    public static class Builder {
        private ContactDetails contactDetails = new ContactDetails();

        public Builder firstName(String firstName) {
            contactDetails.setFirstName(firstName);
            return this;
        }

        public Builder lastName(String lastName) {
            contactDetails.setLastName(lastName);
            return this;
        }

        public Builder email(String email) {
            contactDetails.setEmail(email);
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            contactDetails.setPhoneNumber(phoneNumber);
            return this;
        }

        public Builder address(String address) {
            contactDetails.setAddress(address);
            return this;
        }

        public Builder city(String city) {
            contactDetails.setCity(city);
            return this;
        }

        public Builder postalCode(String postalCode) {
            contactDetails.setPostalCode(postalCode);
            return this;
        }

        public Builder country(String country) {
            contactDetails.setCountry(country);
            return this;
        }

        public ContactDetails build() {
            return this.contactDetails;
        }
    }
}
