package com.atm.process;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Main atmOperations = new Main();
    static ATM[] ATM = {
            new ATM(2000,20,40000),
            new ATM(500,10,5000),
            new ATM(100,100,10000)
    };
    static List<ATM> Atm_attributes = Arrays.asList(ATM);
    static Details[] customerDetails = {
            new Details(101,"Suresh",2343,25234),
            new Details(102,"Ganesh",5432,34123),
            new Details(103,"Magesh",7854,26100),
            new Details(104,"Naresh",2345,80000),
            new Details(105,"Harish",1907,103400)
    };
    static List<Details> CUS = Arrays.asList(customerDetails);
    public int checkBalance(int accountNumber,int pin)
    {
        for(Details customer : CUS){
            if(customer.getAccNo() == accountNumber && customer.getPin()==pin){
                return customer.getAccountBalance();
            }
        }
        return 0;
    }

    public boolean withDrawPossible(int accountNumber,int pin,int amt){
        for(Details customer : CUS){
            if(customer.getAccNo() == accountNumber && customer.getPin()==pin && customer.getAccountBalance()>=amt){
                customer.setAccountBalance(customer.getAccountBalance()-amt);
                return true;
            }
        }
        return false;
    }

    public boolean transferPossible(int accountNumber,int pin,int amt,int otherAccNo){
        for(Details customer : CUS){
            if(customer.getAccNo() == accountNumber && customer.getPin()==pin&&customer.getAccountBalance()>=amt){
                customer.setAccountBalance(customer.getAccountBalance()-amt);
            }
        }
        for(Details customer : CUS){
            if(customer.getAccNo() == otherAccNo){
                customer.setAccountBalance(customer.getAccountBalance()+amt);
                return true;
            }
        }

        return false;
    }

    public static boolean correctPinOrNot(int acc)
    {
        for(Details customer : CUS){
            if(customer.getAccNo() == acc){
                return true;
            }
        }
        return false;
    }
    public static boolean correctPinOrNot(int acc,int pin)
    {
        for(Details customer : CUS){
            if(customer.getAccNo() == acc && customer.getPin()==pin){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int amount;
        Scanner scanner = new Scanner(System.in);
        boolean condition = true;
        int otherAccNo;
        int accNo,pin;
        System.out.println("Welcome to BCC Bank ATM");
        while (condition) {
            System.out.println("____Why do you use our ATM?____");
            System.out.println("1 . Check Balance");
            System.out.println("2 . Withdraw Amount");
            System.out.println("3 . Transfer Amount");
            System.out.println("4 . AMT Balance");
            System.out.println("5 . Exit");
            System.out.println("_______________________________");
            System.out.println("Enter Your Choice: ");
            int ch = scanner.nextInt();
            switch (ch) {
                case 1-> {
                    System.out.println("You have to chosen Balance Checking Option\n");
                    System.out.println("Please Enter Account Number: ");
                    accNo = scanner.nextInt();
                    System.out.println("Enter Your Four Digit Pin: ");
                    pin = scanner.nextInt();
                    if (correctPinOrNot(accNo, pin)) {
                        int balance = atmOperations.checkBalance(accNo, pin);
                        System.out.println("\nYour Current Balance is " + balance);
                    }
                }
                case 2->{
                    System.out.println("You have to chosen Withdrawing Option\n");
                    System.out.println("Please Enter Account Number: ");
                    accNo = scanner.nextInt();
                    System.out.println("Enter Your Four Digit Pin: ");
                    pin = scanner.nextInt();
                    System.out.println("Enter Amount of Money to withdraw: ");
                    amount=scanner.nextInt();
                    if(correctPinOrNot(accNo,pin)){
                        if(amount>=2000){
                            if(atmOperations.withDrawPossible(accNo,pin,amount)){
                                int withdrawAmount=amount;
                                if(Atm_attributes.stream().filter(atm -> atm.getValue()>=withdrawAmount).count()>1){
                                    Atm_attributes.stream().filter(atm -> atm.getDenomination()==2000).forEach(atm->atm.setValue(atm.getValue()-withdrawAmount));
                                    Atm_attributes.stream().filter(atm -> atm.getDenomination()==2000).forEach(atm->atm.setNumber(atm.getNumber()-(withdrawAmount/2000)));
                                    System.out.println("AMOUNT WITHDRAWN");
                                }
                            }
                        }
                        else if(amount>=500){
                            if(atmOperations.withDrawPossible(accNo,pin,amount)){
                                int withdrawAmount=amount;
                                if(Atm_attributes.stream().filter(atm -> atm.getValue()>=withdrawAmount).count()>1){
                                    Atm_attributes.stream().filter(atm -> atm.getDenomination()==500).forEach(atm->atm.setValue(atm.getValue()-withdrawAmount));
                                    Atm_attributes.stream().filter(atm -> atm.getDenomination()==500).forEach(atm->atm.setNumber(atm.getNumber()-(withdrawAmount/500)));
                                    System.out.println("AMOUNT WITHDRAWN");
                                }
                            }
                        }
                        else if(amount>=100){
                            if(atmOperations.withDrawPossible(accNo,pin,amount)){
                                int withdrawAmount=amount;
                                if(Atm_attributes.stream().filter(atm -> atm.getValue()>=withdrawAmount).count()>1){
                                    Atm_attributes.stream().filter(atm -> atm.getDenomination()==100).forEach(atm->atm.setValue(atm.getValue()-withdrawAmount));
                                    Atm_attributes.stream().filter(atm -> atm.getDenomination()==100).forEach(atm->atm.setNumber(atm.getNumber()-(withdrawAmount/100)));
                                    System.out.println("AMOUNT WITHDRAWN");
                                }
                            }
                        }
                    }
                }
                case 3->{
                    System.out.println("You have to chosen Transfer Option\n");
                    System.out.println("Please your Account Number: ");
                    accNo = scanner.nextInt();
                    System.out.println("Enter Your Four Digit Pin: ");
                    pin = scanner.nextInt();
                    System.out.println("Enter Amount of Money to transfer: ");
                    amount=scanner.nextInt();
                    if(correctPinOrNot(accNo,pin)){
                        if(amount<=10000){
                            System.out.println("Enter Account Number of Your Friend: ");
                            otherAccNo= scanner.nextInt();
                            if(correctPinOrNot(otherAccNo)){
                                if(amount>=2000){
                                    if(atmOperations.transferPossible(accNo,pin,amount,otherAccNo)){
                                        int transferAmount=amount;
                                        if(Atm_attributes.stream().filter(atm -> atm.getValue()>=transferAmount).count()>1){

                                            Atm_attributes.stream().filter(atm -> atm.getDenomination()==2000).forEach(atm->atm.setValue(atm.getValue()+transferAmount));
                                            Atm_attributes.stream().filter(atm -> atm.getDenomination()==2000).forEach(atm->atm.setNumber(atm.getNumber()+transferAmount/2000));
                                            System.out.println("AMUNT transferee");
                                        }
                                    }
                                }
                                else if(amount>=500){
                                    if(atmOperations.transferPossible(accNo,pin,amount,otherAccNo)){
                                        int transferAmount=amount;
                                        if(Atm_attributes.stream().filter(atm -> atm.getValue()>=transferAmount).count()>1){

                                            Atm_attributes.stream().filter(atm -> atm.getDenomination()==2000).forEach(atm->atm.setValue(atm.getValue()+transferAmount));
                                            Atm_attributes.stream().filter(atm -> atm.getDenomination()==2000).forEach(atm->atm.setNumber(atm.getNumber()+transferAmount/500));
                                            System.out.println("AMUNT transferee");
                                        }
                                    }
                                }
                                else if(amount>=100){
                                    if(atmOperations.transferPossible(accNo,pin,amount,otherAccNo)){
                                        int transferAmount=amount;
                                        if(Atm_attributes.stream().filter(atm -> atm.getValue()>=transferAmount).count()>1){

                                            Atm_attributes.stream().filter(atm -> atm.getDenomination()==2000).forEach(atm->atm.setValue(atm.getValue()+transferAmount));
                                            Atm_attributes.stream().filter(atm -> atm.getDenomination()==2000).forEach(atm->atm.setNumber(atm.getNumber()+transferAmount/100));
                                            System.out.println("AMUNT transferee");
                                        }
                                    }
                                }
                            }
                        }
                        else{
                            System.out.println("Amount Must be less than 10000");
                        }
                    }

                }
                case 4-> Atm_attributes.forEach(System.out::println);
                case 5-> condition=false;
                default -> System.out.println("Enter from 1,2,3,4,5");
            }
        }
    }
}
