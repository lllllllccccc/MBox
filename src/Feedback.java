public class Feedback {
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private String reviewID;
    private boolean longFeedback;

    public Feedback(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName; }
    public String getLastName() {
        return lastName; }
    public String getEmail() {
        return email; }


    public void analyseFeedback(boolean isConcatenation, String sent1, String sent2, String sent3, String sent4, String sent5) {
        if (isConcatenation) {
            completeFeedback = feedbackUsingConcatenation(sent1,sent2,sent3,sent4, sent5);
        } else {
            completeFeedback = feedbackUsingStringBuilder(sent1, sent2, sent3, sent4, sent5);
        }
        checkFeedbackLength(completeFeedback);
        createReviewID(firstName, lastName, completeFeedback);
    }
private void createReviewID(String firstName, String lastName, String feedback){
        reviewID=(firstName+lastName).substring(0,4)+System.currentTimeMillis();
}
private boolean checkFeedbackLength(String feedback){
        if (feedback.length()>500){
            longFeedback =true;
            return longFeedback;}
            longFeedback=false;
        return longFeedback;
}

    private String feedbackUsingStringBuilder(String sent1,String sent2,String sent3,String sent4,String sent5){
        StringBuilder sb =new StringBuilder();
        sb.append(sent1);
        sb.append(sent2);
        sb.append(sent3);
        sb.append(sent4);
        sb.append(sent5);
        return sb.toString();
}
    public String toString() {
        return "Feedback from" +
                 firstName + " " + lastName + '\'' +
                ", email='" + email + '\'' +
                ", completeFeedback='" + completeFeedback + '\'' +
                ", longFeedback=" + longFeedback +
                ", reviewID='" + reviewID + '\'' +
                '}';
    }

    private String feedbackUsingConcatenation(String sent1, String sent2, String sent3, String sent4, String sent5) {
        String concatenatedFeedback = "";
        concatenatedFeedback += sent1;
        concatenatedFeedback += sent2;
        concatenatedFeedback += sent3;
        concatenatedFeedback += sent4;
        concatenatedFeedback += sent5;

        return concatenatedFeedback;
    }
public static void main(String[] args){
        String sent1="I was very satisfied with the service.";
        String sent2="The e-Bike is quite comfortable to ride.";
        String sent3="The battery life of the e-Bike is impressive";
        String sent4="The customer support was helpful and responsive.";
        String sent5="I would recommend this e-bike to my friends and family.";
        Feedback feedback=new Feedback("Si","Zeen","youxiang");
        feedback.analyseFeedback(true,sent1,sent2,sent3,sent4,sent5);
        System.out.println(feedback);
} 

}
