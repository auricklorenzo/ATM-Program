package ATM_PROGRAM;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.DecimalFormat;
abstract class Service {
    Random rand = new Random();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    LocalDateTime dateTime = LocalDateTime.now();
    Accounts Acc = new Accounts();
    Scanner input = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("#,###");
    private int amount = 0;
    private int Num_Press;
    boolean withdraw_loop = true;
    boolean custom_amount = false;
    boolean cancel_Transaction = false;
    public void setNum_Press(int press){
        Num_Press = press;
    }

    public int getNum_Press(){
        return Num_Press;
    }
    public String ShowAccNo(int AccNo){
        int count = 0;
        for (int i = 0; i < Integer.toString(AccNo).length(); i++){
            count++;
        }
        return String.valueOf(AccNo).substring(count - 4);
    }
    public String MoneyFormatter(int balance){
        return df.format(balance);
    }
    public String Spaces(int length, int Avail_space){
        String spaces  = "";
        for (int i = 0; i < Avail_space - length; i++){
            spaces += " ";
        }
        return spaces;
    }

    public void Withdraw_Validity(int amount, int balance){
        int final_amount = 0;
        if ((amount % 100) == 0 && amount >= 100){
            if (amount <= balance){
                if (amount >= 100 && amount <= 20000){
                    final_amount = balance - amount;
                    Acc.setAcc_Balances(Acc.getIdentification_no(), final_amount);
                    withdraw_loop = false;
                    custom_amount = false;
                }else {
                    System.out.println("     Please Follow the Max. and Min. Amount!\n");
                    withdraw_loop = true;
                    custom_amount = true;
                }
            }else {
                System.out.println("                 Invalid Input!\n   The Amount entered is more than the Balance!\n");
                withdraw_loop = true;
                custom_amount = true;
            }
        }else {
            System.out.println("           Please Enter a Whole Amount!\n      And must be more than the Min. Amount!\n");
            withdraw_loop = true;
            custom_amount = true;
        }
    }
    public void CheckBalance(int Accno, int balance) throws InterruptedException{
        boolean receipt_loop = true;
        System.out.println("                Please wait, your ");
        System.out.println("         transaction is being process....\n\n");
        Thread.sleep(3000);
        System.out.println("==================================================");
        System.out.println("             Y O U R  B A L A N C E :             ");
        System.out.println("==================================================\n");
        Thread.sleep(1000);
        System.out.println("         ===============================");
        System.out.println("         Account Number : ******" + ShowAccNo(Accno));
        System.out.println("         Balance        : ₱ " + MoneyFormatter(balance));
        System.out.println("         ===============================\n");
        do {
            try {
                System.out.print("          Do you want a receipt? (Y/N) ");
                String receipt_ans = input.nextLine();

                if (receipt_ans.equalsIgnoreCase("Y")){
                    System.out.println();
                    int record_no = rand.nextInt(1000, 9999);
                    System.out.println("   ============================================");
                    System.out.println("   |                NVTR BANK                 |");
                    System.out.println("   |        For Customer Service Call         |");
                    System.out.println("   |              0912-456-7889               |");
                    System.out.println("   |                                          |");
                    System.out.println("   |           Lakewood, California           |");
                    System.out.println("   |                                          |");
                    System.out.println("   | Date & Time      : " + dtf.format(dateTime)+"   |");
                    System.out.println("   | Receipt No.      : " + record_no + "                  |");
                    System.out.println("   | Account No.      : " + "******" + ShowAccNo(Accno) + "            |");
                    System.out.println("   |                                          |");
                    System.out.println("   |------------------------------------------|");
                    System.out.println("   | Transaction        :     Balance Inquiry |");
                    System.out.println("   | Available Balance  : " + Spaces(MoneyFormatter(balance).length(), 17) + "₱ " + MoneyFormatter(balance) + " |");
                    System.out.println("   |                                          |");
                    System.out.println("   |                                          |");
                    System.out.println("   |    Thank you For choosing NVTR BANK:)    |");
                    System.out.println("   ============================================\n");
                    receipt_loop = false;
                }else if (receipt_ans.equalsIgnoreCase("N")){
                    receipt_loop = false;
                }else {
                    System.out.println("\n             Wrong Input, Try Again!\n");
                    continue;
                }
            }catch (Exception ex){
                System.out.println("\n                 Invalid Input!\n");
                continue;
            }
        }while (receipt_loop);
    }

