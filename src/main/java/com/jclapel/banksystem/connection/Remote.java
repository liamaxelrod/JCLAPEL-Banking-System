package com.jclapel.banksystem.connection;

import com.jclapel.banksystem.back_end.TestProfile;

public class Remote {
    public TestProfile createTestProfile(){
        TestProfile newProfile = new TestProfile("liam", "axelrod", "jason", "1");
        return newProfile;
    }
}
