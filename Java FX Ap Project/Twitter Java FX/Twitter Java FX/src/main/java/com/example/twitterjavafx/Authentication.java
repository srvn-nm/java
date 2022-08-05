package com.example.twitterjavafx;

import javafx.event.ActionEvent;
import org.ce.ap.com.company.server.impl.AuthenticationService;
import org.ce.ap.com.company.server.model.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.ce.ap.com.company.server.service.AccountFile;
import org.ce.ap.com.company.server.service.ClientFileHandler;

/**
 * AuthenticationService class for get users information like :
 *             name , userName , Password , BirthDayDate , signUpDate , Bio , ...
 *             and sing up in twitter system
 *
 * AuthenticationService class will provide log in section too ...
 *
 *this class is collaborating with Account class which will help user to sign up or sign in
 * @author Abtin Zandi , Sarvin Nami
 * @version 3.0
 */

public class Authentication implements AuthenticationService {

    public String image;

    @FXML public Button sideMenuSign3;
    @FXML public TextField ProfileImage;
    //Log In Section
    @FXML
    private Button CreateNewAccount;
    @FXML
    private Button ExitButton;
    @FXML
    private Button LogInButton;
    @FXML
    private TextField PasswordLogIn;
    @FXML
    private Text PasswordLogInWarning;
    @FXML
    private CheckBox RemembermeCheck;
    @FXML
    private TextField UserNameLogIn;
    @FXML
    private Text UserNameLogInWarning;

    //Sign Up (First Part)
    @FXML
    private TextField Day;
    @FXML
    private Text DayWarning;
    @FXML
    private Button ExitButton2;
    @FXML
    private TextField LastName;
    @FXML
    private Text LastNameWarning;
    @FXML
    private TextField Month;
    @FXML
    private Text MonthWarning;
    @FXML
    private TextField Name;
    @FXML
    private Text NameWarning;
    @FXML
    private Button NextFirstMenu;
    @FXML
    private TextField Year;
    @FXML
    private Text YearWarning;

    //Sign Up (Second Part)
    @FXML
    private Button FirstBack;
    @FXML
    private Button Next;
    @FXML
    private TextField PasswordAgain;
    @FXML
    private TextField PasswordSignUp;
    @FXML
    private Text PasswordSignUpWarning;
    @FXML
    private Text PasswordSignUpWarning2;
    @FXML
    private TextField UserNameSignUp;
    @FXML
    private Text UserNameSignUpWarning;

    //Sign Up (third Part)
    @FXML
    private TextField Bio;
    @FXML
    private Button SignUp;
    @FXML
    private Button ProFileImage;
    @FXML
    private Button SecondBack;
    @FXML
    private Text imageWarning;
    @FXML
    private Text BioWarning;

    public ArrayList<Account> twitterUsers = new ArrayList<>(); //this array will save all sign up users
    private final AccountFile usersFileManger = new AccountFile();
    private final ClientFileHandler clientFileHandler = new ClientFileHandler();

    //Log In Methods Section
    /**
     * log in  system will return our user username to open user panel in twitter
     * @param event ,
     */
    public void LogInClick(ActionEvent event) {
        updateUsers();
        boolean UserNameCheck = usernameCheck(UserNameLogIn.getText());
        boolean passwordCheck = passwordCheckForLogIn(PasswordLogIn.getText());
        if(UserNameCheck && passwordCheck){
            int clientNumber = clientFileHandler.getFxmlState("LogIn");
            clientFileHandler.NewUserAccount(clientNumber,UserNameLogIn.getText());
            clientFileHandler.updateClient(clientNumber,"TimeLineShow");
        }
        if(!UserNameCheck){
            UserNameLogInWarning.setText("Invalid User Name !");
        }
        if(UserNameCheck){
            UserNameLogInWarning.setText("");
        }
        if(!passwordCheck){
            PasswordLogInWarning.setText("Invalid password !");
        }
        if(passwordCheck){
            PasswordLogInWarning.setText("");

        }
    }

    /**
     * this method will help us to control LogIn Fxml transition
     * @param event ,
     */
    public void SignUpLogButton(ActionEvent event) {
        int clientNumber = clientFileHandler.getFxmlState("LogIn");
        clientFileHandler.updateClient(clientNumber,"SignUpSecond");
    }

    /**
     * this method will help us to control LogIn Fxml transition
     * @param event ,
     */
    public void Exit(ActionEvent event) {
        int clientNumber = clientFileHandler.getFxmlState("LogIn");
        clientFileHandler.updateClient(clientNumber,"FirstMenu");
    }