    public void WithdrawMoney(int AccNo, int balance) throws InterruptedException{
        int withdraw_choice = 0;
        System.out.println("==================================================");
        System.out.println("               W I T H D R A W A L                ");
        System.out.println("==================================================\n");
        System.out.println("                 Press 1  ₱100");
        System.out.println("                 Press 2  ₱500");
        System.out.println("                 Press 3  ₱1000");
        System.out.println("                 Press 4  ₱10,000");
        System.out.println("                 Press 5  Others");
        System.out.println("                 Press 6  Cancel\n");
        do {
            try {
                System.out.print("                     Press: ");
                withdraw_choice = Integer.parseInt(input.nextLine());
                if (withdraw_choice > 6){
                    System.out.println("\n         Please pick One from the Choices!\n");
                    continue;
                }else if (withdraw_choice < 1){
                    System.out.println("\n         Please pick One from the Choices!\n");
                    continue;
                }else if (withdraw_choice <= 6 && withdraw_choice >= 1){
                    withdraw_loop = false;
                }
            }catch (NumberFormatException ex){
                System.out.println("\n                 Invalid Input!\n");
            }
        }while (withdraw_loop);

        switch (withdraw_choice){
            case 1:
                amount = 100;
                Withdraw_Validity(amount, Acc.getAcc_Balances(Acc.getIdentification_no()));
                custom_amount = false;
                break;
            case 2:
                amount = 500;
                Withdraw_Validity(amount, Acc.getAcc_Balances(Acc.getIdentification_no()));
                custom_amount = false;
                break;
            case 3:
                amount = 1000;
                Withdraw_Validity(amount, Acc.getAcc_Balances(Acc.getIdentification_no()));
                custom_amount = false;
                break;
            case 4:
                amount = 10000;
                Withdraw_Validity(amount, Acc.getAcc_Balances(Acc.getIdentification_no()));
                custom_amount = false;
                break;
            case 5:
                System.out.println();
                System.out.println("      The Maximum Withdrawal is ₱20,000 and");
                System.out.println("         the Minimum Withdrawal is ₱100\n\n");
                custom_amount = true;
                break;
            case 6:
                cancel_Transaction = true;
                Thread.sleep(5000);
                return;
            default:
                System.out.println("");
        }

        while (custom_amount){
            try {
                System.out.println("         Please Enter the desired amount");
                System.out.print("                    ₱ ");
                amount = Integer.parseInt(input.nextLine());
                System.out.println();
                custom_amount = false;
            }catch (NumberFormatException ex){
                System.out.println("\n              Invalid Input! Try Again\n");
                continue;
            }
            Withdraw_Validity(amount, Acc.getAcc_Balances(Acc.getIdentification_no()));
        }
        System.out.println("\n                Please wait, your ");
        System.out.println("         transaction is being process....\n\n");
        Thread.sleep(8000);
        System.out.println("              Please Get your Cash...");
        Thread.sleep(1000);
        System.out.println();
        System.out.println();

        boolean receipt_loop = true;
        do {
            try {
                System.out.print("          Do you want a receipt? (Y/N) ");
                String receipt_ans = input.nextLine();

                if (receipt_ans.equalsIgnoreCase("Y")){
                    System.out.println();
                    int record_no = rand.nextInt(1000, 9999);
                    String amt = MoneyFormatter(amount);

                    System.out.println("   ============================================");
                    System.out.println("   |                NVTR BANK                 |");
                    System.out.println("   |        For Customer Service Call         |");
                    System.out.println("   |              0912-456-7889               |");
                    System.out.println("   |                                          |");
                    System.out.println("   |           Lakewood, California           |");
                    System.out.println("   |                                          |");
                    System.out.println("   | Date & Time : " + dtf.format(dateTime)+"        |");
                    System.out.println("   | Receipt No. : " + record_no + "                       |");
                    System.out.println("   | Account No. : " + "******" + ShowAccNo(AccNo) + "                 |");
                    System.out.println("   |                                          |");
                    System.out.println("   |------------------------------------------|");
                    System.out.println("   | Transaction        :          Withdrawal |");
                    System.out.println("   | Withdraw Amount    : " + Spaces(amt.length(), 17) + "₱ " + amt + " |");
                    System.out.println("   | Available Balance  : " + Spaces(MoneyFormatter(balance).length(), 17) + "₱ " + MoneyFormatter(Acc.getAcc_Balances(Acc.getIdentification_no())) + " |");
                    System.out.println("   |                                          |");
                    System.out.println("   |                                          |");
                    System.out.println("   |    Thank you For choosing NVTR BANK:)    |");
                    System.out.println("   ============================================\n");
                    receipt_loop = false;
                }else if (receipt_ans.equalsIgnoreCase("N")){
                    receipt_loop = false;
                }else {
                    System.out.println("\n             Wrong Input, Try again!\n");
                    continue;
                }
            }catch (Exception ex){
                System.out.println("\n                 Invalid Input!\n");
                continue;
            }
        }while (receipt_loop);
    }

