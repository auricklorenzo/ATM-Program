package ATM_PROGRAM;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main extends Service{
    static Scanner input = new Scanner(System.in);
    static Main main = new Main();
    static Accounts Acc = new Accounts();
    static boolean loop = true;
    static boolean main_loop2 = false;
    static boolean pin_loop = true;
    static int no_try = 0;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("==================================================");
        System.out.println("              A T M   M A C H I N E               ");
        System.out.println("==================================================\n\n");

        while (loop){
            System.out.println("     |\\ | \\  / ¯¯|¯¯ |¯>   |¯>  /¯\\  |\\ | |_/");
            System.out.println("     | \\|  \\/    |   |¯\\   |¯> /   \\ | \\| | \\");
            System.out.println();
            System.out.println("              Welcome to NVTR BANK!");
            System.out.println();
            main.InsertCard();
            do {
                main.PINNum();
                if (no_try == 3){
                    System.out.println("       Number of Attempts has been reached\n" +
                            "             Please Try Again Later!\n\n");
                    no_try = 0;
                    Thread.sleep(1000);
                    System.out.println("             Please Get your Card....");
                    Thread.sleep(3000);
                    System.out.println();
                    System.out.println("          Thank you for Banking with us!\n\n\n");
                    Thread.sleep(5000);
                    pin_loop = false;
                }else {
                    main_loop2 = true;
                    pin_loop = false;
                }
            }while (pin_loop);
            if (no_try == 3){
                continue;
            }
            while (main_loop2){
                main.ListOfService();
                if (main.getNum_Press() == 1) {
                    main.CheckBalance(Acc.getAccNo(), Acc.getAcc_Balances(Acc.getIdentification_no()));
                    if (main.AnotherTransc()){
                        System.out.println();
                        Thread.sleep(2000);
                        continue;
                    }else {
                        Thread.sleep(2000);
                        System.out.println("\n             Please Get your Card....");
                        Thread.sleep(3000);
                        System.out.println();
                        System.out.println("          Thank you for Banking with us!\n\n\n");
                        Thread.sleep(4000);
                        main_loop2 = false;
                        break;
                    }
                }else if (main.getNum_Press() == 2){
                    main.WithdrawMoney(Acc.getAccNo(), Acc.getAcc_Balances(Acc.getIdentification_no()));
                    if (main.cancel_Transaction){
                        System.out.println("\n\n");
                        continue;
                    }else{
                        if (main.AnotherTransc()){
                            System.out.println();
                            Thread.sleep(2000);
                            continue;
                        }else {
                            Thread.sleep(2000);
                            System.out.println("\n             Please Get your Card....");
                            Thread.sleep(3000);
                            System.out.println();
                            System.out.println("          Thank you for Banking with us!\n\n\n");
                            Thread.sleep(4000);
                            main_loop2 = false;
                            break;
                        }
                    }
                }else if (main.getNum_Press() == 3){
                    main.TransferMoney(Acc.getAccNo(), Acc.getAcc_Balances(Acc.getIdentification_no()));
                    if (main.cancel_Transaction){
                        System.out.println("\n\n");
                        continue;
                    }else{
                        if (main.AnotherTransc()){
                            System.out.println();
                            Thread.sleep(2000);
                            continue;
                        }else {
                            Thread.sleep(2000);
                            System.out.println("\n             Please Get your Card....");
                            Thread.sleep(3000);
                            System.out.println();
                            System.out.println("          Thank you for Banking with us!\n\n\n");
                            Thread.sleep(4000);
                            main_loop2 = false;
                            break;
                        }
                    }
                }else if (main.getNum_Press()  == 4){
                    main.ChangePin(Acc.getAccNo(), Acc.getIdentification_no(), Acc.getPin());
                    if (main.cancel_Transaction){
                        System.out.println("\n\n");
                        continue;
                    }else{
                        if (main.AnotherTransc()){
                            System.out.println();
                            Thread.sleep(2000);
                            continue;
                        }else {
                            Thread.sleep(2000);
                            System.out.println("\n             Please Get your Card....");
                            Thread.sleep(3000);
                            System.out.println();
                            System.out.println("          Thank you for Banking with us!\n\n\n");
                            Thread.sleep(4000);
                            main_loop2 = false;
                            break;
                        }
                    }
                } else if (main.getNum_Press() == 5) {
                    Thread.sleep(2000);
                    System.out.println("\n             Please Get your Card....");
                    Thread.sleep(3000);
                    System.out.println();
                    System.out.println("          Thank you for Banking with us!\n\n\n");
                    Thread.sleep(4000);
                    main_loop2 = false;
                    break;
                }
            }

        }
    }

    public void PINNum() throws InterruptedException {
        boolean main_loop = true;
        int pin = 0;
        while (main_loop) {
            boolean pin_loop = true;
            if (no_try == 3){
                pin_loop = false;
                break;
            }
            do {
                try {
                    System.out.print("            Please Enter your PIN: ");
                    pin = Integer.parseInt(input.nextLine());
                    System.out.println();
                    pin_loop = false;
                } catch (NumberFormatException ex) {
                    no_try++;
                    System.out.println("\n   Please Enter the Correct format of your PIN!\n");
                    Thread.sleep(1000);
                }
            } while (pin_loop);

            int count = 0;
            for (int i = 0; i < Integer.toString(pin).length(); i++) {
                count++;
            }
            int EXACT_LEN = 6;
            if (count > EXACT_LEN) {
                System.out.println("               Invalid PIN Number!\n");
                no_try++;
                Thread.sleep(1000);
                continue;
            } else if (count < EXACT_LEN) {
                System.out.println("               Invalid PIN Number!\n");
                no_try++;
                Thread.sleep(1000);
                continue;
            } else if (count == EXACT_LEN) {
                Acc.setPin(pin);
                if (Acc.Valid_PINNum(Acc.getPin())) {
                    break;
                } else {
                    no_try++;
                    System.out.println("                No Account Found!\n" +
                            "                Please Try Again!\n");
                    Thread.sleep(1000);
                    continue;
                }
            }


        }
    }

    public void ListOfService() throws InterruptedException{
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        System.out.println("|                                                |");
        System.out.println("|         L I S T  O F  S E R V I C E S          |");
        System.out.println("|                                                |");
        System.out.println("|           Press 1 Balance Inquiry              |");
        System.out.println("|           Press 2 Withdraw Cash                |");
        System.out.println("|           Press 3 Send Money                   |");
        System.out.println("|           Press 4 Change PIN                   |");
        System.out.println("|           Press 5 Cancel                       |");
        System.out.println("|                                                |");
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_\n");
        boolean main_loop = true;
        int Num_Press = 0;
        while (main_loop){
            boolean press_loop = true;
            int Last_Option = 5;
            do {
                try {
                    System.out.print("                     Press: ");
                    Num_Press = Integer.parseInt(input.nextLine());
                    if (Num_Press > Last_Option || Num_Press < 1){
                        System.out.println();
                        System.out.println("         Please pick One from the Choices!\n");
                        Thread.sleep(1000);
                        continue;
                    }else {
                        press_loop = false;
                    }
                }catch (NumberFormatException ex){
                    System.out.println("\n                 Invalid Input!");
                    Thread.sleep(1000);
                    System.out.println();
                }
            }while (press_loop);
            System.out.println();
            System.out.println();

            int count_press = 0;

            for (int i = 0; i < Integer.toString(Num_Press).length(); i++){
                count_press++;
            }
            int LEN_PRESS = 1;
            if (count_press == LEN_PRESS ){
                setNum_Press(Num_Press);
                main_loop = false;
            }else if (count_press > LEN_PRESS){
                System.out.println("              Invalid Input! Try Again");
                Thread.sleep(1000);
                main_loop = true;
            } else if (count_press < LEN_PRESS) {
                System.out.println("              Please Input a Value!");
                Thread.sleep(1000);
                main_loop = true;
            }
        }
    }
    public void InsertCard() throws InterruptedException{
        Thread.sleep(2000);
        System.out.println("            Insert your Card please...");
        Thread.sleep(5000);
        BufferedReader Br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("            Press Enter to Continue...");
        try {
            Br.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n");
    }

    public boolean AnotherTransc(){
        boolean AT = true;
        boolean AT_Valid = false;
        do {
            try {
                System.out.print("    Do you want another transaction? (Y/N) ");
                String AT_answer = input.nextLine();
                if (AT_answer.equalsIgnoreCase("Y")){
                    AT = false;
                    AT_Valid = true;
                }else if (AT_answer.equalsIgnoreCase("N")){
                    AT = false;
                    AT_Valid = false;
                }else {
                    System.out.println("\n             Wrong Input, Try again!\n");
                    AT = true;
                }
            }catch (Exception ex){
                ex.printStackTrace();
                System.out.println("\n                 Invalid Input!\n");
            }
        }while (AT);


        return AT_Valid;
    }
}
