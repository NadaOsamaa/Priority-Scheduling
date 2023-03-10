 import java.util.Scanner;
 public class Main {
     public static void main(String[] args)
     {
         try (Scanner myObj = new Scanner(System.in)) {
            //getting number of process from the user:
             System.out.print("Enter Number of process: ");
             int num = myObj.nextInt();
 
             Process[] proc = new Process[num];
 
             for (int i=0; i<num; i++)
             {
                 System.out.print("Enter Name For Process " + (i+1) + ": ");
                 int pn = myObj.nextInt();
 
                 System.out.print("Enter Process's Arrival Time: ");
                 int art = myObj.nextInt();
 
                 System.out.print("Enter Process's Burst Time: ");
                 int bt = myObj.nextInt();
 
                 System.out.print("Enter Process's Priority: ");
                 int p = myObj.nextInt();
 
                 proc[i] = new Process(pn, art, bt, p);
             }
 
             //getting Context switching from the user:
             System.out.print("Enter Context switching: ");
             int cs = myObj.nextInt();
 
 
             Priority.findWaitingTime(proc, proc.length,cs);  // Shortest Job First
        }
     }
 }