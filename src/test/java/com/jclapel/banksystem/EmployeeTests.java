package com.jclapel.banksystem;

import com.example.BackEnd.Employee;
import com.example.BackEnd.Facade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class EmployeeTests {

    private Facade facade;
    private int ID;
    private Employee johnSmith;


    @BeforeEach
    public void testSetup() {
        facade = new Facade();
        ID = facade.createEmployee("John Smith", "Password80!"); //setting up test Employee
        johnSmith = facade.employees.get(ID);
    }

    @Test
    public void testEmployeeCreation(){

        //given
        int ID = facade.createEmployee("John Smith", "Password80!");
        Employee johnSmith = facade.employees.get(ID);

        //then
        assertThat(johnSmith.getName()).isEqualTo("John Smith");
        assertThat(facade.createEmployee("", "")).isEqualTo(0);
        //We want to make sure the name is not empty?????

        facade.removeEmployee(ID); //testing employee removal
        johnSmith = facade.loadEmployee(ID);
        assertThat(johnSmith).isEqualTo(null);
    }   // Patrik, Labi,, Erik

    @Test
    public void loadEmployeeTest(){
        assertThat(facade.loadEmployee(ID)).isEqualTo(johnSmith);
    }   //Labi

}
