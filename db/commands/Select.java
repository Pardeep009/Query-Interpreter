    
    package commands;

    import java.util.*;
    import java.io.*;

    public class Select extends ParentCommand 
    {

        public void select_procedure(String query)
        {
            String[] words = query.split(" ");
            ArrayList<String> columns = new ArrayList<String>();
            // HashMap<String,String> map = new HashMap<>();
            // System.out.println(map); 
            if(check_validity_of_select_command(query,columns))
            {
                // String[] words = query.split(" ");
                // for (String var : words) {
                // 	System.out.println(var);
                // }
            }
        }

        void print_table3(String table_name,String[] required_columns,HashMap<String,String> where)
            {
                try {
                    HashMap<String,Integer> map = new HashMap<String,Integer>();
                    String filename = pathname+table_name+".txt";
                    FileReader fr=new FileReader(filename);    
                    BufferedReader br=new BufferedReader(fr);
                    String line = br.readLine();
                    String present_columns[] = line.split(",");
                    // System.out.println("present_columns length "+present_columns.length);
                    if(present_columns.length<where.size())
                    {
                        System.out.println("Error:Condition on more no of columns than present columns");
                        return;
                    }
                    if(required_columns.length>present_columns.length)
                    {
                        System.out.println("Too many columns are asked");
                        return;
                    }
                    for(int i=0;i<present_columns.length;i++)
                    {
                        map.put(present_columns[i],i);
                    }
                    for (Map.Entry mapElement : where.entrySet()) { 
                        String key = (String)mapElement.getKey(); 
            
                        // mapElement.getValue(); 
                        if(!map.containsKey(key))
                        {
                            System.out.println(key + " column is not present in table ");
                            return; 	
                        }
                    }
                    int a[] = new int[required_columns.length];
                    label: for(int i=0;i<required_columns.length;i++)
                    {
                        for(int j=0;j<present_columns.length;j++)
                        {
                            if(present_columns[j].equals(required_columns[i]))
                            {
                                a[i] = j;
                                continue label;
                            }
                        }
                        System.out.println("Column names do not match");
                        return;
                    }
                    line = br.readLine();
                    if(line.equals("0"))
                    {
                        System.out.println("Table is Empty");
                        return;
                    }
                    for(int i=0;i<required_columns.length;i++)
                    {
                        System.out.print(required_columns[i]+" ");
                    }
                    System.out.print("\n");
                    br.readLine();
                    line = br.readLine();
                    int f=0;
                    while(line!=null)
                    {
                        String []line1 = line.split(",");
                        if(where.size()==0)
                        {
                            for(int i=0;i<required_columns.length;i++)
                            {
                                f=1;
                                System.out.print(line1[a[i]]+" ");
                            }
                        }
                        else
                        {
                            int c=0;
                            for (Map.Entry mapElement : where.entrySet()) { 
                                int key = map.get(mapElement.getKey()); 
                                if(!line1[key].equals(mapElement.getValue()))
                                {
                                    break;
                                }
                                c++;
                            }
                            if(c==where.size())
                            {
                                for(int i=0;i<required_columns.length;i++)
                                {
                                    f=1;
                                    System.out.print(line1[a[i]]+" ");
                                }
                            }
                        }
                        System.out.print("\n");
                        line = br.readLine();
                    }
                    if(f==0)
                    {
                        System.out.println("No such record Exists");
                    }
                }
                catch(Exception e)
                {
                    
                }
            }

            void print_table2(String table_name,HashMap<String,String> where)
            {
                try {
                    HashMap<String,Integer> map = new HashMap<String,Integer>();
                    String filename = pathname+table_name+".txt";
                    FileReader fr=new FileReader(filename);    
                    BufferedReader br=new BufferedReader(fr);
                    String line = br.readLine();
                    line = line.replace(',',' ');
                    String []present_columns = line.split(" ");
                    if(present_columns.length<where.size())
                    {
                        System.out.println("Error:Condition on more no of columns than present columns");
                        return;
                    }
                    for(int i=0;i<present_columns.length;i++)
                    {
                        map.put(present_columns[i],i);
                    }
                    for (Map.Entry mapElement : where.entrySet()) { 
                        String key = (String)mapElement.getKey(); 
            
                        // mapElement.getValue(); 
                        if(!map.containsKey(key))
                        {
                            System.out.println(key + " column is not present in table ");
                            return; 	
                        }
                    } 
                    System.out.println(line);
                    line = br.readLine();
                    if(line.equals("0"))
                    {
                        System.out.println("Table is Empty");
                        return;
                    }
                    br.readLine();
                    line = br.readLine();
                    int f=0;
                    while(line!=null)
                    {
                        line = line.replace(',',' ');
                        String []present_values = line.split(" ");
                        int c=0;
                        for (Map.Entry mapElement : where.entrySet()) { 
                            int key = map.get(mapElement.getKey()); 
                            if(!present_values[key].equals(mapElement.getValue()))
                            {
                                break;
                            }
                            c++;
                        }
                        if(c==where.size())
                        {
                            f=1;
                            System.out.println(line);
                        }
                        line = br.readLine();
                    }
                    if(f==0)
                    {
                        System.out.println("No such record Exists");
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Error Occurred while reading database");
                }
            }

            void print_table1(String table_name)
            {
                try {
                    String filename = pathname+table_name+".txt";
                    FileReader fr=new FileReader(filename);    
                    BufferedReader br=new BufferedReader(fr);
                    String line = br.readLine();
                    line = line.replace(',',' ');
                    System.out.println(line);
                    line = br.readLine();
                    if(line.equals("0"))
                    {
                        System.out.println("Table is Empty");
                        return;
                    }
                    br.readLine();
                    line = br.readLine();
                    while(line!=null)
                    {
                        line = line.replace(',',' ');
                        System.out.println(line);
                        line = br.readLine();
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Error Occurred while reading database");
                }
            }

        boolean getColumns(String query,int start_index,HashMap<String,String> map)
        {
            start_index = query.indexOf(' ',start_index);
            while(true)
            {
                if(start_index==-1)
                {
                    System.out.println("No conditions found after where");
                    return false;
                }
                start_index++;
                String colname="",value="";
                int end_index = query.indexOf('=',start_index);
                if(end_index==-1 || start_index==end_index || end_index==query.length()-1)
                {
                    System.out.println("column name and value for that column must be seprated by = ");
                    return false;
                }
                if(query.charAt(end_index-1)==' ')
                {
                    colname = query.substring(start_index,end_index-1);
                }
                else
                {
                    colname = query.substring(start_index,end_index);
                }
                start_index = end_index;
                start_index++;
                if(query.charAt(start_index)==' ')
                {
                    start_index++;
                }
                end_index = query.indexOf('\'',start_index+1);
                if(end_index==-1)
                {
                    System.out.println("Value for a column must be present between \'\'");
                    return false;
                }
                if(start_index+1==end_index||(start_index+2==end_index&&query.charAt(start_index+1)==' '))
                {
                    System.out.println("Column value cannot be empty");
                    return false;
                }
                value = query.substring(start_index+1,end_index);
                System.out.println(colname + "===" + value);
                map.put(colname,value);
                if(end_index!=query.length()-1)
                {
                    start_index = query.indexOf(' ',end_index+2);
                    if(start_index == -1)
                    {
                        System.out.println("Conditiions must be seprated by and|or operators");
                        return false;
                    }
                    String operation = query.substring(end_index+2,start_index);
                    if(!operation.equals("and")||operation.equals("or"))
                    {
                        System.out.println("Conditiions must be seprated by and|or operators");
                        return false;
                    }
                }
                else
                {
                    return true;
                }
            }
        }

        boolean check_validity_of_select_command(String query,ArrayList<String> string)
        {
            String[] words = query.split(" ");
            HashMap<String,String> map = new HashMap<>(); 
            // for(int i=0;i<words.length;i++)
            // System.out.println(words[i]+"--");
            if(extra_chars_present(query))
            {
                System.out.println("Wrong Syntax");
                return false;
            }
            if(!words[2].equals("from"))
            {
                System.out.println(words[2] + " is found instead of from");
                return false;
            }
            if(!isTableExists(words[3]))
            {	System.out.println("Table Does not exists");
                return false;
            }
            int index = query.indexOf("where");
            if(index!=-1)
            {
                if(!getColumns(query,index,map))
                return false;
            }
            System.out.println(map); 
            try {
                if(words[1].equals("*"))
                {
                    if(words.length==4)
                    {
                        print_table1(words[3]);
                        // String filename = pathname+words[3]+".txt";
                        // FileReader fr=new FileReader(filename);    
                        // BufferedReader br=new BufferedReader(fr);
                        // String line = br.readLine();
                        // line = line.replace(',',' ');
                        // System.out.println(line);
                        // line = br.readLine();
                        // if(line.equals("0"))
                        // {
                        // 	System.out.println("Table is Empty");
                        // 	return false;
                        // }
                        // br.readLine();
                        // line = br.readLine();
                        // while(line!=null)
                        // {
                        // 	line = line.replace(',',' ');
                        // 	System.out.println(line);
                        // 	line = br.readLine();
                        // }
                    }
                    else
                    {
                        print_table2(words[3],map);
                    }
                }
                else
                {
                    String []columns = words[1].split(",");
                    print_table3(words[3],columns,map);
                }
                return true;
            }
            catch(Exception e)
            {
                
            }

            return true;
        }
        
        public void Sayhello()
        {
            System.out.println("hello");
        }

        // public static void main(String []args)
        // {
        //     Select obj = new Select();
            
        // }

    }