package dermatologyapp.shahanchor.com.dermatologist.feedback;

/**
 * Created by charilj on 1/12/2017.
 */

public class FeedbackDetailsModel {
    private String enteredNameForFeedback;

    public String getEnteredEmailForFeedback() {
        return enteredEmailForFeedback;
    }

    public void setEnteredEmailForFeedback(String enteredEmailForFeedback) {
        this.enteredEmailForFeedback = enteredEmailForFeedback;
    }

    public String getEnteredNameForFeedback() {
        return enteredNameForFeedback;
    }

    public void setEnteredNameForFeedback(String enteredNameForFeedback) {
        this.enteredNameForFeedback = enteredNameForFeedback;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedBackDetails() {
        return feedBackDetails;
    }

    public void setFeedBackDetails(String feedBackDetails) {
        this.feedBackDetails = feedBackDetails;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String enteredEmailForFeedback;
    private String feedbackType;
    private String feedBackDetails;
    private String userName;
    private String userId;


}
