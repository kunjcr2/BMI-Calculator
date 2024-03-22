import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.DecimalFormat;
package BMI_Calculator;
package BMI_Calculator;

public class BMI_Master_KunjShah {
    static String str;

    static void wlcmMsg() {
        System.out.println("My CSC 215 BMI Calculator Projects:");
        System.out.println("   1. BMI, English");
        System.out.println("   2. BMI, Metric");
        System.out.println();
        System.out.println("[ USER MANUAL ] Enter an exclamation mark ! to end");
        System.out.print("Please enter the version you want to try: ");
    }
    static void exclamation() {
        Scanner sc = new Scanner(System.in);
        str = sc.nextLine();
        if (str.equals("!")) {
            System.exit(3);
        }
    }
    static String getInpt(String okay) {
        System.out.println();
        str = str.toLowerCase();
        str = str.trim();
        str = str.replace(" ", "");
        str = str + " ";
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                continue;
            }
            okay = okay + str.charAt(i);
        }
        return okay;
    }
    static void evaluate(String okay) {
        if (okay.equals("english")) {
            BMI_CSC215_English_KunjShah.BMI_CSC215_English_KunjShah();
        } else if (okay.equals("metric")) {
            BMI_CSC215_Metric_KunjShah.BMI_CSC215_Metric_KunjShah1();
        }
    }
    static void menu(){
        do {
            wlcmMsg();
            exclamation();
            getInpt("");
            evaluate(getInpt(""));
            str = "";
        }while(true);
    }
    public static void main(String[] args){
        menu();
    }
}

