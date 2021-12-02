package com.example.easyGoBetween;

import com.example.BackEnd.TestProfile;

public class FrontEndTalkToObjects {

    public TestProfile createTestProfile(){
        TestProfile newProfile = new TestProfile("liam", "axelrod", "jason", "1");
        return newProfile;
    }

}