    public void TransferMoney(int Accno, int balance)  throws InterruptedException{
        boolean main_loop = true;
        boolean press_loop = true;
        boolean Accno_loop = true;
        boolean confirmation_loop = true;
        boolean Amount_loop = true;
        boolean receipt_loop = true;
        int acc_choice = 0;
        int confirm_press = 0;
        int fee = 0;
        int AccNo_Trans = 0;
        int Send_Money = 0;
        int final_amount = 0;
        int total = 0;
        boolean isNVTRAcc = false;
        boolean isOtherAcc  = false;
        boolean isAccNoCorrect = false;
        System.out.println("==================================================");
        System.out.println("                S E N D  M O N E Y                ");
        System.out.println("==================================================\n");
        while (main_loop){
            while (press_loop){
                System.out.println("<----------- SELECT THE ACCOUNT TYPE ------------>\n");
                System.out.println("       Other Bank Account has a Fee of ₱ 10");
                System.out.println("            Press 1 NVTR Account");
                System.out.println("            Press 2 Other Bank Account");
                System.out.println("            Press 3 to Cancel\n");
                try {
                    System.out.print("                     Press: ");
                    acc_choice = Integer.parseInt(input.nextLine());

                    if (acc_choice > 3 || acc_choice < 1){
                        System.out.println("\n         Please pick One from the Choices!\n");
                        Thread.sleep(1000);
                        continue;
                    }else {
                        press_loop = false;
                    }
                }catch (NumberFormatException ex){
                    System.out.println("\n                 Invalid Input!\n");
                    Thread.sleep(1000);
                }
            }

            if (acc_choice == 1){
                fee = 0;
                isNVTRAcc = true;
            }else if (acc_choice == 2){
                fee = 10;
                isOtherAcc = true;
            }else if (acc_choice == 3){
                Thread.sleep(5000);
                cancel_Transaction = true;
                return;
            }

            do {
                try {
                    System.out.println("\n         Please Enter the Account Number");
                    System.out.print("                    ");
                    AccNo_Trans = Integer.parseInt(input.nextLine());
                    System.out.println();
                }catch (NumberFormatException ex){
                    System.out.println("                 Invalid Input!");
                    continue;
                }
                int count = 0;
                for (int i = 0; i < Integer.toString(AccNo_Trans).length(); i++){
                    count++;
                }
                int EXACT_LEN = 10;
                if(count > EXACT_LEN){
                    System.out.println("\n             Invalid Account Number!\n");
                    Thread.sleep(1000);
                    continue;
                }else if(count < EXACT_LEN){
                    System.out.println("\n             Invalid Account Number!\n");
                    Thread.sleep(1000);
                    continue;
                }else if(count == EXACT_LEN){
                    if (isNVTRAcc){
                        isAccNoCorrect = Acc.Valid_BankAccount(AccNo_Trans);
                    }else if (isOtherAcc){
                        isAccNoCorrect = Acc.Valid_OtherBankAccount(AccNo_Trans);
                    }
                }
                do {
                    try {
                        System.out.println("           Please Enter the Cash Amount");
                        System.out.print("                    ₱ ");
                        Send_Money = Integer.parseInt(input.nextLine());
                    }catch (NumberFormatException ex){
                        System.out.println("\n                 Invalid Input!\n");
                        continue;
                    }
                    if (Send_Money > 300000){
                        System.out.println("\n           Sorry, the Maximum Amount of\n             Transaction is ₱ 300,000");
                        continue;
                    }else {
                        Amount_loop = false;
                    }
                }while (Amount_loop);
                if (isAccNoCorrect){
                    System.out.println("\n");
                    System.out.println("<------------- TRANSACTION DETAILS --------------->\n");
                    System.out.println("         ===============================");
                    System.out.println("         Account Number : " + Acc.getAccNo_Trans());
                    System.out.println("         Cash Amount    : ₱ " + MoneyFormatter(Send_Money));
                    System.out.println("         ===============================\n");
                    System.out.println("                Press 1 to Confirm");
                    System.out.println("                Press 2 to Edit");
                    System.out.println("                Press 3 to Cancel");
                    break;
                } else if (AccNo_Trans == Acc.getAccNo()) {
                    System.out.println("\n              Invalid Account Number\n  You can't transfer money to your own account!");
                } else {
                    System.out.println("  Sorry, the Account you Entered cannot be Found");
                    System.out.println("                 Please Try Again");
                }
            }while (Accno_loop);

            System.out.println();
            do {
                try {
                    System.out.print("                     Press: ");
                    confirm_press = Integer.parseInt(input.nextLine());
                    if (confirm_press > 3 || confirm_press <= 0){
                        System.out.println("\n         Please pick One from the Choices!\n");
                        continue;
                    }else {
                        System.out.println("\n");
                        confirmation_loop = false;
                    }
                }catch (NumberFormatException ex){
                    System.out.println("\n                 Invalid Input!\n");
                }
            }while (confirmation_loop);
            if (confirm_press == 1){
                System.out.println("                 Please wait your ");
                System.out.println("         transaction is being process....\n\n");
                Thread.sleep(5000);
                total = Send_Money + fee;
                final_amount = balance - total;
                Acc.setAcc_Balances(Acc.getIdentification_no(), final_amount);
                int receiver_balance = Acc.getAcc_Balances(Acc.getIdentification_no_Trans()) + Send_Money;
                if (isNVTRAcc){
                    Acc.setAcc_Balances(Acc.getIdentification_no_Trans(), receiver_balance);
                }
                System.out.println("          The Transaction is Successful\n");
            }else if (confirm_press == 2){
                press_loop = false;
                Accno_loop = true;
                System.out.println("<--------------- EDIT TRANSACTION--------------- >\n");
                continue;

            }else if (confirm_press == 3){
                press_loop = true;
                continue;
            }
            do {
                try {
                    System.out.print("          Do you want a receipt? (Y/N) ");
                    String receipt_ans = input.nextLine();

                    if (receipt_ans.equalsIgnoreCase("Y")){
                        System.out.println();
                        int record_no = rand.nextInt(1000, 9999);
                        System.out.println("   ============================================");
                        System.out.println("   |                NVTR BANK                 |");
                        System.out.println("   |        For Customer Service Call         |");
                        System.out.println("   |              0912-456-7889               |");
                        System.out.println("   |                                          |");
                        System.out.println("   |           Lakewood, California           |");
                        System.out.println("   |                                          |");
                        System.out.println("   | Date & Time      : " + dtf.format(dateTime)+"   |");
                        System.out.println("   | Receipt No.      : " + record_no + "                  |");
                        System.out.println("   | Account No.      : " + "******" + ShowAccNo(Accno) + "            |");
                        System.out.println("   | Receiver Acc No. : " + Acc.getAccNo_Trans() + "            |");
                        System.out.println("   |                                          |");
                        System.out.println("   |------------------------------------------|");
                        System.out.println("   | Transaction        :          Send Money |");
                        System.out.println("   | Cash Amount        : " + Spaces(MoneyFormatter(Send_Money).length(), 17) + "₱ " + MoneyFormatter(Send_Money) + " |");
                        System.out.println("   | Transaction Fee    : " + Spaces(Integer.toString(fee).length(), 17) + "₱ " + fee + " |");
                        System.out.println("   |" + Spaces(MoneyFormatter(total).length() + 9, 41) + "Total: ₱ " + MoneyFormatter(total) + " |");
                        System.out.println("   |                                          |");
                        System.out.println("   | Available Balance  : " + Spaces(MoneyFormatter(balance).length(), 17) + "₱ " +MoneyFormatter(Acc.getAcc_Balances(Acc.getIdentification_no())) + " |");
                        System.out.println("   |                                          |");
                        System.out.println("   |                                          |");
                        System.out.println("   |    Thank you For choosing NVTR BANK:)    |");
                        System.out.println("   ============================================\n");
                        receipt_loop = false;
                        main_loop = false;
                    }else if (receipt_ans.equalsIgnoreCase("N")){
                        receipt_loop = false;
                        main_loop = false;
                    }else {
                        System.out.println("\n             Wrong Input, Try Again!\n");
                        continue;
                    }
                }catch (Exception ex){
                    System.out.println("\n                 Invalid Input!\n");
                    continue;
                }
            }while (receipt_loop);
        }

    }

