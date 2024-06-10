package project.epic_energy_back.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){
        super(message);
    }
}