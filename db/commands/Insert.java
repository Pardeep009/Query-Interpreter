package commands;

import java.util.*;
import java.io.*;

public class Insert extends ParentCommand 
{

    boolean check_validity_of_insert_command(String query,ArrayList<String> columns)
    {
        String[] words = query.split(" ");
        String table_name = "";
        int table_name_index = 12;
        if(words.length<4)
        {
            System.out.println("Less Number Of Arguments are passed");
            return false;
        }
        if(!words[1].equals("into"))
        {
            System.out.println(words[1] + " is found instead of into");
            return false;
        }
        if(extra_chars_present(query))
        {
            System.out.println("Wrong Syntax");
            return false;
        }
        int ind = query.indexOf('(',table_name_index+1);
        if(query.charAt(ind-1)==' ')
        ind--;
        String values = query.substring(query.indexOf(' ',table_name_index+1)+1,ind);
        if(!values.equals("values"))
        {
            System.out.println(values + " is found instead of values");
            return false;
        }
        else
        // {	insert into mm values(
    // int index = query.indexOf
            // if(query.substring(query.indexOf(' ',table_name_index+1)+1,query.indexOf()))
            table_name = get_Table_name_ci(query,table_name_index,' ');
            if(check_paraenthesis(query))
            {
                System.out.println(table_name);
                if(isTableExists(table_name))
                {
                    if(extract_columns(query,columns))
                    {
                        columns.add(table_name);
                        return true;
                    }
                    return false;
                }
                else
                {
                    System.out.println("table does not exists");
                    return false;
                }
            }
            return false;
    }

    long check_file_columns(int size,String line)
    {			
        long count = line.chars().filter(c -> c == ',').count();
        return count - (long)(size);
    }

    public void insert_procedure(String query)
    {
        ArrayList<String> columns = new ArrayList<String>();
        if(check_validity_of_insert_command(query,columns))
        {
            int size = columns.size()-1;
            if(size<0)
                size++;
            String filename = pathname+columns.get(size)+".txt";
            try {
                RandomAccessFile file = new RandomAccessFile(filename,"rw");
                String line = file.readLine();
                long result = check_file_columns(columns.size()-1,line);
                if(result==0)
                {
                    String count = file.readLine();
                    while(line!=null) {
                        line = file.readLine();
                    }
                    line = "\n";
                    file.write(line.getBytes());
                    line = "";
                    for(int i=0;i<columns.size()-1;i++)
                    {
                        line = line + columns.get(i) + ",";
                    }
                    file.write(line.getBytes());
                    file.seek(0);
                    file.readLine();
                    int c = Integer.parseInt(count)+1;
                    line = Integer.toString(c);
                    file.write(line.getBytes());
                    System.out.println("success...");
                }
                else if(result>0) {
                    System.out.println("Error : Enteries are missing");
                }
                else {
                    System.out.println("Error : Extra Fields are provided");
                }
                file.close();
            }
            catch(Exception e)
            {
                
            }
        }
    }
    
    public void Sayhello()
    {
        System.out.println("hello");
    }

    // public static void main(String []args)
    // {
    //     Insert obj = new Insert();
        
    // }

}