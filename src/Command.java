public class Command {
    String user;
    String message;
    String command;
    String zipcode;
    String units;

    Command(String user, String message, String command, String zipcode, String units){
        this.user = user;
        this.message = message;
        this.command = command;
        this.zipcode = zipcode;
        this.units = units;
    }

    boolean equals(String s){
        return command.equals(s);
    }

    boolean equalsIgnoreCase(String s){
        return command.equalsIgnoreCase(s);
    }

    public String toString(){
        return "user: " + user + "\nmessage: " + message + "\ncommand: " + command + "\nzipcode: " + zipcode + "\nunits: " + units;
    }
}
