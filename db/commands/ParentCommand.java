
package commands;

import java.util.*;
import java.io.*;

abstract public class ParentCommand
{
    String pathname = "../db/db/";

    public final String[] reserved ={"create","table","insert","into","values","select","from","where","and","or","delete","drop","load","store"};

    public boolean not_a_reserved_name(String name)
    {
        for(int i=0;i<reserved.length;i++)
        {
            if(reserved[i].equals(name))
                return false;
        }
        return true;
    }

    public boolean extra_chars_present(String query)
    {
        for(int i=0;i<query.length();i++)
        {
            switch(query.charAt(i))
            {
                case '.' :
                case '/' : 
                case '\\' : 
                case '-' :
                case '+' :
                case '_' :
                case '&' :
                case '^' :
                case '%' :
                case '#' :
                case '@' :
                case '!' :
                case '~' :
                case '[' :
                case ']' :
                case '{' :
                case '}' :
                case ';' :
                return true;
                
                default : 
                continue;
            }
        }
        return false;
    }

    public boolean is_parenthesis_present(String query)
    {
        int s_index = query.indexOf('(');
        int e_index = query.indexOf(')');
        return s_index==e_index?false:true;
    }

    public boolean isTableExists(String table_name)
    {
        File f = new File(pathname+table_name+".txt");                
        if(f.exists()) {                                       // checking table already exists or not
            System.out.println("Table exist");
            return true;
        }
        else {
            System.out.println("Table does not exist");
        }
        return false;
    }

    public boolean check_paraenthesis(String query)
    {
        int s_index = query.indexOf('(');
        int e_index = query.indexOf(')');
        
        if(s_index==-1||e_index==-1)
        {
            System.out.println("Wrong Syntax");
            return false;
        }
        if(e_index+1!=query.length())                    // create table table_name(   ))      if multiple closing brackets are there                  
        {
            System.out.println("Wrong Syntax");
            return false;
        }
        if(query.indexOf('(',s_index+1)!=-1)
        {
            System.out.println("Wrong Syntax");
            return false;
        }
        return true;
    }

    public void print_columns(ArrayList<String> columns)
    {
        for (int i=0; i<columns.size();i++) 
        {
            System.out.print(columns.get(i)+"----");
        }
    }

    public boolean string_contains_space(String str)
    {
        return str.indexOf(' ')==-1?false:true;
    }

    public String get_Table_name_ci(String query,int table_name_index,char delmiter)
    {
        String table_name = "";
        int s_index = query.indexOf(delmiter,table_name_index);
        if(query.charAt(s_index-1)==' ')
        {
            table_name = query.substring(table_name_index,s_index-1);
        }
        else
        {
            table_name = query.substring(table_name_index,s_index);
        }
        return table_name;
    }

    public boolean extract_columns(String query,ArrayList<String> columns)
    {
        int s_index = query.indexOf('(');
        int e_index = query.indexOf(')');
        int s = s_index,e;
        String colname = "";
        while(true)
        {
            if(query.charAt(s+1)==' ')
            {
                s++;
            }
            e = query.indexOf(',',s+1);
            if(s+1==e || query.length()-2==s)
            {
                System.out.println("Provide a valid column name");
                return false;
            }
            if(e==-1)
            {
                e = query.charAt(e_index-1)==' '?e_index-1:e_index;
                colname = query.substring(s+1,e);
                if(not_a_reserved_name(colname))
                {
                    columns.add(colname);
                }
                else
                {
                    System.out.println("Column name cannot be " + colname);
                    return false;
                }
                // columns.add(query.substring(s+1,e));
                // System.out.println(query.substring(s+1,e));
                return true;
            }
            else if(query.charAt(e-1)==' ')
            {
                colname = query.substring(s+1,e-1);
                if(not_a_reserved_name(colname))
                {
                    columns.add(colname);
                }
                else
                {
                    System.out.println("Column name cannot be " + colname);
                    return false;
                }
                // columns.add(query.substring(s+1,e-1));
                // System.out.println(query.substring(s+1,e-1));
            }
            else
            {
                colname = query.substring(s+1,e);
                if(not_a_reserved_name(colname))
                {
                    columns.add(colname);
                }
                else
                {
                    System.out.println("Column name cannot be " + colname);
                    return false;
                }
                // columns.add(query.substring(s+1,e));
                // System.out.println(query.substring(s+1,e));
            }
            s = e;
        }
    }

    public abstract void Sayhello();

    // public static void main(String []args)
    // {
    //     Scanner sc = new Scanner(System.in);
    //     ParentCommand obj = new ParentCommand();
    //     String query = sc.next();
    //     obj.isTableExists(query);
    // }
        
}
