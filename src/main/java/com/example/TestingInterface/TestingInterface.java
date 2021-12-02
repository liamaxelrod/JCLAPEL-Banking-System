package com.example.TestingInterface;


import java.util.HashMap;

public class TestingInterface {
    public HashMap<Integer, TestProfile> testProfileList = new HashMap<>();

    public TestProfile defaultTestProfile(){
        TestProfile newProfile = new TestProfile("liam", "axelrod", "jason", "1");
        return newProfile;
    }

    public void createTestProfile(String firstName, String secondName, int userName, String password){
        TestProfile newProfile = new TestProfile(firstName, secondName, userName, password);
        testProfileList.put(userName,newProfile);
    }

}
