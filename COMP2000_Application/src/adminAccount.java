public class adminAccount
{
    String userName;
    String passWord;

    public adminAccount (String adminUsername, String adminPassword)
    {
        userName = adminUsername;
        passWord = adminPassword;
    }

    public String GetUsername()
    {
        return userName;
    }

    public String GetPassword()
    {
        return passWord;
    }

    public void SetUsername(String accountUsername)
    {
        userName = accountUsername;
    }

    public void SetPassword(String accountPassword)
    {
        passWord = accountPassword;
    }
}
