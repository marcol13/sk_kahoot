package gui;

public class main {
    public static void main(String[] args){

        if(args.length < 1){
            System.out.println("Podaj adres serwera");
        }
        else{
            AppSettings.serverAddress = args[0];
            GUI window = new GUI();
            new Menu(window);
        }
    }
}
