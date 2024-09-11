package edu.fatec.Porygon.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
public class Portal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String url;
    private String seletorNoticia;

    @ManyToOne
    @JoinColumn(name = "agendador_id")
    private Agendador agendador;

    private boolean ativo;

    @ManyToMany
    @JoinTable(
        name = "portal_tag", 
        joinColumns = @JoinColumn(name = "portal_id"), 
        inverseJoinColumns = @JoinColumn(name = "tag_id") 
    )
    private List<Tag> tags;

    @OneToMany(mappedBy = "portal")
    private List<Noticia> noticias;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSeletorNoticia() {
        return seletorNoticia;
    }

    public void setSeletorNoticia(String seletorNoticia) {
        this.seletorNoticia = seletorNoticia;
    }

    public Agendador getAgendador() {
        return agendador;
    }

    public void setAgendador(Agendador agendador) {
        this.agendador = agendador;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }
}
