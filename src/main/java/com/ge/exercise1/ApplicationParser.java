package com.ge.exercise1;

import java.util.ArrayList;
import java.util.List;

public class ApplicationParser implements Parser {

    @Override
    public Application parseApplicationData(String data) {
        // INPUT : Application(id: 0,name: MyApp,users:[User(id: 2,name: Beth Jones)],groups:[Group(id: 1,name: SimpleGroup,users:[User(id: 2,name: Beth Jones)])])
        // MyApplication myapp = new MyApplication("0", "MyApp");
        return ExtractAppData(data);
    }

    private MyApplication ExtractAppData(String data) {
        
        List<User> usersList = new ArrayList<User>();
        List<Group> groupsList = new ArrayList<Group>();      
        MyApplication myapp = null;
        
        if (data.contains("Application")) {
            myapp = extractUsersAndGroupData(data, usersList, groupsList);
        }
        
        myapp.setUsers(usersList);
        myapp.setGroups(groupsList);
                
        return myapp;
    }

    private MyApplication extractUsersAndGroupData(String data, List<User> usersList, List<Group> groupsList) {
        
        MyApplication myapp;
        String appData = data.substring("Application".length());
        String fielddata = appData.substring(1, appData.length() - 1);
        String substring = fielddata.substring(0, fielddata.indexOf(",users"));
        String idsAndNamesArray[] = substring.split(",");
        
        myapp = new MyApplication(idsAndNamesArray[0].split(":")[1].trim(), idsAndNamesArray[1].split(":")[1].trim());
        String usersdata = fielddata.substring(fielddata.indexOf("users"), fielddata.indexOf(",groups"));
        String users = usersdata.substring(usersdata.indexOf("[") + 1, usersdata.indexOf("]"));
        
        extractUserData(usersList, users);
        extractGroupData(groupsList, fielddata);
        
        return myapp;
    }

    private void extractGroupData(List<Group> groupsList, String fielddata) {
        /* INPUT :
         * id: 0,name: MyApp,users:[User(id: 2,name: Beth Jones,salry:rrer),User(id: 3,name: Beth Jones3,salry:rrer),User(id: 4,name: Beth Jones4,salry:rrer)],groups:[Group(id:
         1,name: SimpleGroup,users:[User(id: 2,name: Beth Jones)])]
        */
        String groupData = fielddata.substring(fielddata.indexOf("groups"));
        // groups:[Group(id: 1,name: SimpleGroup,users:[User(id: 2,name: Beth Jones)])]
        groupData = groupData.substring(groupData.indexOf("[") + 1, groupData.length() - 1);
        // Group(id: 1,name: SimpleGroup,users:[User(id: 2,name: Beth Jones)])
        String[] groupsplit = groupData.split("Group\\(");
        
        for (String groupstr : groupsplit) {
            Group myGroup = null;
            if (groupstr != null && !groupstr.isEmpty()) {
                myGroup = new MyGroup();

                String idandnameuser = groupstr.substring(groupstr.indexOf("id"), groupstr.length() - 1);
                // id: 1,name: SimpleGroup,users:[User(id: 2,name: Beth Jones)]
                String idnamestr = idandnameuser.substring(0, idandnameuser.indexOf(",users"));
                String userValues = idandnameuser.substring(idandnameuser.indexOf("users"));
                String commaSeperatedUsers[] = idnamestr.split(",");
                List<User> usersList = new ArrayList<User>();
                
                for (String colonsep : commaSeperatedUsers) {
                    
                    if ("id".equals(colonsep.split(":")[0])) {
                        myGroup.setId(colonsep.split(":")[1].trim());
                    }
                    if ("name".equals(colonsep.split(":")[0])) {
                        myGroup.setName(colonsep.split(":")[1].trim());
                    }
                }
                String usersextract = userValues.substring(userValues.indexOf("[") + 1, userValues.indexOf("]"));
                extractUserData(usersList,usersextract);
                myGroup.setUsers(usersList);
                groupsList.add(myGroup);
            }         
        }
    }

    private void extractUserData(List<User> usersList, String users) {
        String[] usersplit = users.split("User");
        for (String user : usersplit) {
            User userobj = null;
            if (user != null && !user.isEmpty()) {
                userobj = new MyUser();
                if (!user.isEmpty()) {
                    String idandname = user.substring(user.indexOf("id"), user.indexOf(")"));
                    String commavalues[] = idandname.split(",");
                    for (String colonsep : commavalues) {
                        if ("id".equals(colonsep.split(":")[0])) {
                            userobj.setId(colonsep.split(":")[1].trim());
                        }
                        if ("name".equals(colonsep.split(":")[0])) {
                            userobj.setName(colonsep.split(":")[1].trim());
                        }
                    }
                }
                usersList.add(userobj);
            }
        }
    }

}
