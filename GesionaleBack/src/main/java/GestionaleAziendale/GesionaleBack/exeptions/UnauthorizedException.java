package GestionaleAziendale.GesionaleBack.exeptions;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(String msg){
        super(msg);
    }
}