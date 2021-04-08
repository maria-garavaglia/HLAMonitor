package com.github.tylergaravaglia.hlamonitor.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RprFomParser
{
    private String[] structList = {
        "ObjectModel",
        "ComplexDataType",
        "EnumeratedDataType",
        "Class",
        "Interaction",
        "Note"
    };

    private String[] fields = {
        "ComplexComponent",
        "Enumeration",
        "Dimension",
        "Attribute",
        "Parameter"
    };

    public RprFomParser(){}

    public void parseFile(String filename)
    {
        List<String> tokenList = new ArrayList<>();

        try(Scanner scanner = new Scanner(new File(filename)))
        {

            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine().strip();
                if(line.charAt(0) == '#')
                    continue;

                for(int i = 0; i < line.length();)
                {
                    if(line.charAt(i) == ')')
                    {
                        tokenList.add(")");
                        i++;
                    }
                    else if(line.charAt(i) == '(')
                    {
                        int findIndex = line.indexOf(' ', i);
                        if(findIndex == -1)
                            findIndex = line.length();

                        String text = line.substring(i + 1, findIndex);

                        i += text.length() + 1;
                        tokenList.add("(");
                        tokenList.add(text);
                    }
                    else if(line.charAt(i) == '"')
                    {
                        int findIndex = line.lastIndexOf('"');

                        String text = line.substring(i, findIndex + 1);

                        i += text.length();
                        tokenList.add(text);
                    }
                    else if(line.charAt(i) == ' ')
                    {
                        i++;
                    }
                    else
                    {
                        int findIndex = line.lastIndexOf(')');
                        if(findIndex == -1)
                            findIndex = line.length();

                        String text = line.substring(i, findIndex);
                        i += text.length();

                        if(text.charAt(text.length() - 1) == ')')
                        {
                            text = text.substring(0, text.length() - 1);
                            tokenList.add(text);
                            tokenList.add(")");
                        }
                        else
                        {
                            tokenList.add(text);
                        }
                    }
                }
            }

        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