    //Sign up first section
    public void SecondSignUpLogButton(ActionEvent actionEvent) {
        boolean unRepeatedUsrName = usernameCheck(UserNameSignUp.getText());
        boolean PasswordSecurity = passwordQualityCheck(PasswordSignUp.getText());
        boolean PasswordCheck = passwordCheck(PasswordAgain.getText(),PasswordSignUp.getText());
        if(!unRepeatedUsrName && PasswordSecurity && PasswordCheck ){
            String UserData = UserNameSignUp.getText()+"\r\n" + PasswordSignUp.getText() +"\r\n"+ PasswordAgain.getText() ;
            int clientNumber = clientFileHandler.getFxmlState("SignUpSecond");
            clientFileHandler.updateClient(clientNumber,"SignUpFirst");
            clientFileHandler.updateServerFXML(clientNumber,"SignUpSecond",UserData);
        }
        if(unRepeatedUsrName){
            UserNameSignUpWarning.setText("Error : Input userName is repeated");
        }
        if(!unRepeatedUsrName){
            UserNameSignUpWarning.setText("");
        }
        if(PasswordSecurity){
            PasswordLogInWarning.setText("");
        }
        if(PasswordCheck){
            PasswordSignUpWarning2.setText("");
        }
    }

    /**
     * this mmetod will help us to control Fxml transition
     * @param actionEvent
     */
    public void BackToFirstMenu(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("SignUpSecond");
        clientFileHandler.updateClient(clientNumber,"FirstMenu");
    }

    //Sign up second section name lastname and birthday
    public void BackToFirstSignUp(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("SignUpFirst");
        clientFileHandler.updateClient(clientNumber,"SignUpSecond");
    }

    /**
     * this method will handle :
     * Sign up (name lastname ... )
     * File saving
     * @param actionEvent ,
     */
    public void FirstSignUpLogButton(ActionEvent actionEvent) {
        boolean nameCheck = stringCheck(Name.getText());
        boolean lastNameCheck = stringCheck(LastName.getText());

        //birthday is numeric
        boolean BirthDayStrDay = stringCheck(Day.getText());
        boolean BirthDayStrMonth = stringCheck(Month.getText());
        boolean BirthDayStrYear = stringCheck(Year.getText());

        //birthday Check
        boolean DayCheck = BirthDayDayCheck();
        boolean MonthCheck = BirthDayMonthCheck();
        boolean YearCheck = BirthDayYearCheck();

        if(nameCheck && lastNameCheck && !BirthDayStrDay && !BirthDayStrMonth && !BirthDayStrYear && DayCheck && MonthCheck && YearCheck ){
            String temp1 = Name.getText() +"\r\n" + LastName.getText() + "\r\n";
            String UserData = temp1 + "\r\n" + Day.getText() +"\r\n" + Month.getText() +"\r\n" + Year.getText() ;
            int clientNumber = clientFileHandler.getFxmlState("SignUpFirst");
            clientFileHandler.updateClient(clientNumber,"SignUpSecond");
            clientFileHandler.updateServerFXML(clientNumber,"SignUpFirst",UserData);
        }
        if(!nameCheck){
            NameWarning.setText("Input name is incorrect due to incorrect characters or numbers");
        }
        if(!lastNameCheck){
            LastNameWarning.setText("Input Last Name is incorrect due to incorrect characters or numbers");
        }
        if(BirthDayStrDay){
            DayWarning.setText("date is Numeric");
        }

        if(BirthDayStrMonth){
            MonthWarning.setText("date is Numeric");
        }

        if(BirthDayStrYear){
            YearWarning.setText("date is Numeric");
        }
        if(nameCheck){
            NameWarning.setText("");
        }
        if(lastNameCheck){
            LastNameWarning.setText("");
        }
        if(!BirthDayStrDay){
            DayWarning.setText("");
        }

        if(!BirthDayStrMonth){
            MonthWarning.setText("");
        }

        if(!BirthDayStrYear){
            YearWarning.setText("");
        }

    }

    /**
     * this method will help us to control birthday date
     * @return toCheck
     */
    private boolean BirthDayYearCheck() {

        int year = 0;
        boolean toCheck = false;
        try{
            year = Integer.parseInt(Year.getText());
        }catch (Exception e){
            System.out.println();
        }

        if(year>0 && year<2008){
            toCheck = true;
        }
        else if(year>=2009){
            YearWarning.setText("You can not register due to being under the age of majority !");
        }

        return toCheck;
    }

