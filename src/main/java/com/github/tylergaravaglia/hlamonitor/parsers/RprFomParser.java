package com.github.tylergaravaglia.hlamonitor.parsers;

import com.github.tylergaravaglia.hlamonitor.hla.OmtObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RprFomParser {
    private List<String> structList = new ArrayList<>(List.of(
        "ObjectModel",
        "ComplexDataType",
        "EnumeratedDataType",
        "Class",
        "Interaction",
        "Note"
    ));

    private List<String> fields = new ArrayList<>(List.of(
        "ComplexComponent",
        "Enumeration",
        "Dimension",
        "Attribute",
        "Parameter"
    ));

    private List<OmtObject> omtObjectList = new ArrayList<>();
//    private Map<String, List<String>> structDict = new HashMap<>();

    public RprFomParser() {}

    public void parseFile(String filename) {
        List<String> tokenList = new ArrayList<>();

        try(Scanner scanner = new Scanner(new File(filename))) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine().strip();
                if(line.charAt(0) == '#')
                    continue;

                for(int i = 0; i < line.length();) {
                    if(line.charAt(i) == ')') {
//                        tokenList.add(")");
                        i++;
                    }
                    else if(line.charAt(i) == '(') {
                        int findIndex = line.indexOf(' ', i);
                        if(findIndex == -1)
                            findIndex = line.length();

                        String text = line.substring(i + 1, findIndex);

                        i += text.length() + 1;
//                        tokenList.add("(");
                        tokenList.add(text);
                    }
                    else if(line.charAt(i) == '"') {
                        int findIndex = line.lastIndexOf('"');

                        String text = line.substring(i, findIndex + 1);

                        i += text.length();
                        tokenList.add(text);
                    }
                    else if(line.charAt(i) == ' ') {
                        i++;
                    }
                    else {
                        int findIndex = line.lastIndexOf(')');
                        if(findIndex == -1)
                            findIndex = line.length();

                        String text = line.substring(i, findIndex);
                        i += text.length();

                        if(text.charAt(text.length() - 1) == ')') {
                            text = text.substring(0, text.length() - 1);
                            tokenList.add(text);
//                            tokenList.add(")");
                        }
                        else {
                            tokenList.add(text);
                        }
                    }
                }
            }

        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        OmtObject currObject = null;
        OmtObject currField = null;
        for(int index = 0; index < tokenList.size(); index++) {
            String token = tokenList.get(index);
            if(structList.contains(token)) {
                if(currObject != null) {
                    omtObjectList.add(currObject);
                }
                currObject = new OmtObject(token);
            }
            else if(fields.contains(token))
            {
                if(currField != null) {
                    currObject.addField(currField.getType(), currField);
                }
                currField = new OmtObject(token);
            }
            else {
                String value = tokenList.get(++index);
                if(currField != null) {
                    currField.setValue(token, value);
                }
                else if(currObject != null) {
                    currObject.setValue(token, value);
                }
            }
        }

        System.out.println("test");

//        for(String structName : structList) {
//            structDict.put(structName, new ArrayList<String>());
//        }
//
//        int level = 0;
//        int level_old = 0;
//        List<String> currList = null;
//
//        for(int index = 0; index < tokenList.size(); index++)
//        {
//            String token = tokenList.get(index);
//
//            if(token.equals("("))
//            {
//                level++;
//                if(structList.contains(tokenList.get(index + 1)))
//                {
//                    level_old = level - 1;
//                    currList = structDict.get(tokenList.get(index + 1));
//                }
//            }
//            else if(token.equals(")"))
//            {
//                level--;
//
//                if(level == level_old)
//                {
//                    currList = null;
//                }
//            }
//            else if(currList != null)
//            {
//                currList.add(token);
//            }
//        }
//
//
//        for(int index = 1; index < structDict.get("ObjectModel").size(); index++) {
//            String key = structDict.get("ObjectModel").get(index);
//            if(key.equals("ObjectModel"))
//                continue;
//
//            String val = structDict.get("ObjectModel").get(++index);
//
//        }

//        int level = 0;
//        for(String token : tokenList) {
//            if(token.charAt(0) == ')')
//                level--;
//            for(int count = 0; count < level; count++) {
//                System.out.print("    ");
//            }
//
//            System.out.println(token);
//
//            if(token.charAt(0) == '(')
//                level++;
//        }
    }


}
