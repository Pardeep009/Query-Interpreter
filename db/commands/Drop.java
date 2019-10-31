package commands;

import java.util.*;
import java.io.*;

public class Drop extends ParentCommand 
{
    public void drop_procedure(String query)
    {
        if(is_parenthesis_present(query))
        {
            System.out.println("Wrong Syntax");
            return;
        }
        String words[] = query.split(" ");
        if(words.length!=3||(!words[1].equals("table")))
        {
            System.out.println("Wrong Syntax");
            return;
        }
        if(isTableExists(words[2]))
        {
            File file = new File(pathname+words[2]+".txt");           
            if(file.delete()) 
            { 
                System.out.println("File deleted successfully"); 
            } 
            else
            { 
                System.out.println("Failed to delete the file"); 
            } 
        }
        else
        {
            System.out.println("Table Does Not Exist");
        }
    }

    public void Sayhello()
    {
        System.out.println("hello");
    }

}