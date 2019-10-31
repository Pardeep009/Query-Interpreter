    
    package commands;

    import java.util.*;
    import java.io.*;

    public class Create extends ParentCommand 
    {

        boolean check_validity_of_create_command(String query,ArrayList<String> columns)
        {
            String[] words = query.split(" ");
            String table_name = "";
            int table_name_index = 13;
            if(extra_chars_present(query))
            {
                System.out.println("Wrong Syntax");
                return false;
            }
            if(words.length<3)
            {
                System.out.println("Less Number Of Arguments are passed");
                return false;
            }
            if(!words[1].equals("table"))                     //  create table(yha pe spelling mistake) ...........
            {
                System.out.println(words[1] + " is found instead of table");
                return false;
            }
            if(check_paraenthesis(query))
            {
                table_name = get_Table_name_ci(query,table_name_index,'(');
                if(string_contains_space(table_name))
                {
                    System.out.println("Table name cannot have space");
                    return false;
                }
                if(not_a_reserved_name(table_name))
                {
                    columns.add(table_name);
                }
                else
                {
                    System.out.println("Column name cannot be " + table_name);
                    return false;
                }
                if(isTableExists(table_name))
                    return false;
                if(extract_columns(query,columns))
                {
                    columns.add(table_name);
                    for(int i=0;i<columns.size();i++)
                    {
                        if(string_contains_space(columns.get(i)))
                        {
                            System.out.println("Column name cannot have space");
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
            return false;
        }

        public void create_procedure(String query)
        {
            ArrayList<String> columns = new ArrayList<String>();
            if(check_validity_of_create_command(query,columns))
            {
                
                int size = columns.size()-1;
                if(size<0)
                    size++;
                String filename = pathname+columns.get(size)+".txt";
                try
                {
                    FileWriter fw = new FileWriter(filename);
                    BufferedWriter writer = new BufferedWriter(fw);  
                    for (int i=0; i<columns.size()-1;i++) 
                    {
                        writer.write(columns.get(i)+",");
                        writer.flush();
                    }
                    writer.newLine();
                    writer.flush();
                    writer.write("0");
                    writer.flush();
                    writer.newLine();
                    writer.flush();
                    writer.close();
                    System.out.println("success...");
                                
                        
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }    
            }
        }
        
        public void Sayhello()
        {
            System.out.println("hello");
        }

        // public static void main(String []args)
        // {
        //     Create obj = new Create();
        //     obj.Sayhello();   
        // }

    }