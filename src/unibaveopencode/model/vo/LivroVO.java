/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.model.vo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

/**
 *
 * @author Stéfani
 */
@Data //Cria os get e set
@Builder //Cria um construtor dinâmico
@NoArgsConstructor //Cria um construtor sem argumentos
@AllArgsConstructor //Cria um construtor com todos os argumentos
@EqualsAndHashCode(of = {"numTombo"}) //Cria o HashCode a partir do ID
@Entity //Cria uma entidade Hibernate
@Table(name = "livro") //Relaciona a VO com a tabela no banco de dados
@NamedQueries({
    @NamedQuery(name = "LivroVO.findAll", query = "SELECT a FROM LivroVO a"), //Encontra todos os dados da tabela
    @NamedQuery(name = "LivroVO.findNumTombo", query = "SELECT a FROM LivroVO a WHERE a.numTombo = :numTombo"), //Encontra os livros por código
    @NamedQuery(name = "LivroVO.findNomTitulo", query = "SELECT a FROM LivroVO a WHERE UPPER(a.nomTitulo) LIKE '%'||UPPER(:nomTitulo)||'%'"), //Encontra os livros por nome
    @NamedQuery(name = "LivroVO.findNomAutor", query = "SELECT a FROM LivroVO a JOIN a.autor autor WHERE UPPER(autor.nomAutor) LIKE '%'||UPPER(:nomAutor)||'%'"), //Encontra os livros por autor
    @NamedQuery(name = "LivroVO.findNomEditora", query = "SELECT a FROM LivroVO a WHERE UPPER(a.editora.nomEditora) LIKE '%'||UPPER(:nomEditora)||'%'"), //Encontra os livros por editora
    @NamedQuery(name = "LivroVO.findDesAno", query = "SELECT a FROM LivroVO a WHERE a.desAno = :desAno"), //Encontra os livros por ano
    @NamedQuery(name = "LivroVO.findDesClassificacao", query = "SELECT a FROM LivroVO a WHERE UPPER(a.classificacao.desClassificacao) LIKE '%'||UPPER(:desClassificacao)||'%'"), //Encontra os livros por classificação
    @NamedQuery(name = "LivroVO.findDesUrl", query = "SELECT a FROM LivroVO a WHERE UPPER(a.desUrl) LIKE '%'||UPPER(:desUrl)||'%'") //Encontra os livros por URL 
})  
public class LivroVO implements Serializable {

    public static final String FIND_ALL = "LivroVO.findAll";
    public static final String FIND_NUM_TOMBO = "LivroVO.findNumTombo";
    public static final String FIND_NOM_TITULO = "LivroVO.findNomTitulo";
    public static final String FIND_NOM_AUTOR = "LivroVO.findNomAutor";
    public static final String FIND_NOM_EDITORA = "LivroVO.findNomEditora";
    public static final String FIND_DES_ANO = "LivroVO.findDesAno";
    public static final String FIND_DES_CLASSIFICACAO = "LivroVO.findDesClassificacao";
    public static final String FIND_DES_URL = "LivroVO.findDesUrl";
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "num_tombo", nullable = false, unique = true)
    private Long numTombo;

    @Column(name = "nom_titulo")
    private String nomTitulo;

    @JoinTable(name = "livro_autor", joinColumns = @JoinColumn(name = "num_tombo"),
            inverseJoinColumns = @JoinColumn(name = "cod_autor"), uniqueConstraints=@UniqueConstraint(columnNames={"num_tombo","cod_autor"}))
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AutorVO> autor;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_editora")
    private EditoraVO editora;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_classificacao")
    private ClassificacaoVO classificacao;

    @Column(name = "des_ano")
    private Integer desAno;

    @Column(name = "des_url")
    private String desUrl;
}