    /**
     * this method will help us to control birthday date
     * @return toCheck
     */
    private boolean BirthDayMonthCheck() {

        int month = 0;
        boolean toCheck = false;
        try{
            month = Integer.parseInt(Month.getText());
        }catch (Exception e){
            System.out.println();
        }

        if(month>0 && month<13){
            toCheck = true;
        }

        else {
            MonthWarning.setText("We dont have more than 12 month in on year !");
        }
        return toCheck;
    }

    /**
     * this method will help us to control birthday date
     * @return toCheck
     */
    private boolean BirthDayDayCheck() {

        int month = 0;
        int day = 0;
        boolean toCheck = false;

        try{
            day = Integer.parseInt(Day.getText());
            month = Integer.parseInt(Month.getText());
        }catch (Exception e){
            System.out.println();
        }

        if(month==1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 ){
            if(day<32){
                toCheck=true;
            }
            else {
                DayWarning.setText(month + " th month have only 31 days");
            }
        }
        else if(month==11 || month == 9|| month == 6 || month == 4 ){
            if(day<31){
                toCheck=true;
            }
            else {
                DayWarning.setText(month + " th month have only 30 days");
            }
        }
        else {
            if (day < 30) {
                toCheck=true;
            } else if (day > 29) {
                DayWarning.setText(month + " th month have only 29 days");
            }
        }
        return toCheck;
    }

    //sign up third section
    public void BackToSecondSignUp(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("SignUpThird");
        clientFileHandler.updateClient(clientNumber,"SignUpFirst");
    }

    /***
     * this method will help us to manage sign up event
     * @param actionEvent ,
     */
    public void SignUp(ActionEvent actionEvent) {
        boolean BioCheck = bioCheck(Bio.getText());
        image = ProfileImage.getText();
        if(BioCheck){
            String UserData =  Bio.getText() + "\r\n" + image;
            int clientNumber = clientFileHandler.getFxmlState("SignUpThird");
            clientFileHandler.updateClient(clientNumber,"TimeLineShow");
            clientFileHandler.updateServerFXML(clientNumber,"SignUpThird",UserData);
            NewAccountFxmlFile(clientNumber);
        }
        if(!BioCheck){
            BioWarning.setText("Bio len is more than 256 char limit");
        }
    }

    /**
     * Here we check that our string does not contain  a number or incorrect character.
     * ascii base ...
     * @param text
     * @return toCheck
     */
    public boolean stringCheck(String text) {
        boolean toCheck = true;
        for (Character character : text.toCharArray()) {
            int ascii = (int) character;
            if (ascii > 90 && ascii < 97) {
                toCheck = false;
                break;
            } else if (ascii > 128) {
                toCheck = false;
                break;
            } else if (ascii < 65) {
                toCheck = false;
                break;
            }
        }
        return toCheck;
    }

