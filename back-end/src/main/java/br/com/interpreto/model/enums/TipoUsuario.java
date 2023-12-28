package br.com.interpreto.model.enums;

public enum TipoUsuario {
    ADMIN("admin"),
    SURDO("surdo"),
    INTERPRETE("interprete");

    private String role;

    TipoUsuario(String role){
        this.role = role;
    }
    public String getRole(){
        return role;
    }
}
