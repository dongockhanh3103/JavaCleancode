package vn.kms.launch.cleancode;

import vn.kms.launch.cleancode.Config.Annotation.*;

import java.lang.reflect.Field;

public class Contact implements Comparable<String>  {
    @ColunmField(value = "id", dataType = "Integer")
    private int id;

    @NotEmpty
    @ValidLength(maxLength =10)
    @ColunmField(value = "first_name", dataType = "String")
    private String firstName;

    @NotEmpty
    @ValidLength(maxLength = 10)
    @ColunmField(value = "last_name", dataType = "String")
    private String lastName;

    @ValidLength(maxLength = 20)
    @ColunmField(value = "address", dataType = "String")
    private String address;

    @ValidLength(maxLength = 15)
    @ColunmField(value = "city", dataType = "String")
    private String city;

    @StateValid
    @ColunmField(value = "state", dataType = "String")
    private String state;

    @Pattern("^\\d{4,5}$")
    @ColunmField(value = "zip",dataType = "String")
    private String zipCode;

    @Pattern(value = "^\\d{3}\\-\\d{3}\\-\\d{4}$",phoneFormat = "XXX-XXX-XXX")
    @ColunmField(value = "phone1", dataType = "String")
    private String mobilePhone;

    @Pattern("^.+@.+\\..+$")
    @ColunmField(value = "email", dataType = "String")
    private String email;

    private int age;

    @DatetimeFormat()
    @ColunmField(value = "date_of_birth",dataType = "String")
    private String dayOfBirth;

    public int getId() {
        return id;
    }

    public Contact setId(int id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Contact setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Contact setLastName(String lastName) {
        this.lastName = lastName;
        return  this;
    }

    public String getAddress() {
        return address;
    }

    public Contact setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Contact setCity(String city) {
        this.city = city;
        return  this;
    }

    public String getState() {
        return state;
    }

    public Contact setState(String state) {
        this.state = state;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Contact setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public Contact setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Contact setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Contact setAge(int age) {
        this.age = age;
        return this;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public Contact setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
        return this;
    }
    public String toLine() {
        return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s", id, firstName, lastName, dayOfBirth, address, city, state, zipCode, mobilePhone, email);
    }
    public  void set(String fname, String value){
        try {
            Field field= Contact.class.getDeclaredField(fname);
            field.setAccessible(true);
            field.set(this, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public void set(String fieldName, Integer value){
        try {
            Field field= Contact.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(this, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int compareTo(String o) {
        return 0;
    }
}

