package java_programs;

public class DisplayOddNumbers {

    public void printOdd () {


        int i;
        for (i = 1; i <= 100; i++) ;
        {
            if (i % 2 != 0) {
                System.out.println(i);
            }

        }
    }

        public static void main(String[] args)     {
            DisplayOddNumbers  DisplayOddNumbers=new DisplayOddNumbers();
            System.out.println("odd numbers from 1 to 100 is :");
            DisplayOddNumbers.printOdd();





        }



    }

