public class loginAccount {
    public String username;
    public String password;

    public loginAccount(String accountName, String accountPass) {
        this.username = accountName;
        this.password = accountPass;
    }

    public String GetUsername() {
        return username;
    }

    public String GetPassword() {
        return password;
    }

    public void SetUsername(String loginUsername) {
        username = loginUsername;
    }

    public void SetPassword(String loginPassword) {
        password = loginPassword;
    }
}