    public void ChangePin(int Accno, int index, int pin) throws InterruptedException{
        boolean main_loop = true;
        boolean enter_new_pin = true;
        boolean reEnterPin = true;
        boolean confirm_loop = false;
        boolean receipt_loop = true;
        int newPin1 = 0;
        int newPin2 = 0;
        int press_confirm = 0;
        System.out.println("==================================================");
        System.out.println("                C H A N G E  P I N                ");
        System.out.println("==================================================\n");

        while (main_loop){
            while (enter_new_pin) {
                try {
                    System.out.println("          Please Enter your New 6-Digit");
                    System.out.println("          Personal Identification Number\n");
                    System.out.print("                      ");
                    newPin1 = Integer.parseInt(input.nextLine());
                }catch (NumberFormatException ex){
                    System.out.println("\n             Invalid New PIN Number!\n");
                    continue;
                }
                int count = 0;
                for (int i = 0; i < Integer.toString(newPin1).length(); i++) {
                    count++;
                }
                int EXACT_LEN = 6;
                if (count > EXACT_LEN){
                    System.out.println("\n             Invalid New PIN Number!\n             PIN No. Must be 6-Digits\n");
                    continue;
                } else if (count < EXACT_LEN) {
                    System.out.println("\n             Invalid New PIN Number!\n             PIN No. Must be 6-Digits\n");
                    continue;
                }
                while (reEnterPin){
                    try {
                        System.out.println("\n        Please Re-Enter your New 6-Digit");
                        System.out.println("          Personal Identification Number\n");
                        System.out.print("                      ");
                        System.out.println();
                        newPin2 = Integer.parseInt(input.nextLine());
                    }catch (NumberFormatException ex){
                        System.out.println("\n             Invalid New PIN Number!\n");
                        Thread.sleep(1000);
                        continue;
                    }
                    int count_reEnterPin = 0;
                    for (int i = 0; i < Integer.toString(newPin1).length(); i++) {
                        count_reEnterPin++;
                    }
                    if (count_reEnterPin > EXACT_LEN){
                        System.out.println("\n             Invalid New PIN Number!\n             PIN No. Must be 6-Digits\n");
                        Thread.sleep(1000);
                        continue;
                    }else if (count_reEnterPin < EXACT_LEN) {
                        System.out.println("\n             Invalid New PIN Number!\n             PIN No. Must be 6-Digits\n");
                        Thread.sleep(1000);
                        continue;
                    } else if (count_reEnterPin == EXACT_LEN) {
                        break;

                    }
                }
                if (newPin2 == pin && newPin1 == pin) {
                    System.out.println("\n The new PIN Number cannot be the old PIN Number!");
                    System.out.println("                 Please Try Again\n");
                    Thread.sleep(1000);
                    continue;

                }else if (newPin1 == newPin2){
                    confirm_loop = true;
                    reEnterPin = false;
                    enter_new_pin = false;
                }
                else {
                    System.out.println("\n        The New PIN Number does not Match!\n                Please Try Again!\n");
                    Thread.sleep(1000);
                    continue;
                }
            }
            System.out.println("<--------------- CONFIRM NEW PIN --------------->\n");
            System.out.println("                Press 1 to Confirm");
            System.out.println("                Press 2 to Edit");
            System.out.println("                Press 3 to Cancel\n");

            do {
                try {
                    System.out.print("                     Press: ");
                    press_confirm = Integer.parseInt(input.nextLine());
                }catch (NumberFormatException ex){
                    System.out.println("\n                 Invalid Input!\n");
                    continue;
                }
                if (press_confirm > 3){
                    System.out.println("\n         Please pick One from the Choices!\n");
                    continue;
                } else if (press_confirm < 1) {
                    System.out.println("\n         Please pick One from the Choices!\n");
                    continue;
                }else {
                    confirm_loop = false;
                }
            }while (confirm_loop);

            if (press_confirm == 1){
                System.out.println("\n\n                Please wait, your");
                System.out.println("         transaction is being process....\n");
                Acc.setNewPins(newPin2, index);
                Acc.setPin(newPin2);
                Thread.sleep(4000);
                System.out.println("           PIN has Successfully Change\n");
            }else if (press_confirm == 2){
                enter_new_pin = true;
                reEnterPin = true;
                continue;
            }else if (press_confirm == 3){
                cancel_Transaction = true;
                return;
            }

            do {
                try {
                    System.out.print("          Do you want a receipt? (Y/N) ");
                    String receipt_ans = input.nextLine();

                    if (receipt_ans.equalsIgnoreCase("Y")){
                        System.out.println();
                        int record_no = rand.nextInt(1000, 9999);
                        System.out.println("   ============================================");
                        System.out.println("   |                NVTR BANK                 |");
                        System.out.println("   |        For Customer Service Call         |");
                        System.out.println("   |              0912-456-7889               |");
                        System.out.println("   |                                          |");
                        System.out.println("   |           Lakewood, California           |");
                        System.out.println("   |                                          |");
                        System.out.println("   | Date & Time      : " + dtf.format(dateTime)+"   |");
                        System.out.println("   | Receipt No.      : " + record_no + "                  |");
                        System.out.println("   | Account No.      : " + "******" + ShowAccNo(Accno) + "            |");
                        System.out.println("   |                                          |");
                        System.out.println("   |------------------------------------------|");
                        System.out.println("   | Transaction        :          Change PIN |");
                        System.out.println("   | New PIN Number     : " + Spaces(Integer.toString(newPin2).length(), 19) + newPin2 + " |");
                        System.out.println("   |                                          |");
                        System.out.println("   |                                          |");
                        System.out.println("   |    Thank you For choosing NVTR BANK:)    |");
                        System.out.println("   ============================================\n");
                        receipt_loop = false;
                        main_loop = false;
                    }else if (receipt_ans.equalsIgnoreCase("N")){
                        receipt_loop = false;
                        main_loop = false;
                    }else {
                        System.out.println("\n             Wrong Input, Try Again!\n");
                        continue;
                    }
                }catch (Exception ex){
                    System.out.println("\n                 Invalid Input!\n");
                    continue;
                }
            }while (receipt_loop);

        }

    }

}
