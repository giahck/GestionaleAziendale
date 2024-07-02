package GestionaleAziendale.GesionaleBack.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class UserDtoCompetenze {



        private int id;
        private String nome;
        private String cognome;

        public UserDtoCompetenze(int id, String nome, String cognome) {
            this.id = id;
            this.nome = nome;
            this.cognome = cognome;
        }
    }

