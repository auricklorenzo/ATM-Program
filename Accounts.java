package ATM_PROGRAM;

public class Accounts {
    private int pin;
    private static int AccNo;
    private static int AccNo_Trans;
    private static int identification_no;
    private static int identification_no_Trans;
//    private static int[] Acc_Num_OtherBank_Balances = {896905, 43836, 1948941259, 7285, 10234};
    private final int[] Acc_Num_Other = {1899103849, 2084041279, 1854411639, 1255626653, 1437124101};
    private static int[] Acc_Balances = {1234980, 235678, 983536, 42456, 5678};

    private final int[] Acc_Num = {1234567890, 2145169446, 1567866724, 2134507657, 1987654142};

    private static int[] pins =    {111204, 210705, 280511, 221234, 981001};

    public void setPin(int pin){
        this.pin = pin;
    }
    public int getPin(){
        return pin;
    }

    public int getAccNo(){
        return AccNo;
    }
    public void setAccNo(int AccNo){
        this.AccNo = AccNo;
    }
    public void setAccNo_Trans(int Accno){
        AccNo_Trans = Accno;
    }
    public int getAccNo_Trans(){
        return AccNo_Trans;
    }
    public void setIdentification_no(int i){
        identification_no = i;
    }

    public int getIdentification_no(){
        return identification_no;
    }
    public void setIdentification_no_Trans(int i){
        identification_no_Trans = i;
    }
    public int getIdentification_no_Trans(){
        return identification_no_Trans;
    }
    public int getAcc_Balances(int index){
        return Acc_Balances[index];
    }

    public void setAcc_Balances(int index, int amount){
        Acc_Balances[index] = amount;
    }

    public void setNewPins(int newPin, int index){
        pins[index] = newPin;
    }
    public boolean Valid_PINNum(int Pin){
        boolean validity = false;
        for (int i = 0; i < pins.length; i++){
            if (pins[i] == Pin){
                validity = true;
                setAccNo(Acc_Num[i]);
                setIdentification_no(i);
                break;
            }
        }
        return validity;
    }

    public boolean Valid_OtherBankAccount(int Accno){
        boolean validity = false;
        for (int i = 0; i< Acc_Num_Other.length; i++){
            if (Acc_Num_Other[i] == Accno){
                validity = true;
                setAccNo_Trans(Accno);
                break;
            }
        }
        return validity;
    }

    public boolean Valid_BankAccount(int Accno){
        boolean validity = false;
        for (int i = 0; i< Acc_Num.length; i++){
            if (Acc_Num[i] == Accno){
                if (Accno == getAccNo()){
                    break;
                }else {
                    validity = true;
                    setAccNo_Trans(Accno);
                    setIdentification_no_Trans(i);
                    break;
                }
            }
        }
        return validity;
    }

}