class BMI_CSC215_English_KunjShah{
    static String name, status, fin;
    static int feet, inch;
    static double weight, weightLow,weightHigh, x;
    static void welcomeMsg(){
        System.out.println("-----------------------------------------------------------");
        System.out.println("--Welcome to: ");
        System.out.println("--           BODY MASS INDEX (BMI) Computation, CSC 215, English Verison");
        System.out.println("--                                                                  by KunjShah");
        System.out.println("-----------------------------------------------------------");
    }
    static void getInput(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        name = sc.nextLine();
        System.out.print("Please enter height in feet and inches for " +name+ ": ");
        feet = sc.nextInt();
        inch = sc.nextInt();
        System.out.print("Please enter you weight (in pounds) for " +name+ ": ");
        weight = sc.nextDouble();
    }
    static double calculate(double w,int f, int i){
        int inches = 12*feet + inch;
        double n = w / (inches*inches) *703;
        return n;
    }
    static String catResult(double bmi){
        if(bmi<18.5){
            status = "Underweight";
            bmi = Math.floor(bmi*100)/100;
        }else if(bmi<24.9 && bmi>=18.5){
            status = "Healthy Weight";
            bmi = Math.floor(bmi*1000)/1000;
        }else if(bmi<29.9 && bmi>=24.9){
            status = "Overweight";
            bmi = Math.floor(bmi*10000)/10000;
        }else{
            status = "Obesity";
            bmi = Math.floor(bmi*100000)/100000;
        }

        return status;
    }
    static String bmiCalc(double bmi){
        if(bmi<18.5){
            status = "Underweight";
            DecimalFormat df = new DecimalFormat("00.00");
            x = (double)(Math.round(bmi*100.)/100.);
            fin = df.format(x);
        }else if(bmi<24.9 && bmi>=18.5){
            status = "Healthy Weight";
            DecimalFormat df = new DecimalFormat("00.000");
            x = (double)(Math.round(bmi*1000.)/1000.);
            fin = df.format(x);
        }else if(bmi<29.9 && bmi>=24.9){
            status = "Overweight";
            DecimalFormat df = new DecimalFormat("00.0000");
            x = (double)(Math.round(bmi*10000.)/10000.);
            fin = df.format(x);
        }else{
            status = "Obesity";
            DecimalFormat df = new DecimalFormat("00.00000");
            x = (double)(Math.round(bmi*100000.)/100000.);
            fin = df.format(x);
        }
        return fin;
    }
    static void displayResult() {
        LocalDate today = LocalDate.now();
        String fullDate = today.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
        System.out.println("");
        System.out.println("--SUMMARY REPORT for "+name.toUpperCase());
        System.out.println("--Date and Time:            "+ fullDate +" "+ LocalTime.now());
        System.out.printf("--BMI:                      %.5f",calculate(weight,feet,inch));
        System.out.printf(" (or %.1f if rounded)",calculate(weight,feet,inch));
        System.out.println();
        System.out.println("--Weight Status:            "+catResult(calculate(weight,feet,inch)));

    }
    static void weightRange(){
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter a LOW weight in pounds for "+name+": ");
        weightLow = sc.nextDouble();
        System.out.print("Please enter a HIGH weight in pounds for "+name+": ");
        weightHigh = sc.nextDouble();
    }
    static void displayTable(){
        String RESET = "\u001B[0m";
        String YELLOW = "\u001B[43m";
        String BLACK = "\u001B[30m";
        String h = "(HIGH)";
        String l = "(LOW)";
        String t = "(this)";
        Double w = Math.floor(weight*100)/100;
        System.out.println("-------------------------------------------------------");
        System.out.println("|   WEIGHT    |  BMI        |  WEIGHT STATUS          |");
        System.out.println(" ----------------------------------------------------- ");
        System.out.printf("| %6.2f      |  %-8s  |  %-14s    %s |\n",weightLow, bmiCalc(calculate(weightLow,feet,inch)), catResult(calculate(weightLow,feet,inch)), YELLOW + BLACK+l+RESET + RESET); //LOW
        for(double u = weightLow+5.5; u<=weightHigh; u=u+5.5){
            Double un = Math.floor(u*100)/100;
            if(u+5.5>weight && u<weight || u==weight){
                System.out.printf("| %6.2f      |  %-8s  |  %-14s %5s   |\n",w, bmiCalc(calculate(weight,feet,inch)), catResult(calculate(weight,feet,inch)),t);
            }else{
                System.out.printf("| %6.2f      |  %-8s  |  %-14s          |\n",u,bmiCalc(calculate(u,feet,inch)),catResult(calculate(u,feet,inch)));
            }
        }
        //System.out.println(bmiCalc(calculate(weight,meter)));
        System.out.printf("| %6.2f      |  %-8s  |  %-14s   %s |\n",weightHigh, bmiCalc(calculate(weightHigh,feet,inch)), catResult(calculate(weightHigh,feet,inch)),YELLOW + BLACK+h+RESET + RESET); //HIGH
        System.out.println("------------------------------------------------------- ");
    }
    static void endingMsg(){
        System.out.println();
        System.out.println("The SFSU Mashouf Wellness Center is at 755 Font Blvd.");
        System.out.println();
        System.out.println("--------------------------------------------------------------------");
        System.out.println("--Thank you for using my program,"+name+"!");
        System.out.println("--Poopaye!!!");
        System.out.println("--------------------------------------------------------------------");
    }
    public static void BMI_CSC215_English_KunjShah(){
        welcomeMsg();
        getInput();
        displayResult();
        weightRange();
        displayTable();
        endingMsg();
    }
}

