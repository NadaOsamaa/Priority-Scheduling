public class Priority 
{

    static void findWaitingTime(Process[] proc, int n,int cs)
    {
        int ct[] = new int[n]; // ct means complete time
        int tat[] = new int[n];// tat means turn around time
        int wt[] = new int[n];  // wt means waiting time
        int flag[] = new int[n];  //  flag to checks process is completed or not
        int rt[]= new int[n];   // it stores brust time
        int i, st=0, tot=0,temp=0,biggest_diff=0,index=0; //st system time,tot total proccesses , temp to hold the old process to be in cpu
        float avgwt=0, avgta=0;
        //to store burst time into rt[]
        for (i=0;i<n;i++)
        {
            rt[i]= proc[i].bt;
            flag[i]= 0;
        }
    
        while(true)
        {
            int min_pr=999,min_bt,c=n;
            if (tot==n)
            break;
            //finding the highest(leas in number) priority
            for ( i=0;i<n;i++)
            {
            if ((proc[i].art<=st) && (flag[i]==0) && (proc[i].p<min_pr))
                {
                    min_pr=proc[i].p;
                    min_bt=proc[i].bt;
                    c=i;
                }
            }
            if(st==0||c==n) //if it isn't the first iretation(time zero)and c is a process in the queue
                ;
            else
            {
                if(temp!=c&&flag[temp]==0)        //if the old process is't the same as the new one and it's noy finished yet add
                {                                 //context switching
                    System.out.println("Context switching: "+"p"+proc[temp].pn +"  at time: "+st);
                    st+=cs;
                }
            }
            //make the old process same as new one
            temp=c;
            System.out.println("processes excuting order: "+"p"+proc[c].pn +"  at time: "+st);
            //Starvation problem solution
            //if the system time  exceeds 15 then search for the proceess which have the biggest difference between arrival time and systme time
            // (the process that waited the biggest time)and decrease it's priority by 1
            if(st>=15)
            {
            for ( i=0;i<n;i++)
            {
                if (st-proc[i].art>=biggest_diff)
                    {
                        biggest_diff=st-proc[i].art;
                        index=i;
                    }
            }
            if(proc[index].p>1)
                 proc[index].p--;
            }
            // If c==n means c value can not be updated because no process arrival time< system time so we increase the system time 
            if (c==n)
                st++;
            else
            {
                proc[c].bt--;
                st++;
                if (proc[c].bt==0)
                {
                    System.out.println("processes excuting order: "+"p"+proc[c].pn +"  at time: "+st);
                    st+=cs;
                    System.out.println("Context switching: "+"p"+proc[c].pn +"  at time: "+st);
                    ct[c]= st;
                    flag[c]=1;
                    tot++;
                }
            }
        }
        // calculating all times for all proccesses
        for(i=0;i<n;i++)
        {
            tat[i] = ct[i] - proc[i].art;
            wt[i] = tat[i] - rt[i];
            avgwt+= wt[i];
            avgta+= tat[i];
        }
    
        System.out.println("pid  arrival  burst  Priority  complete turn waiting");
        for(i=0;i<n;i++)
        {
            System.out.println(proc[i].pn +"\t"+ proc[i].art+"\t"+ rt[i] +"\t"+proc[i].p+"\t"+ ct[i] +"\t"+ tat[i] +"\t"+ wt[i]);
        }
    
        System.out.println("\naverage tat is "+ (float)(avgta/n));
        System.out.println("average wt is "+ (float)(avgwt/n));
    }
}



