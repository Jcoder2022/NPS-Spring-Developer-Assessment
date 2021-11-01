package com.nps.devassessment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class AlgorithTest {



    @Test
    void isNumberValidSuccessScenarioTest(){
        Boolean actual = Algorithm.isNumberValid("4242424242426742");
        Assertions.assertEquals(true,actual);
    }

    @Test
    void isNumberValidFailureTest(){
        Boolean actual = Algorithm.isNumberValid("1111111111111111");
        Assertions.assertEquals(false,actual);
    }


}
