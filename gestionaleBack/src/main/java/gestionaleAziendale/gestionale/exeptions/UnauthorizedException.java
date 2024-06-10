package project.epic_energy_back.exceptions;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(String msg){
        super(msg);
    }
}