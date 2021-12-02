package com.example.TestingInterface;






public class TestingInterface {

    public TestProfile defaultTestProfile(){
        TestProfile newProfile = new TestProfile("liam", "axelrod", "jason", "1");
        return newProfile;
    }

    public TestProfile createTestProfile(firstName, secondName, userName, password){

        TestProfile newProfile = new TestProfile(firstName, secondName, userName, password);
        return newProfile;
    }

}