class BMI_CSC215_Metric_KunjShah{
    static String name, status, fin;
    static double cm;
    static double meter;
    static double weight, weightLow,weightHigh, x;
    static void welcomeMsg(){
        System.out.println("-----------------------------------------------------------");
        System.out.println("--Welcome to: ");
        System.out.println("--           BODY MASS INDEX (BMI) Computation, CSC 215, Metric Verison");
        System.out.println("--                                                                  by KunjShah");
        System.out.println("-----------------------------------------------------------");
    }
    static void getInput(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        name = sc.nextLine();
        System.out.print("Please enter height in centimeters for " +name+ ": ");
        cm = sc.nextDouble();
        meter = cm/100.;
        System.out.print("Please enter you weight (in kilos) for " +name+ ": ");
        weight = sc.nextDouble();
    }
    static double calculate(double w,double m){
        double n = w / (meter*meter);
        return n;
    }
    static String catResult(double bmi){
        if(bmi<18.5){
            status = "Underweight";
            bmi = Math.floor(bmi*100)/100;
        }else if(bmi<24.9 && bmi>=18.5){
            status = "Healthy Weight";
            bmi = Math.floor(bmi*1000)/1000;
        }else if(bmi<29.9 && bmi>=24.9){
            status = "Overweight";
            bmi = Math.floor(bmi*10000)/10000;
        }else{
            status = "Obesity";
            bmi = Math.floor(bmi*100000)/100000;
        }

        return status;
    }
    static String bmiCalc(double bmi){
        if(bmi<18.5){
            status = "Underweight";
            DecimalFormat df = new DecimalFormat("00.00");
            x = (double)(Math.round(bmi*100.)/100.);
            fin = df.format(x);
        }else if(bmi<24.9 && bmi>=18.5){
            status = "Healthy Weight";
            DecimalFormat df = new DecimalFormat("00.000");
            x = (double)(Math.round(bmi*1000.)/1000.);
            fin = df.format(x);
        }else if(bmi<29.9 && bmi>=24.9){
            status = "Overweight";
            DecimalFormat df = new DecimalFormat("00.0000");
            x = (double)(Math.round(bmi*10000.)/10000.);
            fin = df.format(x);
        }else{
            status = "Obesity";
            DecimalFormat df = new DecimalFormat("00.00000");
            x = (double)(Math.round(bmi*100000.)/100000.);
            fin = df.format(x);
        }
        return fin;
    }
    static void displayResult() {
        LocalDate today = LocalDate.now();
        String fullDate = today.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
        System.out.println("");
        System.out.println("--SUMMARY REPORT for "+name.toUpperCase());
        System.out.println("--Date and Time:            "+ fullDate +" "+ LocalTime.now());
        System.out.printf("--BMI:                      %.5f",calculate(weight,meter));
        System.out.printf(" (or %.1f if rounded)",calculate(weight,meter));
        System.out.println();
        System.out.println("--Weight Status:            "+catResult(calculate(weight,meter)));

    }
    static void weightRange(){
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter a LOW weight in kilogram for "+name+": ");
        weightLow = sc.nextDouble();
        System.out.print("Please enter a HIGH weight in kilogram for "+name+": ");
        weightHigh = sc.nextDouble();
    }
    static void displayTable(){
        String RESET = "\u001B[0m";
        String YELLOW = "\u001B[43m";
        String BLACK = "\u001B[30m";
        String h = "(HIGH)";
        String l = "(LOW)";
        String t = "(this)";
        Double w = Math.floor(weight*100)/100;
        System.out.println("-------------------------------------------------------");
        System.out.println("|   WEIGHT    |  BMI        |  WEIGHT STATUS          |");
        System.out.println(" ----------------------------------------------------- ");
        System.out.printf("| %6.2f      |  %-8s  |  %-14s    %s |\n",weightLow, bmiCalc(calculate(weightLow,meter)), catResult(calculate(weightLow,meter)), YELLOW + BLACK+l+RESET + RESET); //LOW
        for(double u = weightLow+2.5; u<=weightHigh; u=u+2.5){
            Double un = Math.floor(u*100)/100;
            if(u+2.5>weight && u<weight || u==weight){
                System.out.printf("| %6.2f      |  %-8s  |  %-14s %5s   |\n",w, bmiCalc(calculate(weight,meter)), catResult(calculate(weight,meter)),t);
            }else{
                System.out.printf("| %6.2f      |  %-8s  |  %-14s          |\n",u,bmiCalc(calculate(u,meter)),catResult(calculate(u,meter)));
            }
        }
        System.out.printf("| %6.2f      |  %-8s  |  %-14s   %s |\n",weightHigh, bmiCalc(calculate(weightHigh,meter)), catResult(calculate(weightHigh,meter)),YELLOW + BLACK+h+RESET + RESET); //HIGH
        System.out.println("------------------------------------------------------- ");
    }
    static void endingMsg(){
        System.out.println();
        System.out.println("The SFSU Mashouf Wellness Center is at 755 Font Blvd.");
        System.out.println();
        System.out.println("--------------------------------------------------------------------");
        System.out.println("--Thank you for using my program,"+name+"!");
        System.out.println("--Poopaye!!!");
        System.out.println("--------------------------------------------------------------------");
    }
    public static void BMI_CSC215_Metric_KunjShah1(){
        welcomeMsg();
        getInput();
        displayResult();
        weightRange();
        displayTable();
        endingMsg();
    }
}
