package dermatologyapp.shahanchor.com.dermatologist;

/**
 * Created by charilj on 12/21/2016.
 */

public class Constants {
    public static final String invalidFeedbackType="Select Valid Feedback Type";
    public static final String invalidFeedbackDetails="Enter Feedback Details";
    public static final String invalidName="Enter Valid Name";
    public static final String invalidFirstName = "Enter Valid First Name";
    public static final String invalidLastName = "Enter Valid Last Name";
    public static final String invalidAge = "Enter Valid Age";
    public static final String invalidEmail = "Enter Valid Email";
    public static final String invalidPassword = "Enter Valid Password";
    public static final String invalidConfirmPassword = "Enter Valid Confirm Password";
    public static final String selectValidGender = "Select Valid Gender";
    public static final String registrationProgressDialogMessage = "Registering Details ....";
    public static final String noInternetMessage = "No Internet Available";
    public static final String registrationSucessfulMessage = "You Have Sucessfully Registered..";
    public static final String loginSucessfulMessage = "You Have Sucessfully Login..";
    public static final String loginUnsucessfulMessage = "Invalid Login..";


    /*
      ----- Menu Labels For Users-----
     */
    public static final String menuItemViewCases = "View Cases";
    public static final String menuItemNewCases = "New Case";
    public static final String menuItemHelp = "Help";
    public static final String menuItemFeedBack = "FeedBack";
    public static final String menuItemSignOut = "SignOut";


    /*
      ----- Menu Labels For Admin-----
     */
    public static final String menuItemViewUsers = "View Users";
    public static final String menuItemViewDoctors = "View Doctors";
    public static final String menuItemViewFeedbacks = "View Feedbacks";


    /*
        --- FireBase Constants
         */
    public static final String childUserDetails = "usersDetails";
    public static final String childAdminDetails = "adminDetails";
    public static final String childDoctorDetails = "doctorDetails";
    public static final String userFeedbackDetails = "userFeedbackDetails";


    /*
       -- Shared Preferences Fields---
     */
    public static final String sharedPrefernceUserEmailId = "sharedPreferenceUserEmailId";
    public static final String sharedPrefernceUserFirstName= "sharedPrefernceUserFirstName";
    public static final String sharedPrefernceUserLastName= "sharedPrefernceUserLasttName";
}