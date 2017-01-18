package dermatologyapp.shahanchor.com.dermatologist.showUsers;

/**
 * Created by charilj on 1/15/2017.
 */

public class ShowUserModel {
    private String uniqueKey;
    private String firstName;
    private String lastName;

    public ShowUserModel(String uniqueKey, String firstName, String lastName, String age, String email, String sex) {
        this.uniqueKey = uniqueKey;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.sex = sex;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private String age;
    private String email;
    private String sex;

}