    /**
     * Here we check that our username is repeated or not .
     * @param password ,
     * @return toCheck (true --> repeated)
     */
    public boolean passwordCheckForLogIn(String password) {
        String Pass = null;
        try {
            Pass = toHexString(getSHA(password));
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
        boolean toCheck = false;
        for (Account account : twitterUsers) {
            if (Pass.equals(account.getPassword())) {
                toCheck = true;
            }
        }
        return toCheck;
    }

    /**
     * method to get the sha of a string
     * @param input ,
     * @return md.digest(input.getBytes(StandardCharsets.UTF_8))
     * @throws NoSuchAlgorithmException
     */
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called to calculate message digest of an input and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * method to convert a byte array a hexadecimal string value.
     * @param hash ,
     * @return hexString.toString()
     */
    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    /***
     * Here we check that our username is repeated or not .
     * @param userName ,
     * @return toCheck (false --> repeated)
     */
    public boolean usernameCheck(String userName) {

        boolean toCheck = false;
        for (Account account : twitterUsers) {
            if (userName.equals(account.getUserName())) {
                toCheck = true;
            }
        }
        return toCheck;
    }

    /**
     * Here we check password again .
     * @param password ,
     * @param SecondPassword ,
     * @return toCheck (false --> not similar)
     */
    public boolean passwordCheck(String password,String SecondPassword) {
        boolean toCheck = true;
        if (!password.equals(SecondPassword) ){
            PasswordSignUpWarning2.setText("passwords did not match together !");
            toCheck = false;
        }
        return toCheck;
    }

    /**
     * Here we check password quality for user secure
     * @param password ,
     */
    public boolean passwordQualityCheck(String password) {

        boolean toCheckQuality;

        int lowercaseRate=0;
        int NumericRate=0;
        int shapeRate=0;
        int uppercaseRate=0;

        for(Character character : password.toCharArray()){
            for(char charCheck = 'a' ; charCheck<'{';charCheck++){
                if(character == charCheck){
                    lowercaseRate = 20;
                }
            }

            for(char charCheck = 'A' ; charCheck<'[';charCheck++){
                if(character == charCheck){
                    uppercaseRate = 20;
                }
            }

            for(char charCheck = '1' ; charCheck<'9';charCheck++){
                if(character == charCheck){
                    NumericRate = 20;
                }
            }

            for(char charCheck = '!' ; charCheck<'/';charCheck++){
                if(character == charCheck){
                    shapeRate = 20;
                }
            }
        }

        int sumCheck = uppercaseRate + lowercaseRate + shapeRate + NumericRate;
        if (sumCheck < 60){
            toCheckQuality = false;
            PasswordSignUpWarning.setText("your password secure quality is %"+sumCheck+"  and its acceptable");
        }
        else {
            toCheckQuality = true;
        }

        return toCheckQuality;
    }

    /**
     * Bio check will check the length of user Bio
     * @param bio ,
     * @return to check (false --> Bio len is more than 256 char limit )
     */
    public boolean bioCheck(String bio) {
        boolean toCheck = true;
        if (bio.length() > 256 ){
            toCheck = false;
        }
        return toCheck;
    }

    /**
     * this method will help us to delete on specific account
     * @param user --> deleted account
     */
    public void removeAccount(Account user){
        String usernameFollow = user.getUserName();
        Iterator<Account> it = twitterUsers.iterator();
        while (it.hasNext()) {
            Account account = (Account) it.next();
            if (usernameFollow.equals(account.getUserName())) {
                it.remove();
            }
        }
    }

    /**
     * this method will update users list
     */
    public void updateUsers(){
        twitterUsers.clear();
        twitterUsers.addAll(usersFileManger.AllUsers());
    }

    //First side menu button
    /**
     * this method will help us to manage side menu in log in
     * @param actionEvent ,
     */
    public void sideMenuButton(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("LogIn");
        clientFileHandler.updateClient(clientNumber,"SideMenu");
    }

    /**
     * this method will help us to manage side menu in sign 3
     * @param actionEvent ,
     */
    public void sideMenuButtonSign3(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("SignUpThird");
        clientFileHandler.updateClient(clientNumber,"SideMenu");
    }

    /**
     * this method will help us to manage side menu in sign 1
     * @param actionEvent ,
     */
    public void sideMenuButtonSign1(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("SignUpSecond");
        clientFileHandler.updateClient(clientNumber,"SideMenu");
    }

    /**
     * this method will help us to manage side menu in sign 1
     * @param actionEvent ,
     */
    public void sideMenuButtonSign2(ActionEvent actionEvent) {
        int clientNumber = clientFileHandler.getFxmlState("SignUpFirst");
        clientFileHandler.updateClient(clientNumber,"SideMenu");
    }

    /**
     * this method will help us to create a new account
     * @param clientNumber ,
     */
    public void NewAccountFxmlFile(int clientNumber){
        ArrayList<String> NewUsersDetails = new ArrayList<>();
        NewUsersDetails.addAll(clientFileHandler.getFXMLDetails(clientNumber,"SignUpSecond"));
        NewUsersDetails.addAll(clientFileHandler.getFXMLDetails(clientNumber,"SignUpFirst"));
        NewUsersDetails.addAll(clientFileHandler.getFXMLDetails(clientNumber,"SignUpThird"));
        Account NewUser = new Account(NewUsersDetails.get(2),NewUsersDetails.get(3),NewUsersDetails.get(0),NewUsersDetails.get(8));
        NewUser.setPassword(NewUsersDetails.get(1));
        String Date = NewUsersDetails.get(4) +"-" + NewUsersDetails.get(5) +"-" + NewUsersDetails.get(6) +"-" ;
        NewUser.setBirthDayDate(Date);
        NewUser.setBio(NewUsersDetails.get(7));
        usersFileManger.newUser(NewUser);
        clientFileHandler.NewUserAccount(clientNumber,NewUser.getUserName());
    }
}