package romero.kenny.restaurante.restaurante.ui.modelos;

/**
 * Created by kenny.romero on 16/09/2015.
 */
public class Contacto {


    private String idContacto;
    private String name;
    private String email;
    private String genero;
    private String mobil;
    private String home;
    private String office;

    public Contacto (String idContacto, String name, String email, String genero, String mobil, String home, String office){
        this.idContacto = idContacto;
        this.name = name;
        this.email = email;
        this.genero = genero;
        this.mobil = mobil;
        this.home = home;
        this.office = office;

    }

    public String getIdContacto(){
        return idContacto;
    }

    public String getNombre(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getGenero(){
        return genero;
    }

    public String getMobil(){
        return mobil;
    }

    public String getHome(){
        return home;
    }

    public String getOffice(){
        return office;
    }

    public  boolean compararCon(Contacto contacto){
        return this.name.compareTo(contacto.name) == 0 &&
                this.email.compareTo(contacto.email) == 0 &&
                this.genero.compareTo(contacto.genero) == 0 &&
                this.mobil.compareTo(contacto.mobil) == 0 &&
                this.home.compareTo(contacto.home) == 0 &&
                this.office.compareTo(contacto.office) == 0 ;
    }

}
