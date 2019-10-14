package Model;

public class UsuarioDAO {

    private Integer id;
    private String nome;

    public UsuarioDAO() {
    }

    /**
     * @param name Nome
     */
    public UsuarioDAO(String name) {
        this.nome = name;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id ID do Usu�rio
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome Nome do Usu�rio
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
