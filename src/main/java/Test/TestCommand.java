package Test;

import Entity.Command;
import org.junit.Test;


public class TestCommand {
    @Test
    public void test1(){
        Command command = new Command();
        System.out.println(command.getRetryNum());
        System.out.println(command.isSuspend());
    }
}
