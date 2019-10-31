
	import java.util.*;
	import java.io.*;

	import commands.*;
	
	public class DB 
	{
		
		// void printFile(String filename)            // print line by line
		// {
			// FileReader fr=new FileReader(filename);
			// try (BufferedReader br = new BufferedReader(fr)) {
				// String line;
				// while ((line = br.readLine()) != null) {
					// String word[] = line.split(",");
					// for(int i=0;i<word.length;i++)
					// {
						// System.out.println(word[i]);
					// }
					// break;
				   // System.out.println(line);
				// }
			// }
			// catch(Exception e)
			// {
				// System.out.println(e);
			// }
			// FileReader fr=new FileReader(filename);    
					// int i;    
					// while((i=fr.read())!=-1)    
					// System.out.print((char)i);    
					// fr.close();
					// try (BufferedReader br = new BufferedReader(fr)) {
						// String line;
						// while ((line = br.readLine()) != null) {
							// String word[] = line.split(",");
							// for(int i=0;i<word.length;i++)
							// {
								// System.out.println(word[i]);
							// }
							// break;
						   // System.out.println(line);
						// }
					// }
					// catch(Exception e)
					// {
						// System.out.println(e);
					// }
					// fr.close();
					// String s="gfhjkl ghjkl;";    
					// byte b[]=s.getBytes();        //converting string into byte array    
					// fout.write(b); 
					// s = "123456";
					// b = s.getBytes();
					// fout.write(b);
					// fout.close();
		// }
		
		void exit_procedure(String s)
		{
			
		}
		
		void load_procedure(String s)
		{
			
		}
		
		void print_procedure(String s)
		{
			
		}
		
		void store_procedure(String s)
		{
			
		}

		void read_Manual()
		{
			try {
				String filename = "manual.txt";
				FileReader fr=new FileReader(filename);    
				BufferedReader br=new BufferedReader(fr);
				String line  = br.readLine();
				while(line != null)
				{
					System.out.println(line);
					line = br.readLine();
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
		}
		
		public static void main(String[] args)
		{
			System.out.println("Welcome To Bhatt DataBase\n");
			DB db = new DB();
			db.read_Manual();
			Create create = new Create();
			Insert insert = new Insert();
			Select select = new Select();
			Drop drop = new Drop();
			Scanner sc = new Scanner(System.in);
			while(true)
			{
				String query = new String();
				query = sc.nextLine();
				query = query.trim().replaceAll("\\s{1,}"," ");
				query = query.toLowerCase();
				System.out.println("-"+query+"-");
				String operation;
				if(query.contains(" "))
				{
					operation = query.substring(0,query.indexOf(' '));
				}
				else
				{
					System.out.println("Not a Recogonized Command");
					continue;
				}
				switch(operation)
				{
					case "create" :
					create.create_procedure(query);
					break;
					
					case "insert" :
					insert.insert_procedure(query);
					break;
					
					case "select" :
					select.select_procedure(query);
					break;
					
					case "exit" :
					System.out.println(operation);
					break;
					
					case "load" :
					System.out.println(operation);
					break;
					
					case "print" :
					System.out.println(operation);
					break;
					
					case "store" :
					System.out.println(operation);
					break;
					
					case "drop" :
					drop.drop_procedure(query);
					break;
					
					default :
					System.out.println("Not a Recogonized Command");
					break;
					
				}
			}
		}
	}