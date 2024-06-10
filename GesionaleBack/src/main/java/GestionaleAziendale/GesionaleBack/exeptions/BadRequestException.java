package GestionaleAziendale.GesionaleBack.exeptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

}