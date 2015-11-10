import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TugOfWarPicnic {



	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Scanner scnr = null;



		try
		{
			TugOfWarPicnic war = new TugOfWarPicnic();

			/*
			 * Checking for argument, if provided then program considers it as
			 * input from a file and reads the file. Example: C:\test.txt (provide absolute path of file)
			 */

			if(args.length>0)
			{
				File text = new File(args[0]);
				scnr = new Scanner(text);

				int numTestCases = scnr.nextInt();
				/* Checking for no. of testcases to be executed */
				System.out.println("Outputs :");
				while(numTestCases>0)
				{
					int len=scnr.nextInt();
					int[] arr= new int[len];

					for(int i=0;i<len;i++)
					{
						arr[i]=scnr.nextInt();
					}
					war.tugOfWar(arr, len);

					numTestCases--;
				}

			}
			else
			{

				/* Reading the input from console */

				int noOfTestCases = scanner.nextInt();

				/* Checking for no. of testcases to be executed */
				while(noOfTestCases>0)
				{
					int len=scanner.nextInt();
					int[] arr= new int[len];

					for(int i=0;i<len;i++)
					{
						arr[i]=scanner.nextInt();
					}
					System.out.println("Output : ");
					war.tugOfWar(arr, len);

					noOfTestCases--;
				}
			}
		}
		catch(FileNotFoundException f)
		{
			System.out.println(f.getMessage());
			f.printStackTrace();

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();	
		}
		finally
		{
			if(scnr!=null)
				scnr.close();
			if(scanner!=null)
				scanner.close();

		}
	}



	public void tugOfWar(int[] weightArray, int length)
	{

		try
		{
			//long startTime = System.currentTimeMillis();
			int sumWeights=0,bestPartition=0;

			int max=0;

			for(int i=0;i<length;i++)
			{
				if(max<weightArray[i])
					max=weightArray[i];			
			}

			for(int i=0;i<length;i++)
			{
				sumWeights=sumWeights+weightArray[i];
			}

			boolean isPossible[][] = new boolean[(length/2)+2][max*length+max+1];
			isPossible[0][0]=true;

			/*We are trying for every person in the picnic*/
			for (int i=0;i<length;i++) { 

				for (int j=length/2;j>=0;j--) {

					for (int k=max*length;k>=0;k--) {

						if (isPossible[j][k]) 
							isPossible[j+1][k+weightArray[i]]=true;
					}
				}
			}



			for (int j=0;j<=max*length;j++) {
				if (!isPossible[length/2][j]) 
					continue;
				/*We check for elements such that difference of both partitions is minimum and update the bestPartition accordingly*/
				if (Math.abs(sumWeights-2*j) < Math.abs(sumWeights-2*bestPartition))
					bestPartition = j;
			}


			if (bestPartition > sumWeights-bestPartition) 
				bestPartition = sumWeights-bestPartition;

			int difference=sumWeights-bestPartition;

			//System.out.println("The sum of first subset is: "+bestPartition);
			//System.out.println("The sum of second subset is: "+difference);


			/* Printing the partitioned output */
			System.out.print(bestPartition);
			System.out.print(" ");
			System.out.println(difference);


			/*Measuring the time taken by the method tugOfWar*/


			/*long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
	    System.out.println("Time elapsed: "+elapsedTime);*/


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
