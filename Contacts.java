package contacts;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Contacts implements Serializable {
    private static final long serialVersionUID = 1L;

    private String number;
    private transient static int counter = 0;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        Pattern pattern = Pattern.compile("^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*");
        Matcher matcher = pattern.matcher(number);
        if (matcher.matches()) {
            this.number = number;
        } else {
            this.number = "[no number]";
        }
    }

    abstract public String getName();
    abstract public String getSurname();
    abstract public String getBirthDate();
    abstract public String getGender();
    abstract public void setName(String name);
    abstract  public void setSurname(String surname);
    abstract public void setBirthDate(String birthDate);
    abstract public void setGender(String gender);
    abstract public void setAddress(String address);

    abstract public Contacts addContact();
    abstract public void editContact();
}
